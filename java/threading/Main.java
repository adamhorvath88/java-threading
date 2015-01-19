package threading;

import java.util.logging.Level;
import java.util.logging.Logger;
import threading.lecture4.Worker;

/**
 * .
 * @author ahorvath
 */
public class Main {

	public static void main(String[] args) {
		try {
			Worker worker = new Worker();
			worker.main();
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
