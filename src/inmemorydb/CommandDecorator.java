package inmemorydb;

public class CommandDecorator extends InventoryDecorator {
	
	private Inventory decoratedInventory;
	private Command c;

	public CommandDecorator(Inventory inventoryToDecorate) {
		super(inventoryToDecorate);
		decoratedInventory = inventoryToDecorate;
	}

	@Override
	public void addMovie(String movieName, double moviePrice, int movieQty) {
		c = new AddMovieCommand(decoratedInventory, movieName, moviePrice, movieQty);
		c.execute();
	}

	@Override
	public void sellMovie(int movieID) {
		c = new SellMovieCommand(decoratedInventory, movieID);
		c.execute();
	}

	@Override
	public void addMovieQty(int movieID, int qtyToAdd) {
		c = new AddMovieQtyCommand(decoratedInventory, movieID, qtyToAdd);
		c.execute();
	}

	@Override
	public void setMoviePrice(int movieID, double newPrice) {
		c = new SetMoviePriceCommand(decoratedInventory, movieID, newPrice);
		c.execute();
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
