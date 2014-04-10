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

	@Override
	public void execute() {
		subjectInventory.setMoviePrice(movieToUpdate, updatedPrice);
	}

}
