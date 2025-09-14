package procesbuilder.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Pipes {

	// Windows commands (uncomment these lines to test on Windows)
	// private static final String[] COMMAND_1 = {"dir"}; // Windows list files
	// command
	// private static final String[] COMMAND_2 = {"findstr", "txt"};// Windows
	// search command

	// Linux commands
	private static final String[] COMMAND_1 = { "ls" }; // Linux list files command
	private static final String[] COMMAND_2 = { "grep", "txt" };// Linux search command

	public static void main(String[] args) {
		try {
			// First process: list files
			ProcessBuilder pb1 = new ProcessBuilder(COMMAND_1);
			Process p1 = pb1.start();

			// Second process: filter results
			ProcessBuilder pb2 = new ProcessBuilder(COMMAND_2);
			Process p2 = pb2.start();

			// Connect output of p1 to input of p2
			InputStream inputStream = p1.getInputStream();
			OutputStream outputStream = p2.getOutputStream();

			// Transfer all data from p1 to p2
			inputStream.transferTo(outputStream);
			outputStream.close(); // Important: close output so p2 knows input is finished

			// Read final output from p2
			BufferedReader reader = new BufferedReader(new InputStreamReader(p2.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for both processes to finish
			p1.waitFor();
			p2.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
