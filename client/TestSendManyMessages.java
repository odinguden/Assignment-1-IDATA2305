package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class TestSendManyMessages {
	private static char[] operands = new char[]{
		'A', 'S', 'M', 'D', 'P'
	};

	private final static int testAmount = 100;
	private final static int operations = 100;

	public static void main(String[] args) throws IOException {
		Client client = new Client();

		long sumTime = 0;
		int sumRuns = 0;

		for (int t = 0; t < testAmount; t++) {
			long startTime = System.nanoTime();

			Random random = new Random();

			for (int i = 0; i < operations; i++) {
				client.send(""
					+ random.nextInt(100) + " "
					+ random.nextInt(1, 100) + " "
					+ operands[random.nextInt(operands.length - 1)]);
			}
			for (int i = 0; i < operations; i++) {
				client.recive();
			}

			long endTime = System.nanoTime();

			double deltaTime = ((double)(endTime - startTime)) / ((double) 1000000);
			System.out.println("Took " + deltaTime + "ms");

			sumTime += deltaTime;
			sumRuns++;
		}

		System.out.println("Average runtime: " + (sumTime / (double) sumRuns) + "ms");
	}
}
