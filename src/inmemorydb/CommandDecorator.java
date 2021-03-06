package inmemorydb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * CommandDecorator is a child of InventoryDecorator which adds Command
 * Pattern functionality onto the regular functionality of the Inventory
 * Class. Commands are serialized when they are called and are deserialized
 * and executed when a memento is restored.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class CommandDecorator extends InventoryDecorator {
	
	private Inventory decoratedInventory;
	private Command c;
	private File commandFile = new File("commands");
	private File tempCommandFile = new File("tempCommands");
	private String cmdDelim = "|";
	private int numCommands = 0;
	private final int maxCommands = 1000;

	/**
	 * CommandDecorator serializes every command that changes the state of the
	 * database in an Inventory and defers to the Inventory object itself when
	 * there is no state change.
	 * @param inventoryToDecorate
	 */
	public CommandDecorator(Inventory inventoryToDecorate) {
		super(inventoryToDecorate);
		decoratedInventory = inventoryToDecorate;
	}

	/**
	 * Create and execute AddMovieCommand object for decorated Inventory. Command
	 * written to file for later restore.
	 */
	@Override
	public void addMovie(String movieName, double moviePrice, int movieQty) {
		c = new AddMovieCommand(decoratedInventory, movieName, moviePrice, movieQty);
		c.execute();
		
		writeCommandToFile(
				"addMovie" + cmdDelim +
				movieName + cmdDelim + 
				Double.toString(moviePrice) + cmdDelim + 
				Integer.toString(movieQty)
				);
	}

	/**
	 * Create and execute SellMovieCcmmand object for decorated Inventory.
	 * Command written to file for later restore.
	 */
	@Override
	public void sellMovie(int movieID) {
		c = new SellMovieCommand(decoratedInventory, movieID);
		c.execute();
		
		writeCommandToFile(
				"sellMovie" + cmdDelim +
				Integer.toString(movieID)
				);
	}

	/**
	 * Create and execute AddMoveQty object for decorated Inventory. Command
	 * written to file for later restore.
	 */
	@Override
	public void addMovieQty(int movieID, int qtyToAdd) {
		c = new AddMovieQtyCommand(decoratedInventory, movieID, qtyToAdd);
		c.execute();
		
		writeCommandToFile(
				"addMovieQty" + cmdDelim +
				Integer.toString(movieID) + cmdDelim + 
				Integer.toString(qtyToAdd)
				);
	}

	/**
	 * Create and execute SetMoviePrice object for decorated Inventory. Command
	 * written to file for later restore.
	 */
	@Override
	public void setMoviePrice(int movieID, double newPrice) {
		c = new SetMoviePriceCommand(decoratedInventory, movieID, newPrice);
		c.execute();
		
		writeCommandToFile(
				"setMoviePrice" + cmdDelim +
				Integer.toString(movieID) + cmdDelim + 
				Double.toString(newPrice)
				);
	}
	
	/**
	 * In addition to regular functionality, saveToMemento also deletes the 
	 * stored record of commands since last memento.
	 */
	@Override
	public void saveToMemento() {
		decoratedInventory.saveToMemento();
		
		deleteCommands();
	}

	/**
	 * In addition to regular functionality, restoreFromMemento also
	 * restores the commands executed since last memento was saved.
	 */
	@Override
	public void restoreFromMemento() {
		decoratedInventory.restoreFromMemento();
		
		numCommands = 0;//Reset command counter
		executeCommandsFromFile();
	}
	
	/**
	 * Stores a string representing a command to a file for later retrieval.
	 * @param commandToWrite
	 */
	private void writeCommandToFile(String commandToWrite) {
		try {
			//Save commands to temp file first
			tempCommandFile.delete();
			Files.copy(commandFile.toPath(), tempCommandFile.toPath());
			
			BufferedWriter out = new BufferedWriter(new FileWriter(tempCommandFile, true));
			out.write(commandToWrite);
			
			numCommands++;
			
			out.newLine();
			out.close();
			
			//Safe save of commands
			commandFile.delete();
			tempCommandFile.renameTo(commandFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//Save a memento if command file becomes too large.
		if (numCommands >= maxCommands) {
			saveToMemento();
		}
	}
	
	/**
	 * Retrieves the commands executed since the last memento front disk and
	 * re-executes them.
	 */
	private void executeCommandsFromFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(commandFile));
			
			String singleCommand;
			String[] cmdAsArray;
			while ((singleCommand = in.readLine()) != null) {
				System.out.println("Command: " + singleCommand);
				cmdAsArray = singleCommand.split("[" + cmdDelim + "]");
				
				switch (cmdAsArray[0]) {
					case "addMovie":		c = new AddMovieCommand(
												decoratedInventory,
												cmdAsArray[1], 
												Double.parseDouble(cmdAsArray[2]),
												Integer.parseInt(cmdAsArray[3])
												);
											break;
					case "sellMovie":		c = new SellMovieCommand(
												decoratedInventory,
												Integer.parseInt(cmdAsArray[1])
												);
											break;
					case "addMovieQty":		c = new AddMovieQtyCommand(
												decoratedInventory,
												Integer.parseInt(cmdAsArray[1]), 
												Integer.parseInt(cmdAsArray[2])
												);
											break;
					case "setMoviePrice":	c = new SetMoviePriceCommand(
												decoratedInventory,
												Integer.parseInt(cmdAsArray[1]), 
												Double.parseDouble(cmdAsArray[2])
												);
											break;
				}
				
				c.execute();
				numCommands++;
				
			}
			
			in.close();
		} catch(FileNotFoundException e) {
			System.out.println("No Commands to restore.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Overwrite the file on disk that stores the commands executed
	 * since last memento.
	 */
	private void deleteCommands() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(commandFile));
			out.write("");
			numCommands = 0;//Reset command counter
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		/* Old deletion code deletes entire file
		if (!commandFile.delete()) {
			System.out.println("Cannot delete: File does not exist.");
		} else {
			System.out.println(commandFile.getPath() + " deleted");
		}
		*/
	}

}
