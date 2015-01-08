package threading.lecture2;

import java.util.Scanner;

class Processor extends Thread {

	private volatile boolean running = true;

	@Override
	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void shutdown() {
		running = false;
	}
}

/**
 * Basic thread synchronization.
 *
 * @author ahorvath
 */
public class Executor2 {

	public static void execute() {
		Processor proc1 = new Processor();
		proc1.start();

		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		proc1.shutdown();
	}
}
