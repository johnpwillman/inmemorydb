package inmemorydb;

/**
 * AddMovieQtyCommand is a Concrete class in the Command Pattern which adds quantity
 * to a movie in the Inventory.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class AddMovieQtyCommand extends Command {
	
	AbstractInventory subjectInventory;
	int movieToUpdate, addedQty;
	
	public AddMovieQtyCommand(AbstractInventory inv, int movieID, int qtyToAdd) {
		subjectInventory = inv;
		movieToUpdate = movieID;
		addedQty = qtyToAdd;
	}

	@Override
	public void execute() {
		subjectInventory.addMovieQty(movieToUpdate, addedQty);
	}

}
