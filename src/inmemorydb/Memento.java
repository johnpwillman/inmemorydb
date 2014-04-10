package inmemorydb;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Memento is a serializable class which contains the database of an
 * Inventory class for "storage to" or "restoration from" a file.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class Memento implements Serializable {
	
	//Copy of the Movie Database at time of memento creation
	private Hashtable<Integer, Movie> state;
	
	public Memento(Hashtable<Integer, Movie> stateToSave) {
		state = stateToSave;
	}
	
	public Hashtable<Integer, Movie> getSavedState() {
		return state;
	}
}
