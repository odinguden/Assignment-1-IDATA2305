package client;

import java.io.BufferedReader;
import java.io.IOException;

public class TestSendManyMessages {
	public static void main(String[] args) throws IOException {
		Client client1 = new Client("a");
		Client client2 = new Client("b");
		Client client3 = new Client("c");

		BufferedReader reader1 = client1.getReader();
		BufferedReader reader2 = client2.getReader();
		BufferedReader reader3 = client3.getReader();

		long startTime = System.currentTimeMillis();

		client1.send("30 30 A");
		client2.send("50 30 S");
		client3.send("20 40 M");

		System.out.println(reader1.readLine());
		client1.stopSocketCommunication();
		System.out.println(reader2.readLine());
		client2.stopSocketCommunication();
		System.out.println(reader3.readLine());
		client3.stopSocketCommunication();

		long endTime = System.currentTimeMillis();

		long deltaTime = endTime - startTime;
		System.out.println("Took " + deltaTime + "ms");
	}
}
