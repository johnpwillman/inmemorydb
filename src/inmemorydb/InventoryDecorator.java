package inmemorydb;

/**
 * InventoryDecorator is a concrete instance of an AbstractInventory
 * that wraps the functionality of an Inventory object and adds no
 * new functionality. This implementation follows the Decorator
 * Pattern and will allow its children to attach extra functionality
 * to Inventory objects.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class InventoryDecorator extends AbstractInventory {
	
	private Inventory decoratedInventory;
	
	/**
	 * InventoryDecorator contains reference to real Inventory 
	 * object and defers all method calls to this decorated
	 * Inventory's methods.
	 * @param inventoryToDecorate
	 */
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
	public void saveToMemento() {
		decoratedInventory.saveToMemento();
	}

	@Override
	public void restoreFromMemento() {
		decoratedInventory.restoreFromMemento();
	}

	@Override
	public int getCurrentUniqueIDSequence() {
		return decoratedInventory.getCurrentUniqueIDSequence();
	}
	
}
