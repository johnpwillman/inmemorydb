package inmemorydb;

public class InventoryDecorator extends AbstractInventory {
	
	private Inventory decoratedInventory;
	
	public InventoryDecorator(Inventory inventoryToDecorate) {
		decoratedInventory = inventoryToDecorate;
	}

	@Override
	public void addMovie(String movieName, double moviePrice, int movieQty) {
		decoratedInventory.addMovie(movieName, moviePrice, movieQty);
	}

	@Override
	public String getMovieName(int movieID) {
		return decoratedInventory.getMovieName(movieID);
	}

	@Override
	public void sellMovie(int movieID) {
		decoratedInventory.sellMovie(movieID);
	}

	@Override
	public int getMovieQty(int movieID) {
		return decoratedInventory.getMovieQty(movieID);
	}

	@Override
	public int getMovieQty(String movieName) {
		return decoratedInventory.getMovieQty(movieName);
	}

	@Override
	public void addMovieQty(int movieID, int qtyToAdd) {
		decoratedInventory.addMovieQty(movieID, qtyToAdd);
		
	}

	@Override
	public void setMoviePrice(int movieID, double newPrice) {
		decoratedInventory.setMoviePrice(movieID, newPrice);
	}

	@Override
	public double getMoviePrice(int movieID) {
		return decoratedInventory.getMoviePrice(movieID);
	}

	@Override
	public double getMoviePrice(String movieName) {
		return decoratedInventory.getMoviePrice(movieName);
	}

	@Override
	public Memento saveToMemento() {
		return decoratedInventory.saveToMemento();
	}

	@Override
	public void restoreFromMemento(Memento memento) {
		decoratedInventory.restoreFromMemento(memento);
	}
	
}
