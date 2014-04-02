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
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		m = inv.getMovie(uid);
		
		assertEquals("Movie name should be equal to" + movieName, m.getName(), movieName);
	}
	
	@Test
	public void testSellMovie() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 2;
		
		Inventory inv = new Inventory();
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		m = inv.getMovie(uid);
		
		inv.sellMovie(uid);
		
		assertEquals(
				"Movie qty should be equal to" + Integer.toString(movieQty - 1), 
				m.getQty(), 
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
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		m = inv.getMovie(uid);
		
		inv.addQty(uid, qtyToAdd);
		
		assertEquals(
				"Movie qty should be equal to" + Integer.toString(movieQty + qtyToAdd), 
				m.getQty(), 
				movieQty + qtyToAdd
				);
	}
	
	@Test
	public void testChangePrice() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		double newPrice = 5.00;
		
		Inventory inv = new Inventory();
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		m = inv.getMovie(uid);
		
		inv.changePrice(uid, newPrice);
		
		assertEquals(
				"Movie qty should be equal to" + Double.toString(newPrice), 
				m.getPrice(), 
				newPrice
				);
	}
	
	@Test
	public void testFindPriceByID() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		assertEquals(
				"Movie price should be equal to" + Double.toString(moviePrice), 
				inv.getPrice(uid), 
				moviePrice
				);
	}
	
	@Test
	public void testFindPriceByName() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		assertEquals(
				"Movie price should be equal to" + Double.toString(moviePrice), 
				inv.getPrice(movieName), 
				moviePrice
				);
	}
	
	@Test
	public void testFindQtyByID() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		assertEquals(
				"Movie price should be equal to" + Integer.toString(movieQty), 
				inv.getQty(uid), 
				movieQty
				);
	}
	
	@Test
	public void testFindQtyByName() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		Inventory inv = new Inventory();
		Movie m;
		
		int uid;
		
		uid = inv.addMovie(
				movieName,
				moviePrice,
				movieQty
				);
		
		assertEquals(
				"Movie price should be equal to" + Integer.toString(movieQty), 
				inv.getQty(movieName), 
				movieQty
				);
	}
	
	

}
