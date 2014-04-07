package inmemorydb;

public class CommandDecorator extends InventoryDecorator {
	
	private Inventory decoratedInventory;
	private Command c;

	public CommandDecorator(Inventory inventoryToDecorate) {
		super(inventoryToDecorate);
		decoratedInventory = inventoryToDecorate;
	}

	@Override
	public int addMovie(String movieName, double moviePrice, int movieQty) {
		return decoratedInventory.addMovie(movieName, moviePrice, movieQty);
	}

	@Override
	public void sellMovie(int movieID) {
		c = new SellMovieCommand(decoratedInventory, movieID);
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
	public Memento saveToMemento() {
		return decoratedInventory.saveToMemento();
	}

	@Override
	public void restoreFromMemento(Memento memento) {
		decoratedInventory.restoreFromMemento(memento);
	}

}
