package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket socket;

	public Server() throws IOException {
		this.socket = new ServerSocket(8080);
	}

	public void startSinglethreadServer() {
		while (!socket.isClosed()) {
			try {
				Socket client = this.socket.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter output = new PrintWriter(client.getOutputStream());


				int result = 0;
				try {
					String message = input.readLine();
					String[] constituents = message.split(" ");
					if (constituents.length == 3) {
						try {
							int number1 = Integer.parseInt(constituents[0]);
							int number2 = Integer.parseInt(constituents[1]);
							char operand = constituents[2].charAt(0);

							result = CalculationUtil.operation(operand, number1, number2);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					output.println(result);
					input.close();
					output.close();
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void startThreadedServer() {
		new ClientDistributor(this.socket).start();
	}

	private class ClientDistributor extends Thread {
		private final ServerSocket socket;

		public ClientDistributor(ServerSocket serverSocket) {
			this.socket = serverSocket;
		}

		@Override
		public void run() {
			while (!socket.isClosed()) {
				try {
					Socket client = this.socket.accept();
					this.attemptToAcceptConnection(client);
				} catch (IOException e) {
					if (!socket.isClosed()) {
						e.printStackTrace();
					}
				}
			}
		}

		private void attemptToAcceptConnection(Socket client) {
			try {
				new ClientHandler(client).start();
				System.out.println("Client connected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class ClientHandler extends Thread {
		private final BufferedReader input;
		private final PrintWriter output;

		public ClientHandler(Socket client) throws IOException {

			this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.output = new PrintWriter(client.getOutputStream(), true);
		}

		@Override
		public void run() {
			while (true) {
				int result = 0;
				try {
					System.out.println("Awaiting message");
					String message = input.readLine();
					System.out.println("Got message: " + message);
					String[] constituents = message.split(" ");
					if (constituents.length == 3) {
						try {
							int number1 = Integer.parseInt(constituents[0]);
							int number2 = Integer.parseInt(constituents[1]);
							char operand = constituents[2].charAt(0);

							result = CalculationUtil.operation(operand, number1, number2);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					output.println(result);
				}
			}
		}
	}
}
