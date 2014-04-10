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

	@Override
	public void execute() {
		subjectInventory.sellMovie(movieToSell);
	}

}
