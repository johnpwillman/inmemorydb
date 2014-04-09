package inmemorydb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Caretaker {
	
	public Caretaker() {
		
	}
	
	public void saveMemento(Memento m) {
		try {
			FileOutputStream fileOut =
			new FileOutputStream("mementos");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m);
			out.close();
			fileOut.close();
			System.out.printf("Mementos saved to 'mementos' file");
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public Memento getMemento() {
		Memento m = null;
		try {
			FileInputStream fileIn = new FileInputStream("mementos");
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
