public class App {
	public static void main(String[] args) {
		new MyThread('A').start();
		new MyThread('B').start();
	}
}