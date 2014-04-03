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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
