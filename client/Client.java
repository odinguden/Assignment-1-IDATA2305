package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * This class is the client duh
 */
public class Client extends Thread {
	private boolean isConnected;
	private DatagramSocket socket;
	private InetAddress address;
	private PrintWriter output;
	private BufferedReader reader;
	private byte[] buf;


	public Client(String name) {
		try {
			socket = new DatagramSocket();
			address = InetAddress.getLocalHost();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void send(String msg) throws IOException {
        buf = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 8080);

        socket.send(packet);
    }

	public String recive() throws IOException {
		DatagramPacket packet;
		packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
          packet.getData(), 0, packet.getLength());
        return received;
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

	public void stopSocketCommunication() throws IOException {
		socket.close();
	}
}
