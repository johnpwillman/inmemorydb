package inmemorydb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	private String cmdDelim = "|";

	public CommandDecorator(Inventory inventoryToDecorate) {
		super(inventoryToDecorate);
		decoratedInventory = inventoryToDecorate;
	}

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

	@Override
	public void sellMovie(int movieID) {
		c = new SellMovieCommand(decoratedInventory, movieID);
		c.execute();
		
		writeCommandToFile(
				"sellMovie" + cmdDelim +
				Integer.toString(movieID)
				);
	}

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

	@Override
	public void saveToMemento() {
		decoratedInventory.saveToMemento();
		
		deleteCommands();
	}

	@Override
	public void restoreFromMemento() {
		decoratedInventory.restoreFromMemento();
		
		executeCommandsFromFile();
	}
	
	private void writeCommandToFile(String commandToWrite) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(commandFile, true));
			out.write(commandToWrite);
			out.newLine();
			
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
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
				
			}
			
			in.close();
		} catch(FileNotFoundException e) {
			System.out.println("No Commands to restore.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteCommands() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(commandFile));
			out.write("");
			
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
