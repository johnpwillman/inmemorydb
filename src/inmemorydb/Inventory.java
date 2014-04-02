package inmemorydb;

public class Inventory {
	private int uniqueIDSequence;
	
	public Inventory() {
		uniqueIDSequence = 0;
	}
	
	public int addMovie(String movieName, double moviePrice, int movieQty) {
		int uniqueID = getNewUniqueID();
		
		new Movie(uniqueID, movieName, moviePrice, movieQty);
		
		return uniqueID;
	}
	
	private int getNewUniqueID() {
		return ++uniqueIDSequence;
	}
	
	private void setUniqueIDSequence(int i) {
		uniqueIDSequence = i;
	}
	
}
