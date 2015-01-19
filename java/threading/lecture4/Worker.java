package threading.lecture4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstrates how to use Objects to lock out Threads.
 *
 * @author ahorvath
 */
public class Worker {

	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	synchronized void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
			}
			list1.add(random.nextInt(100));
		}
	}

	synchronized void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
			}
			list2.add(random.nextInt(100));
		}
	}

	void process() {
		for (int i = 0; i < 1000; ++i) {
			stageOne();
			stageTwo();
		}
	}

	public void main() throws InterruptedException {
		System.out.println("Starting ...");
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		t1.start();
		t2.start();
		//
		t1.join();
		t2.join();

		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start) + " msecs");
		System.out.println("List1: " + list1.size() + " List2: " + list2.size());
	}
}
