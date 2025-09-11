package process;

public class ProcessExample {
    public static void main(String[] args) {
        try {
            // Start a new process (Linux command "ls")
            Process process = Runtime.getRuntime().exec("ls");
            
            // Read process output
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(process.getInputStream())
            );
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Output: " + line);
            }
            
            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Process finished with exit code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
