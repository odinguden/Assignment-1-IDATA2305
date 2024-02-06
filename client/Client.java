package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is the client duh
 */
public class Client extends Thread {
	private boolean isConnected;
	private Socket socket;
	private PrintWriter output;
	private BufferedReader reader;



	public Client(String name) {
		try {
			socket = new Socket("localhost", 8080);
			output = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(
				new InputStreamReader(
					socket.getInputStream()
				)
			);
			isConnected = true;
		} catch (UnknownHostException e) {
			System.out.println("Could not find Host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An ioException occurred");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (isConnected) {
			String messageIn = null;
			try {
				messageIn = reader.readLine();
			} catch (IOException e) {
				System.out.println("Could not read message");
				e.printStackTrace();
			}
			if (messageIn != null) {
				System.out.println(messageIn);
			}
		}
	}

	public void send(String message) throws IOException {
		System.out.println("Sending message " + message);
		output.println(message);
		System.out.println("Message sent");
	}

	public void stopSocketCommunication() throws IOException {
		socket.close();
	}
}
