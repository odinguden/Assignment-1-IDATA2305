package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliInterface {
	private Client client;
	private boolean isOn;

	public CliInterface() {
		client = new Client("Odin");
		client.start();
		isOn = true;
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));

		while (isOn) {
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (line != null) {
				try {
					client.send(line);
				} catch (IOException e) {
					System.out.println("Could not send");
					e.printStackTrace();
				}
			}
		}
	}
}
