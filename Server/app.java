package Server;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		new Server(false).run();
	}
}
