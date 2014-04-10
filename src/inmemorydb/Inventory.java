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
	
	//Sequence for generating unique IDs for Movies
	private int uniqueIDSequence;
	
	//Movie database object
	private Hashtable<Integer, Movie> movies;
	
	//Caretaker object to handle Memento de/serialization
	private Caretaker careTaker = new Caretaker();
	
	public Inventory() {
		uniqueIDSequence = 0;
		movies = new Hashtable<Integer, Movie>();
	}
	
	/**
	 * Add a movie to the database with a name, a price, and a quantity.
	 */
	public void addMovie(String movieName, double moviePrice, int movieQty) {
		int uniqueID = getNewUniqueID();
		Movie m = new Movie(uniqueID, movieName, moviePrice, movieQty);
		
		movies.put(uniqueID, m);
	}
	
	/**
	 * Retrieve the name of the movie that matches a unique integer ID
	 */
	public String getMovieName(int movieID) {
		return movies.get(movieID).getName();
	}
	
	/**
	 * Decrement the quantity of the movie matching a unique integer ID
	 * by one.
	 */
	public void sellMovie(int movieID) {
		Movie m = movies.get(movieID);
		
		m.setQty(m.getQty() - 1);
	}
	
	/**
	 * Retrieve the quantity of the movie matching a unique integer ID
	 */
	public int getMovieQty(int movieID) {
		return movies.get(movieID).getQty();
	}
	
	/**
	 * Retrieve the quantity of the movie with the given name
	 */
	public int getMovieQty(String movieName) {
		Iterator<Movie> allMovies = movies.values().iterator();
		Movie m;
		int qty = 0;
		
		//Use hash table iterator to find movie matching movieName
		while (allMovies.hasNext()) {
			m = allMovies.next();
			
			if (m.getName().equals(movieName)) {
				qty = m.getQty();
			}
		}
		
		return qty;
	}
	
	/**
	 * Increment the quantity of the movie matching a unique Integer ID
	 * by a given amount.
	 */
	public void addMovieQty(int movieID, int qtyToAdd) {
		Movie m = movies.get(movieID);
		
		m.setQty(m.getQty() + qtyToAdd);
	}
	
	/**
	 * Set the price of the movie matching a unique Integer ID to the
	 * specified amount.
	 */
	public void setMoviePrice(int movieID, double newPrice) {
		movies.get(movieID).setPrice(newPrice);
	}
	
	/**
	 * Retrieve the price of the movie matching a unique Integer ID.
	 */
	public double getMoviePrice(int movieID) {
		return movies.get(movieID).getPrice();
	}
	
	/**
	 * Retrieve the price of the movie with the given name.
	 */
	public double getMoviePrice(String movieName) {
		Iterator<Movie> allMovies = movies.values().iterator();
		Movie m;
		double price = 0.00;
		
		//Use has table iterator to find Movie matching movieName
		while (allMovies.hasNext()) {
			m = allMovies.next();
			
			if (m.getName().equals(movieName)) {
				price = m.getPrice();
			}
		}
		
		return price;
	}
	
	/**
	 * Creates a memento object with a copy of the Movie database.
	 * (For this project, no memento is returned, but is instead
	 * immediately forwarded to the Caretaker for serialization)
	 */
	public void saveToMemento() {
		careTaker.saveMemento(new Memento(movies));
	}
	
	/**
	 * Retrieves the last saved memento from the Caretaker and
	 * rebuilds the database from it.
	 */
	public void restoreFromMemento() {
		movies = careTaker.getMemento().getSavedState();
		//movies = memento.getSavedState();
		
		setUniqueIDSequence(maxSequenceID());
	}
	
	/**
	 * Retrieve the current value of the sequence which is also
	 * the last ID assigned.
	 */
	public int getCurrentUniqueIDSequence() {
		return uniqueIDSequence;
	}
	
	/**
	 * Determines what the current value of the sequence should be.
	 * (Useful for when the database is restored from memento)
	 * @return
	 */
	private int maxSequenceID() {
		Enumeration<Integer> keys = movies.keys();
		int maxSequenceID = 0;
		int holdValue;
		
		//Use hash table Enumeration to find largest sequence ID from memento
		while(keys.hasMoreElements()) {
			holdValue = keys.nextElement();
			
			maxSequenceID = holdValue > maxSequenceID ? holdValue : maxSequenceID;
		}
		
		return maxSequenceID;
	}
	
	/**
	 * Pre-increments the Sequence and returns its new value for use
	 * in assigning a movie to the database.
	 * @return
	 */
	private int getNewUniqueID() {
		return ++uniqueIDSequence;
	}
	
	private void setUniqueIDSequence(int newSequenceID) {
		uniqueIDSequence = newSequenceID;
	}
	
}
