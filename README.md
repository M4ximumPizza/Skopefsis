# Skopefsis - Multi-threaded Port Scanning Tool
Greek for "Scanner" or "Scout"

Skopefsis is a Java application designed to scan ports on a specified host using multiple threads for faster execution. It provides a simple command-line interface for scanning ports and performing additional tasks such as banner grabbing and reverse DNS lookup.

## Features
- **Multi-threaded Port Scanning**: Skopefsis utilizes multiple threads for parallel scanning of ports, improving speed and efficiency.
- **Banner Grabbing**: Skopefsis can retrieve banners from open ports to identify services running on those ports.
- **Reverse DNS Lookup**: Skopefsis performs reverse DNS lookup to map IP addresses to hostnames.
- **Configurable Timeout**: Users can specify a custom timeout value for port scanning.
- **User-friendly Interface**: Skopefsis offers a straightforward command-line interface for initiating port scans and viewing results.

## Usage
1. Compile the source files:
   ```
   javac -cp src src/mi/m4x/project/skopefsis/PortScanner.java src/mi/m4x/project/skopefsis/PortScannerEngine.java src/mi/m4x/project/skopefsis/PortScannerThread.java src/mi/m4x/project/skopefsis/PortScannerConstants.java src/mi/m4x/project/skopefsis/PortScannerUtils.java
   ```

2. Run the main class:
   ```
   java -cp src mi.m4x.project.skopefsis.PortScanner <host>
   ```
   Replace `<host>` with the hostname or IP address of the target host.

3. Follow the prompts to initiate the port scan and view the results.

## Additional Tasks
- **Perform Banner Grabbing**: Skopefsis retrieves banners from open ports to identify services running on those ports.
- **Perform Reverse DNS Lookup**: Skopefsis performs reverse DNS lookup to map IP addresses to hostnames.

### LICENSE

This project is under the MIT LICENSE - see the [LICENSE](LICENSE.txt) file for details.