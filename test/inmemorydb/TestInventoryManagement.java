package inmemorydb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestInventoryManagement {

	@Test
	public void testAddNewMovie() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		assertEquals(
				"Movie name should be equal to" + movieName, 
				inv.getMovieName(uid), 
				movieName
				);
	}
	
	@Test
	public void testSellMovie() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 2;
		
		Inventory inv = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		inv.sellMovie(uid);
		
		assertEquals(
				"Movie qty should be equal to" + Integer.toString(movieQty - 1), 
				inv.getMovieQty(uid), 
				movieQty - 1
				);
	}
	
	@Test
	public void testAddQty() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		int qtyToAdd = 2;
		
		Inventory inv = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		inv.addMovieQty(uid, qtyToAdd);
		
		assertEquals(
				"Movie qty should be equal to" + Integer.toString(movieQty + qtyToAdd), 
				inv.getMovieQty(uid), 
				movieQty + qtyToAdd
				);
	}
	
	@Test
	public void testSetPrice() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		double newPrice = 5.00;
		
		Inventory inv = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		inv.setMoviePrice(uid, newPrice);
		
		assertTrue(
				"Movie qty should be equal to" + Double.toString(newPrice), 
				inv.getMoviePrice(uid) == newPrice
				);
	}
	
	@Test
	public void testFindPriceByID() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		assertTrue(
				"Movie price should be equal to" + Double.toString(moviePrice), 
				inv.getMoviePrice(uid) == moviePrice
				);
	}
	
	@Test
	public void testFindPriceByName() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		assertTrue(
				"Movie price should be equal to" + Double.toString(moviePrice), 
				inv.getMoviePrice(movieName) == moviePrice
				);
	}
	
	@Test
	public void testFindQtyByID() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		assertEquals(
				"Movie price should be equal to" + Integer.toString(movieQty), 
				inv.getMovieQty(uid), 
				movieQty
				);
	}
	
	@Test
	public void testFindQtyByName() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		assertEquals(
				"Movie price should be equal to" + Integer.toString(movieQty), 
				inv.getMovieQty(movieName), 
				movieQty
				);
	}

	@Test
	public void testMemento() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		
		Inventory inv2 = new Inventory();
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		inv.saveToMemento();
		
		inv = null;
		
		inv2.restoreFromMemento();
		
		assertEquals(
				"Name of movie in restored inventory should match 'Titanic'",
				inv2.getMovieName(uid),
				movieName
				);
	}

	@Test
	public void testDecoratorMemento() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		AbstractInventory inv = new CommandDecorator(new Inventory());
		
		AbstractInventory inv2 = new CommandDecorator(new Inventory());
		
		int uid;
		
		inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		uid = inv.getCurrentUniqueIDSequence();
		
		inv.saveToMemento();
		
		inv = null;
		
		inv2.restoreFromMemento();
		
		assertEquals(
				"Name of movie in restored inventory should match 'Titanic'",
				inv2.getMovieName(uid),
				movieName
				);
	}

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
