package process.example;

import java.io.IOException;

public class ListFiles {
	// Windows commands (uncomment these lines to test on Windows)
	// private static final String LIST_FILES_COMMAND = "dir"; //Windows

	// Linux commands
	private static final String COMMAND = "ls"; // Linux

	public static void main(String[] args) {
		try {
			// Process list files
			Process process = Runtime.getRuntime().exec(COMMAND);

			// Print command and PID
			System.out.printf("Started process %d: %s%n", process.pid(), String.join(" ", COMMAND));

			// Print final output from process
			process.getInputStream().transferTo(System.out);

			// Wait for processes to finish
			System.out.printf("Process %d exited with code: %d%n", process.pid(), process.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
