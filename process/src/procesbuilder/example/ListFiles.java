package procesbuilder.example;

import java.io.IOException;

public class ListFiles {
	// Windows commands (uncomment these lines to test on Windows)
	// private static final String COMMAND = {"dir", "/n"}; // Windows list files
	// with long listing format

	private static final String[] COMMAND = { "ls", "-l" }; // Linux list files with long listing format

	public static void main(String[] args) {
		try {
			// Process list files
			ProcessBuilder processBuilder = new ProcessBuilder(COMMAND);

			// Start the process
			Process process = processBuilder.start();

			// Print command and PID
			System.out.printf("Started process %d: %s%n", process.pid(), String.join(" ", processBuilder.command()));

			// Print final output from process
			process.getInputStream().transferTo(System.out);

			// Wait for processes to finish
			System.out.printf("Process %d exited with code: %d%n", process.pid(), process.waitFor());
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