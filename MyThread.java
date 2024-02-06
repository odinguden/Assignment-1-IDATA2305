

public class MyThread extends Thread {

	public MyThread(char c) {
		System.out.println("Started thread: " + c);
		setName("thread" + c);
	}


	@Override
	public void run() {
		int i = 0;

		while (i <= 20) {
			System.out.println(i);
			i++;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Fuck");
				e.printStackTrace();
			}
		}
	}
}
