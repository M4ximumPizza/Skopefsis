package mi.m4x.project.skopefsis;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PortScanner {
    private static final Logger LOGGER = Logger.getLogger(PortScanner.class.getName());

    private boolean useIPv6;;

    public static void main(String[] args) {
        configureLogging();

        boolean useIPv6 = false;

        if (args.length < 3 || args.length > 7) {
            LOGGER.log(Level.SEVERE, "Invalid number of arguments. Usage: java PortScanner <host> [<startPort> <endPort>] [<protocol>] [<outputFormat>] [<useUDP>] [<numThreads>]");
            return;
        }

        int argIndex = 0;
        String host = args[argIndex++];
        int startPort = 1;
        int endPort = PortScannerConstants.MAX_PORT_NUMBER;
        String protocol = "TCP"; // Default protocol
        String outputFormat = "text"; // Default output format
        boolean useUDP = false; // Default to TCP
        int numThreads = 5; // Default number of threads

        try {
            if (args.length >= 3) {
                startPort = Integer.parseInt(args[argIndex++]);
                endPort = Integer.parseInt(args[argIndex++]);
            }

            if (args.length >= 4) {
                protocol = args[argIndex++].toUpperCase();
            }

            if (args.length >= 5) {
                outputFormat = args[argIndex++].toLowerCase();
            }

            if (args.length >= 6) {
                useUDP = Boolean.parseBoolean(args[argIndex++]);
            }

            if (args.length >= 7) {
                numThreads = Integer.parseInt(args[argIndex++]);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid number format provided.");
            return;
        }

        // Create a new instance of PortScannerEngine with the provided parameters
        PortScannerEngine scanner = new PortScannerEngine(host, startPort, endPort, protocol, outputFormat, numThreads, useUDP, useIPv6);
        scanner.scanPorts();
    }

    private static void configureLogging() {
        try {
            InputStream inputStream = PortScanner.class.getResourceAsStream("resources/logging.properties");
            if (inputStream != null) {
                LogManager.getLogManager().readConfiguration(inputStream);
            } else {
                System.err.println("Logging configuration file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
