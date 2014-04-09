package inmemorydb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
				case "addMovie":		super.addMovie(
											cmdAsArray[1], 
											Double.parseDouble(cmdAsArray[2]),
											Integer.parseInt(cmdAsArray[3])
											);
										break;
				case "sellMovie":		super.sellMovie(
											Integer.parseInt(cmdAsArray[1])
											);
										break;
				case "addMovieQty":		super.addMovieQty(
											Integer.parseInt(cmdAsArray[1]), 
											Integer.parseInt(cmdAsArray[2])
											);
										break;
				case "setMoviePrice":	super.setMoviePrice(
											Integer.parseInt(cmdAsArray[1]), 
											Double.parseDouble(cmdAsArray[2])
											);
										break;
				}
			}
			
			in.close();
		} catch(FileNotFoundException e) {
			//File doesn't exist yet
			//Do Nothing
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteCommands() {
		commandFile.delete();
	}

}
