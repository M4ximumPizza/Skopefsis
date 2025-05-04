package mi.m4x.project.skopefsis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PortScanner {
    private static final Logger LOGGER = Logger.getLogger(PortScanner.class.getName());

    public static void main(String[] args) {
        configureLogging();
        Properties config = loadConfig();

        if (args.length < 1) {
            LOGGER.log(Level.SEVERE, "Usage: java PortScanner <host>");
            return;
        }

        String host = args[0];
        int startPort = Integer.parseInt(config.getProperty("startPort", "1"));
        int endPort = Integer.parseInt(config.getProperty("endPort", "65535"));
        String protocol = config.getProperty("protocol", "TCP").toUpperCase();
        int numThreads = Integer.parseInt(config.getProperty("numThreads", "10"));
        boolean useIPv6 = Boolean.parseBoolean(config.getProperty("useIPv6", "false"));

        PortScannerEngine scanner = new PortScannerEngine(host, startPort, endPort, protocol, numThreads, useIPv6);
        scanner.scanPorts();
    }

    private static void configureLogging() {
        try (InputStream inputStream = PortScanner.class.getResourceAsStream("/logging.properties")) {
            if (inputStream != null) {
                LogManager.getLogManager().readConfiguration(inputStream);
            } else {
                LOGGER.log(Level.WARNING, "Logging configuration file not found. Using default logging configuration.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error configuring logging", e);
        }
    }

    private static Properties loadConfig() {
        Properties properties = new Properties();
        try (InputStream inputStream = PortScanner.class.getResourceAsStream("/config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                LOGGER.log(Level.WARNING, "Configuration file not found. Using default values.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading configuration", e);
        }
        return properties;
    }
}