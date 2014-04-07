package inmemorydb;

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
