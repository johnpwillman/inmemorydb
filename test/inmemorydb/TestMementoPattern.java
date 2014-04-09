package inmemorydb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMementoPattern {

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

}
