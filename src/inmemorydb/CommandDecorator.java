package inmemorydb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CommandDecorator extends InventoryDecorator {
	
	private Inventory decoratedInventory;
	private Command c;
	private File commandFile = new File("commands");

	public CommandDecorator(Inventory inventoryToDecorate) {
		super(inventoryToDecorate);
		decoratedInventory = inventoryToDecorate;
	}

	@Override
	public void addMovie(String movieName, double moviePrice, int movieQty) {
		c = new AddMovieCommand(decoratedInventory, movieName, moviePrice, movieQty);
		c.execute();
		
		writeCommandToFile("addMovie,");
		readCommandsFromFile();
	}

	@Override
	public void sellMovie(int movieID) {
		c = new SellMovieCommand(decoratedInventory, movieID);
		c.execute();
	}

	@Override
	public void addMovieQty(int movieID, int qtyToAdd) {
		c = new AddMovieQtyCommand(decoratedInventory, movieID, qtyToAdd);
		c.execute();
	}

	@Override
	public void setMoviePrice(int movieID, double newPrice) {
		c = new SetMoviePriceCommand(decoratedInventory, movieID, newPrice);
		c.execute();
	}

	@Override
	public void saveToMemento() {
		decoratedInventory.saveToMemento();
		
		deleteCommands();
	}

	@Override
	public void restoreFromMemento() {
		decoratedInventory.restoreFromMemento();
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
	
	private void readCommandsFromFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(commandFile));
			
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteCommands() {
		commandFile.delete();
	}

}
