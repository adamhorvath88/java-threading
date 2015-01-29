package threading.lecture5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

	final int id;

	Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting: " + id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
		}
		System.out.println("Completed: " + id);
	}
}

public class App {

	public static void main() throws InterruptedException {
		final int threadPoolSize = 2;
		// Creates a pool with two threads.
		ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

		for (int i = 0; i < 5; ++i) {
			// Submitting new tasks
			executor.submit(new Processor((i)));
		}
		// This will not shutdown immediatelly but will wait for the executors
		executor.shutdown();

		System.out.println("All tasks submitted.");
		// A time limit for the tasks to finish
		executor.awaitTermination(1, TimeUnit.DAYS);

		System.out.println("All tasks completed.");
	}
}
