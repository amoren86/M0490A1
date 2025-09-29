package procesbuilder.example;

import java.io.File;
import java.io.IOException;

public class RedirectOutput {
	private static final String OUTPUT_FILE = "output.txt";

	// Windows commands (uncomment these lines to test on Windows)
	// private static final String COMMAND = {"dir", "/n"}; // Windows list files
	// with long listing format

	// Linux commands
	private static final String[] COMMAND = { "ls", "-l" }; // Linux list files with long listing format

	public static void main(String[] args) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(COMMAND);

			// Redirect standard output to a file
			processBuilder.redirectOutput(new File(OUTPUT_FILE));
			System.out.println("Standard output will be redirected to " + OUTPUT_FILE);

			// Start the process
			Process process = processBuilder.start();
			System.out.printf("Started process %d: %s%n", process.pid(), String.join(" ", processBuilder.command()));

			// Wait for the process to finish
			System.out.printf("Process %d exited with code: %d%n", process.pid(), process.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}