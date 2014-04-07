package inmemorydb;

public abstract class AbstractInventory {
	
	public abstract int addMovie();
	
	public abstract void sellMovie(int movieID);
	
	public abstract void addMovieQty(int movieID, int qtyToAdd);
	
	public abstract void setMoviePrice(int movieID, double newPrice);
	
	public abstract Memento saveToMemento();
	
	public abstract void restoreFromMemento(Memento memento);

}
