package Server;

import java.io.IOException;

public class app {
	public static void main(String[] args) throws IOException {
		new Server().startSinglethreadServer();
	}
}
