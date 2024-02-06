package Server;

import java.io.BufferedReader;
import java.net.Socket;

public class ClientHandler extends Thread{
	private boolean isRunning;
	private Socket socket;


	public ClientHandler (Socket socket) {
		isRunning = true;
		this.socket = socket;
		
	}
}
