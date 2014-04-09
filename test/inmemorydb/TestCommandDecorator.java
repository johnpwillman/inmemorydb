package inmemorydb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandDecorator {

	@Test
	public void test() {
		String movieName = "Titanic";
		double moviePrice = 3.00;
		int movieQty = 1;
		
		AbstractInventory inv = new CommandDecorator(new Inventory());
		
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

}
