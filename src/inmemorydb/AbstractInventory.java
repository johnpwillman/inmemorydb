package inmemorydb;

/**
 * AbstractInventory is the parent class that defines the public methods of stand-
 * alone Inventory objects as well as those that are decorated. It functions as the
 * interface for the client application to access the movie Inventory.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public abstract class AbstractInventory {
	
	public abstract void addMovie(String movieName, double moviePrice, int movieQty);
	
	public abstract String getMovieName(int movieID);
	
	public abstract void sellMovie(int movieID);
	
	public abstract int getMovieQty(int movieID);
	
	public abstract int getMovieQty(String movieName);
	
	public abstract void addMovieQty(int movieID, int qtyToAdd);
	
	public abstract void setMoviePrice(int movieID, double newPrice);
	
	public abstract double getMoviePrice(int movieID);
	
	public abstract double getMoviePrice(String movieName);
	
	public abstract void saveToMemento();
	
	public abstract void restoreFromMemento();
	
	public abstract int getCurrentUniqueIDSequence();

}
