package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
	private final DatagramSocket serverSocket;
	private byte[] buffer = new byte[1024];

	private final boolean isSingleThreaded;

	public Server(boolean isSingleThreaded) throws SocketException {
		this.isSingleThreaded = isSingleThreaded;
		serverSocket = new DatagramSocket(8080);
	}

	public void run() throws IOException {
		boolean running = true;

		while (running) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			serverSocket.receive(packet);

			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			packet = new DatagramPacket(buffer, buffer.length, address, port);

			String received = new String(packet.getData(), 0, packet.getLength());

			if (received.equalsIgnoreCase("end")) {
				running = false;
				continue;
			}

			if (isSingleThreaded) {
				new ServerCalculator(serverSocket, received, address, port).run();
			} else {
				new ServerCalculator(serverSocket, received, address, port).start();
			}
		}
	}
}