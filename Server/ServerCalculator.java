package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerCalculator extends Thread {
	private final DatagramSocket socket;
	private final String command;

	private final InetAddress address;
	private final int port;

	public ServerCalculator(DatagramSocket socket, String command, InetAddress returnAddress, int returnPort) {
		this.socket = socket;
		this.command = command;

		this.address = returnAddress;
		this.port = returnPort;
	}

	@Override
	public void run() {
		try {
			int result = calculate();

			DatagramPacket packet = createResultPacket(result);

			socket.send(packet);
		} catch (IOException|NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private int calculate() throws NumberFormatException {
		try {
			sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int result = 0;
		if (this.command == null) return result;

		String[] constituents = command.split(" ");
		if (constituents.length == 3) {
			int number1 = Integer.parseInt(constituents[0]);
			int number2 = Integer.parseInt(constituents[1]);
			char operand = constituents[2].charAt(0);

			result = CalculationUtil.operation(operand, number1, number2);
		}

		return result;
	}

	private DatagramPacket createResultPacket(int result) {
		byte[] message = ("" + result).getBytes();

		return new DatagramPacket(message, message.length, address, port);
	}
}
