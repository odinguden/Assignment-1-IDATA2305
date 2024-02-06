package client;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket socket;

	public Client(String name) {
		try {
			socket = new Socket("localhost", 8080);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(String message) {

	}

	public void stop() throws IOException {
		socket.close();
	}
}
