package procesbuilder.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pipeline {

	// Windows commands (uncomment these lines to test on Windows)
	// private static final String[] COMMAND_1 = {"dir"}; // Windows list files
	// command
	// private static final String[] COMMAND_2 = {"findstr", "txt"};// Windows
	// search command

	// Linux commands
	private static final String[] COMMAND_1 = { "ls" }; // Linux list files command
	private static final String[] COMMAND_2 = { "grep", "txt" };// Linux search command
	private static final String[][] PIPE_LINE = { COMMAND_1, COMMAND_2 };

	public static void main(String[] args) {
		try {
			// Do a pipe line of all commands
			List<ProcessBuilder> processBuilders = new ArrayList<>();
			for (String[] command : PIPE_LINE) {
				processBuilders.add(new ProcessBuilder(command));
			}

			// Start the pipeline
			List<Process> processes = ProcessBuilder.startPipeline(processBuilders);
			for (int i = 0; i < PIPE_LINE.length; i++) {
				System.out.printf("Started process %d: %s%n", processes.get(i).pid(),
						String.join(" ", processBuilders.get(i).command()));

			}

			// Get last process
			Process last = processes.get(processes.size() - 1);

			// Print final output from last process
			last.getInputStream().transferTo(System.out);

			// Wait for processes to finish
			for (Process p : processes) {
				System.out.printf("Process %d exited with code: %d%n", p.pid(), p.waitFor());
			}
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
