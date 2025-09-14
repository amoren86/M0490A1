package procesbuilder.example;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RedirectOutput {
	private static final String OUTPUT_FILE = "output.txt";

	// private static final String LIST_FILES = "dir"; //Windows
	private static final String LIST_FILES = "ls"; // Linux
	private static final String LONG_LISTING_OPTION = "-l"; // Long listing format

	public static void main(String[] args) {
		try {
			List<String> command = Arrays.asList(LIST_FILES, LONG_LISTING_OPTION);
			ProcessBuilder pb = new ProcessBuilder(command);

			// Redirect standard output to a file
			pb.redirectOutput(new File(OUTPUT_FILE));

			// Start the process
			Process process = pb.start();

			System.out.println(String.join(" ", command) + " command executed. Output redirected to output.txt");

			// Wait for the process to finish
			int exitCode = process.waitFor();
			System.out.println("Process finished with exit code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}