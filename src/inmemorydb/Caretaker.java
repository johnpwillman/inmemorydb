package inmemorydb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Caretaker is a member of the Memento Pattern implementation in this
 * project. It's job is to serialize Memento objects to disk and to 
 * deserialize them from the disk.
 * 
 * @author John Willman
 * RedID: 809362691
 *
 */
public class Caretaker {
	
	private File mementoFile = new File("mementos");
	private File tempMementoFile = new File("tempMementos");
	
	public Caretaker() {
		
	}
	
	/**
	 * Serializes a memento to disk for later retrieval. Only one memento
	 * is stored at a time.
	 * @param m
	 */
	public void saveMemento(Memento m) {
		try {
			FileOutputStream fileOut =
			new FileOutputStream(tempMementoFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m);
			out.close();
			fileOut.close();
			
			//Rename tempfile to real Memento file if successfully saved 
			mementoFile.delete();
			tempMementoFile.renameTo(mementoFile);
			
			System.out.println(
					"Memento saved to '" + mementoFile.getPath() + "' file"
					);
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the latest memento from disk via deserialization.
	 * @return
	 */
	public Memento getMemento() {
		Memento m = null;
		try {
			FileInputStream fileIn = new FileInputStream(mementoFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			m = (Memento) in.readObject();
			in.close();
			fileIn.close();
		} catch(FileNotFoundException f) {
			System.out.println("No mementos to restore.");
		} catch(IOException i) {
			i.printStackTrace();
		} catch(ClassNotFoundException c) {
			System.out.println("Memento class not found");
			c.printStackTrace();
		}
		return m;
	}
}
