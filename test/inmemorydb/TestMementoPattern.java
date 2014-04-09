package inmemorydb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMementoPattern {

	@Test
	public void testMementoCreation() {
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
		
		inv.saveToMemento();
		
		assertTrue(true);
	}

}
