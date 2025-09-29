package runtime.example;

import java.io.IOException;

public class LaunchProcess {
	// private static final String PROGRAM = "notepad"; // Windows
	// private static final String PROGRAM = "gedit"; // Linux
	private static final String PROGRAM = "pluma"; // Linux

	public static void main(String[] args) {
		try {
			// Process editor
			Process process = Runtime.getRuntime().exec(PROGRAM);

			// Wait until the external process finishes
			process.waitFor();

			// Notify that the program has been closed
			System.out.println(PROGRAM + " has been closed.");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
			System.exit(2);
		}
		System.exit(0);
	}
}
