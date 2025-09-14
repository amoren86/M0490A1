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
			ProcessBuilder pb = new ProcessBuilder(COMMAND);

			// Redirect standard output to a file
			pb.redirectOutput(new File(OUTPUT_FILE));

			// Start the process
			Process process = pb.start();

			System.out.println(String.join(" ", COMMAND) + " command executed. Output redirected to output.txt");

			// Wait for the process to finish
			int exitCode = process.waitFor();
			System.out.println("Process finished with exit code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}