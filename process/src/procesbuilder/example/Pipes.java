package procesbuilder.example;

import java.io.IOException;
import java.io.InputStream;
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
			ProcessBuilder processBuilder1 = new ProcessBuilder(COMMAND_1);

			// Second process: filter results
			ProcessBuilder processBuilder2 = new ProcessBuilder(COMMAND_2);

			// Start both processes
			Process process1 = processBuilder1.start();
			Process process2 = processBuilder2.start();

			// Print commands and PIDs
			System.out.printf("Started process %d: %s%n", process1.pid(), String.join(" ", processBuilder1.command()));
			System.out.printf("Started process %d: %s%n", process2.pid(), String.join(" ", processBuilder2.command()));

			// Connect output of process1 to input of process2
			InputStream inputStream = process1.getInputStream();
			OutputStream outputStream = process2.getOutputStream();

			// Transfer all data from process1 to process2
			inputStream.transferTo(outputStream);
			outputStream.close(); // Important: close output so p2 knows input is finished

			// Print final output from process2
			process2.getInputStream().transferTo(System.out);

			// Wait for processes to finish
			System.out.printf("Process %d exited with code: %d%n", process1.pid(), process1.waitFor());
			System.out.printf("Process %d exited with code: %d%n", process2.pid(), process2.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
