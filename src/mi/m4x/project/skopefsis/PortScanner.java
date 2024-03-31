package mi.m4x.project.skopefsis;

import java.net.Proxy;
import java.net.InetSocketAddress;

public class PortScanner {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 4) {
            System.out.println("Usage: java PortScanner <host> [<startPort> <endPort>] [<protocol>] [<outputFormat>]");
            return;
        }

        String host = args[0];
        int startPort = 1;
        int endPort = PortScannerConstants.MAX_PORT_NUMBER;
        String protocol = "TCP"; // Default protocol
        String outputFormat = "text"; // Default output format
        int numThreads = 5; // Default number of threads
        int rateLimit = 1000; // Default rate limit (1 port per second)
        Proxy proxy = Proxy.NO_PROXY; // Default proxy (no proxy)
        boolean useIPv6 = false; // Default to IPv4

        if (args.length >= 3) {
            try {
                startPort = Integer.parseInt(args[1]);
                endPort = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port numbers provided. Using default port range.");
            }
        }

        if (args.length >= 4) {
            protocol = args[3].toUpperCase(); // Assuming protocol argument is provided in uppercase
        }

        if (args.length >= 5) {
            outputFormat = args[4].toLowerCase(); // Assuming output format argument is provided in lowercase
        }

        // Create a new instance of PortScannerEngine with the provided parameters
        PortScannerEngine scanner = new PortScannerEngine(host, startPort, endPort, protocol, outputFormat, numThreads, rateLimit, proxy, useIPv6);
        scanner.scanPorts();
    }
}
