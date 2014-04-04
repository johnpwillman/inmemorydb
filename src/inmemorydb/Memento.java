package inmemorydb;

import java.io.Serializable;
import java.util.Hashtable;

public class Memento implements Serializable {
	
	private Hashtable<Integer, Movie> state;
	
	public Memento(Hashtable<Integer, Movie> stateToSave) {
		state = stateToSave;
	}
	
	public Hashtable<Integer, Movie> getSavedState() {
		return state;
	}
}
