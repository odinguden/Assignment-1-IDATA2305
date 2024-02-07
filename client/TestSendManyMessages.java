package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestSendManyMessages {
	private static char[] operands = new char[]{
		'A', 'S', 'M', 'D', 'P'
	};

	private final static int testAmount = 300;
	private final static int operations = 100;
	private final static Random random = new Random();

	public static void main(String[] args) throws IOException {

		long sumTime = 0;
		int sumRuns = 0;

		for (int t = 0; t < testAmount; t++) {
			ArrayList<Thread> threads = new ArrayList<>();

			for (int i = 0; i < operations; i++) {
				Thread thread = new Thread(() -> {
					Client client = new Client();
					try {
						client.send((""
						+ random.nextInt(100) + " "
						+ random.nextInt(1, 100) + " "
						+ operands[random.nextInt(operands.length - 1)]));

						client.recive();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							client.stopSocketCommunication();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});

				threads.add(thread);
			}

			long startTime = System.nanoTime();

			threads.stream().forEach(Thread::start);
			threads.stream().forEach(thread -> {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

			long endTime = System.nanoTime();

			double deltaTime = ((double)(endTime - startTime)) / ((double) 1000000);
			System.out.println("Took " + deltaTime + "ms");
			if (t != 0) {
				sumTime += deltaTime;
				sumRuns++;
			}
		}

		System.out.println("Average runtime: " + (sumTime / (double) sumRuns) + "ms");
	}
}