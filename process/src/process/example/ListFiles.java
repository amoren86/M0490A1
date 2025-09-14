package process.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListFiles {
	// Windows commands (uncomment these lines to test on Windows)
	// private static final String LIST_FILES_COMMAND = "dir"; //Windows

	// Linux commands
	private static final String COMMAND = "ls"; // Linux

	public static void main(String[] args) {
		try {
			// Run system command to list files
			Process process = Runtime.getRuntime().exec(COMMAND);

			System.out.println(COMMAND + " command output:");

			// Get an input stream to read the standard output of the process
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
