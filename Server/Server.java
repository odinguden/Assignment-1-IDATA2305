package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

		}
	}

	public void startThreadedServer() {
		new ClientDistributor(this, this.socket).start();
	}

	private class ClientDistributor extends Thread {
		private final Server server;
		private final ServerSocket socket;

		public ClientDistributor(Server server, ServerSocket serverSocket) {
			this.server = server;
			this.socket = serverSocket;
		}

		@Override
		public void run() {
			while (!socket.isClosed()) {
				try {
					Socket client = this.socket.accept();
					this.attemptToAcceptConnection(server, client);
				} catch (IOException e) {
					if (!socket.isClosed()) {
						e.printStackTrace();
					}
				}
			}
		}

		private void attemptToAcceptConnection(Server server, Socket client) {
			try {
				new ClientHandler(server, client).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class ClientHandler extends Thread {
		private final Socket client;
		private final Server server;

		private final BufferedReader input;
		private final PrintWriter output;

		public ClientHandler(Server server, Socket client) throws IOException {
			this.client = client;
			this.server = server;

			this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.output = new PrintWriter(client.getOutputStream());
		}

		@Override
		public void run() {
			while (true) {
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
				}
			}
		}
	}
}
