package utils;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DebugWindow {

    private static final JFrame frame = new JFrame("Debug Console");
    private static final JTextArea console = new JTextArea();

    private static final String LOG_FILE = "Debug.log";

    private static final int MAX_LOGS = 1000;
    private static final String[] logs = new String[MAX_LOGS];

    private static int nextLog = 0;
    private static int logCount = 0;

    static {
        clearLogFile();

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        console.setEditable(false);
        console.setFont(new Font("Monospaced", Font.PLAIN, 14));

        frame.add(new JScrollPane(console));
        frame.setVisible(true);
    }

    public static void addLog(String message) {

        // Save the log to the file
        saveLogToFile(message);

        // Add the log to the circular buffer
        logs[nextLog] = message;

        nextLog = (nextLog + 1) % MAX_LOGS;

        if (logCount < MAX_LOGS) {
            logCount++;
        }

        // Add the new log to the window
        console.append("\n" + message);

        // Remove the oldest displayed log
        if (logCount == MAX_LOGS) {
            try {
                int end = console.getLineEndOffset(0);
                console.getDocument().remove(0, end);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        console.setCaretPosition(console.getDocument().getLength());
    }

    private static void saveLogToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearLogFile() {
        try (PrintWriter writer = new PrintWriter(LOG_FILE)) {
            // Opening the file without append clears it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}