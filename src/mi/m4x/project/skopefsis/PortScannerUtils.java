package mi.m4x.project.skopefsis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class PortScannerUtils {
    public static void performBannerGrabbing(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            // Get input stream from the socket
            InputStream inputStream = socket.getInputStream();
            // Create a BufferedReader to read lines from the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // Read the first line from the input stream (usually the banner)
            String banner = reader.readLine();
            // Print the banner
            System.out.println("Banner for Port " + port + ": " + banner);
            // Perform service detection
            performServiceDetection(banner);
        } catch (IOException e) {
            // Error occurred while performing banner grabbing
            System.out.println("Error grabbing banner for port " + port + ": " + e.getMessage());
        }
    }

    private static void performServiceDetection(String banner) {
        // Implement service detection logic based on the banner information
        if (banner != null) {
            if (banner.contains("HTTP")) {
                System.out.println("Service detected: HTTP");
            } else if (banner.contains("SSH")) {
                System.out.println("Service detected: SSH");
            } else if (banner.contains("FTP")) {
                System.out.println("Service detected: FTP");
            } else {
                System.out.println("Service detected: Unknown");
            }
        } else {
            System.out.println("Service detection failed: No banner available");
        }
    }

    public static void performAdditionalTasks(String host) {
        performReverseDNSLookup(host);
        // Implement additional tasks such as vulnerability assessment, etc.
    }

    private static void performReverseDNSLookup(String host) {
        try {
            InetAddress ipAddress = InetAddress.getByName(host);
            String hostname = ipAddress.getHostName();
            System.out.println("Reverse DNS lookup result: " + hostname);
        } catch (IOException e) {
            System.out.println("Unable to perform reverse DNS lookup: " + e.getMessage());
        }
    }
}

