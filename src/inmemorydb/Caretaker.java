package inmemorydb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Caretaker {
	
	private File mementoFile = new File("mementos");
	
	public Caretaker() {
		
	}
	
	public void saveMemento(Memento m) {
		try {
			FileOutputStream fileOut =
			new FileOutputStream(mementoFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m);
			out.close();
			fileOut.close();
			System.out.println("Mementos saved to '" + mementoFile.getPath() + "' file");
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public Memento getMemento() {
		Memento m = null;
		try {
			FileInputStream fileIn = new FileInputStream(mementoFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			m = (Memento) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i) {
			i.printStackTrace();
		} catch(ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
		}
		return m;
	}
}
