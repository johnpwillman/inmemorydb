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
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
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
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
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
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
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
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
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
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
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
		
		int uid;
		
		uid = inv.addMovie(
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
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
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
		
		int uid;
		
		uid = inv.addMovie(
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
	
	

}
