package procesbuilder.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListFiles {
	// Windows commands (uncomment these lines to test on Windows)
	// private static final String COMMAND = {"dir", "/n"}; // Windows list files
	// with long listing format

	private static final String[] COMMAND = { "ls", "-l" }; // Linux list files with long listing format

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder(COMMAND);

			// Start the process
			Process process = pb.start();

			System.out.println(String.join(" ", COMMAND) + " command output:");

			// Read the standard output of the process
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for the process to finish
			int exitCode = process.waitFor();
			System.out.println("Process finished with exit code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}