package process.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListFiles {
	// private static final String LIST_FILES = "dir"; //Windows
	private static final String LIST_FILES = "ls"; // Linux

	public static void main(String[] args) {
		try {
			// Run system command to list files
			Process process = Runtime.getRuntime().exec(LIST_FILES);

			// Get an input stream to read the standard output of the process
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			System.out.println(LIST_FILES + " command output:");

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
