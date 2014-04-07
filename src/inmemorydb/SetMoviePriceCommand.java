package inmemorydb;

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
