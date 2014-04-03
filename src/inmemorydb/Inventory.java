package inmemorydb;

import java.util.Hashtable;
import java.util.Iterator;

public class Inventory {
	private int uniqueIDSequence;
	
	private Hashtable<Integer, Movie> movies;
	
	public Inventory() {
		uniqueIDSequence = 0;
		movies = new Hashtable<Integer, Movie>();
	}
	
	public int addMovie(String movieName, double moviePrice, int movieQty) {
		int uniqueID = getNewUniqueID();
		Movie m = new Movie(uniqueID, movieName, moviePrice, movieQty);
		
		movies.put(uniqueID, m);
		
		return uniqueID;
	}
	
	public String getMovieName(int uid) {
		return movies.get(uid).getName();
	}
	
	public void sellMovie(int uid) {
		Movie m = movies.get(uid);
		
		m.setQty(m.getQty() - 1);
	}
	
	public int getMovieQty(int uid) {
		return movies.get(uid).getQty();
	}
	
	public int getMovieQty(String movieName) {
		Iterator<Movie> allMovies = movies.values().iterator();
		Movie m;
		int qty = 0;
		
		while (allMovies.hasNext()) {
			m = allMovies.next();
			
			if (m.getName().equals(movieName)) {
				qty = m.getQty();
			}
		}
		
		return qty;
	}
	
	public void addMovieQty(int uid, int qtyToAdd) {
		Movie m = movies.get(uid);
		
		m.setQty(m.getQty() + qtyToAdd);
	}
	
	public void setMoviePrice(int uid, double newPrice) {
		movies.get(uid).setPrice(newPrice);
	}
	
	public double getMoviePrice(int uid) {
		return movies.get(uid).getPrice();
	}
	
	public double getMoviePrice(String movieName) {
		Iterator<Movie> allMovies = movies.values().iterator();
		Movie m;
		double price = 0.00;
		
		while (allMovies.hasNext()) {
			m = allMovies.next();
			
			if (m.getName().equals(movieName)) {
				price = m.getPrice();
			}
		}
		
		return price;
	}
	
	private int getNewUniqueID() {
		return ++uniqueIDSequence;
	}
	
	private void setUniqueIDSequence(int i) {
		uniqueIDSequence = i;
	}
	
}
