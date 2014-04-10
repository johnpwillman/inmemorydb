package inmemorydb;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Inventory is an interface to the client program which allows
 * for management of the Movie Database. It handles adding,
 * selling, and modifying of movie records as well as backup
 * of the database itself through the Memento Pattern.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class Inventory extends AbstractInventory {
	private int uniqueIDSequence;
	
	private Hashtable<Integer, Movie> movies;
	
	private Caretaker careTaker = new Caretaker();
	
	public Inventory() {
		uniqueIDSequence = 0;
		movies = new Hashtable<Integer, Movie>();
	}
	
	public void addMovie(String movieName, double moviePrice, int movieQty) {
		int uniqueID = getNewUniqueID();
		Movie m = new Movie(uniqueID, movieName, moviePrice, movieQty);
		
		movies.put(uniqueID, m);
	}
	
	public String getMovieName(int movieID) {
		return movies.get(movieID).getName();
	}
	
	public void sellMovie(int movieID) {
		Movie m = movies.get(movieID);
		
		m.setQty(m.getQty() - 1);
	}
	
	public int getMovieQty(int movieID) {
		return movies.get(movieID).getQty();
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
	
	public void addMovieQty(int movieID, int qtyToAdd) {
		Movie m = movies.get(movieID);
		
		m.setQty(m.getQty() + qtyToAdd);
	}
	
	public void setMoviePrice(int movieID, double newPrice) {
		movies.get(movieID).setPrice(newPrice);
	}
	
	public double getMoviePrice(int movieID) {
		return movies.get(movieID).getPrice();
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
	
	///////////////////////////////////////////////////////////
	// Memento Code
	///////////////////////////////////////////////////////////
	public void saveToMemento() {
		careTaker.saveMemento(new Memento(movies));
	}
	
	public void restoreFromMemento() {
		movies = careTaker.getMemento().getSavedState();
		//movies = memento.getSavedState();
		
		setUniqueIDSequence(maxSequenceID());
	}
	
	public int getCurrentUniqueIDSequence() {
		return uniqueIDSequence;
	}
	
	private int maxSequenceID() {
		Enumeration<Integer> keys = movies.keys();
		int maxSequenceID = 0;
		int holdValue;
		
		while(keys.hasMoreElements()) {
			holdValue = keys.nextElement();
			
			maxSequenceID = holdValue > maxSequenceID ? holdValue : maxSequenceID;
		}
		
		return maxSequenceID;
	}
	
	private int getNewUniqueID() {
		return ++uniqueIDSequence;
	}
	
	private void setUniqueIDSequence(int newSequenceID) {
		uniqueIDSequence = newSequenceID;
	}
	
}
