package inmemorydb;

public class AddMovieCommand extends Command {
	
	AbstractInventory subjectInventory;
	String nameOfNewMovie;
	double priceOfNewMovie;
	int qtyOfNewMovie;
	
	public AddMovieCommand(AbstractInventory inv, String movieName, double moviePrice, int movieQty) {
		subjectInventory = inv;
		nameOfNewMovie = movieName;
		priceOfNewMovie = moviePrice;
		qtyOfNewMovie = movieQty;
	}

	@Override
	public void execute() {
		subjectInventory.addMovie(nameOfNewMovie, priceOfNewMovie, qtyOfNewMovie);
	}

}
