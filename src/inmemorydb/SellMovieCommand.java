package inmemorydb;

/**
 * SellMovieCommand is a Concrete class in the Command Pattern which sells a movie
 * in the Inventory.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class SellMovieCommand extends Command {
	
	AbstractInventory subjectInventory;
	int movieToSell;
	
	public SellMovieCommand(AbstractInventory inv, int movieID) {
		subjectInventory = inv;
		movieToSell = movieID;
	}

	/**
	 * Execute method in SellMovieCommand calls the sellMovie method 
	 * in the subject Inventory object using the parameters passed in 
	 * via the constructor.
	 */
	@Override
	public void execute() {
		subjectInventory.sellMovie(movieToSell);
	}

}
