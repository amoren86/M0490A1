package procesbuilder.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ListFiles {
	// private static final String LIST_FILES = "dir"; //Windows
	private static final String LIST_FILES = "ls"; // Linux
	private static final String LONG_LISTING_OPTION = "-l"; // Long listing format

	public static void main(String[] args) {
		try {
			List<String> command = Arrays.asList(LIST_FILES, LONG_LISTING_OPTION);
			ProcessBuilder pb = new ProcessBuilder(command);

			// Start the process
			Process process = pb.start();

			System.out.println(String.join(" ", command) + " command output:");

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