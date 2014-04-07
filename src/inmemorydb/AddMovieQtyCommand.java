package inmemorydb;

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
