package inmemorydb;

public class Movie {
	private int id;
	private int qty;
	private double price;
	private String name;
	
	public Movie(int movieID, String movieName, double moviePrice, int movieQty) {
		setID(movieID);
		setName(movieName);
		setPrice(moviePrice);
		setQty(movieQty);
	}

	private int getID() {
		return id;
	}

	private void setID(int id) {
		this.id = id;
	}

	private int getQty() {
		return qty;
	}

	private void setQty(int qty) {
		this.qty = qty;
	}

	private double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
