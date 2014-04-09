package inmemorydb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandDecorator {

	@Test
	public void testCommandWithMemento() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		double newPrice = 4.00;
		int movieQty = 1;
		int qtyToAdd = 7;
		int uid;
		
		AbstractInventory inv = new CommandDecorator(new Inventory());
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		inv.saveToMemento();
		
		inv.setMoviePrice(uid, newPrice);
		
		inv.addMovieQty(uid, qtyToAdd);
		
		inv = null;
		
		inv = new CommandDecorator(new Inventory());
		
		inv.restoreFromMemento();
		
		assertTrue(
				"Movie price should be equal to" + Double.toString(newPrice), 
				inv.getMoviePrice(uid) == newPrice
				);

		assertTrue(
				"Movie qty should be equal to" + Integer.toString(movieQty + qtyToAdd), 
				inv.getMovieQty(uid) == movieQty + qtyToAdd
				);
		
	}

}
