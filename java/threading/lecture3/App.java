package threading.lecture3;

/**
 * The synchronized keyword.
 *
 * Volatile keyword might help a bit but it won't fix the problem.
 * Volatile-count is always greater or equal than normal-count but still
 * occasionally fails to reach the goal count.
 *
 * @author ahorvath
 */
public class App {

	private int countNormal;
	private volatile int countVolatile;
	private int countSynchronized;

	private final Runnable logic = new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i != 10000; i++) {
				countNormal++;
				countVolatile++;
				increment();
			}
		}
	};

	private synchronized void increment() {
		countSynchronized++;
	}

	public void doWork() {
		Thread t1 = new Thread(logic);
		Thread t2 = new Thread(logic);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException ex) {
			System.out.println("Woops");
		}

		System.out.println("Count is: " + countNormal);
		System.out.println("Count volatile is: " + countVolatile);
		System.out.println("Count synchronized is: " + countSynchronized);
	}
}
