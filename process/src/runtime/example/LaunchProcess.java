package runtime.example;

import java.io.IOException;

public class LaunchProcess {
	//private static final String PROGRAM = "notepad"; // Windows
	//private static final String PROGRAM = "gedit"; // Linux
	private static final String PROGRAM = "pluma"; // Linux
	
	public static void main(String[] args) {
		try {
			// Launch an external process
			Process process = Runtime.getRuntime().exec(PROGRAM);

			// Wait until the external process finishes
			process.waitFor();

			System.out.println(PROGRAM +" has been closed.");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(2);
		}
		System.exit(0);
	}
}
