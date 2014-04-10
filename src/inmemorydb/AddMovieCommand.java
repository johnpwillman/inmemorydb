package inmemorydb;

/**
 * AddMovieCommand is a Concrete class in the Command Pattern which adds a new movie
 * to the Inventory.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class AddMovieCommand extends Command {
	
	AbstractInventory subjectInventory;
	String nameOfNewMovie;
	double priceOfNewMovie;
	int qtyOfNewMovie;
	
	public AddMovieCommand(
			AbstractInventory inv, 
			String movieName, 
			double moviePrice, 
			int movieQty
			) {
		subjectInventory = inv;
		nameOfNewMovie = movieName;
		priceOfNewMovie = moviePrice;
		qtyOfNewMovie = movieQty;
	}

	/**
	 * Execute method in AddMovieCommand calls the addMovie method on the 
	 * subject Inventory object using the params passed in through the
	 * constructor.
	 */
	@Override
	public void execute() {
		subjectInventory.addMovie(nameOfNewMovie, priceOfNewMovie, qtyOfNewMovie);
	}

}
