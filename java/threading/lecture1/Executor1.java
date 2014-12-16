package threading.lecture1;

import static java.lang.Thread.sleep;

class Threader extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);
			try {
				sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

class Runner implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

/**
 * .
 * @author ahorvath
 */
public class Executor1 {

	public static void execute() {
		Threader threader1 = new Threader();
		threader1.start();

		Threader threader2 = new Threader();
		threader2.start();

		Thread runner1 = new Thread(new Runner());
		Thread runner2 = new Thread(new Runner());

		runner1.start();
		runner2.start();

		Thread inliner = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Beep");
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		inliner.start();
	}
}
