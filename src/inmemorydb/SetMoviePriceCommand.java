package inmemorydb;

/**
 * SetMoviePriceCommand is a Concrete class in the Command Pattern which sets the price
 * of a movie in the Inventory.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class SetMoviePriceCommand extends Command {
	
	AbstractInventory subjectInventory;
	int movieToUpdate;
	double updatedPrice;
	
	public SetMoviePriceCommand(AbstractInventory inv, int movieID, double newPrice) {
		subjectInventory = inv;
		movieToUpdate = movieID;
		updatedPrice = newPrice;
	}

	/**
	 * Execute method in SetMoviePriceCommand calls the setMoviePrice
	 * method in the subject Inventory object using the parameters passed 
	 * in via the constructor.
	 */
	@Override
	public void execute() {
		subjectInventory.setMoviePrice(movieToUpdate, updatedPrice);
	}

}
