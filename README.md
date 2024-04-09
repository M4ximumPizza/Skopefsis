# Skopefsis - Multi-threaded Port Scanning Tool
Greek for "Scanner" or "Scout"

Skopefsis is a Java application designed to scan ports on a specified host using multiple threads for faster execution. It provides a simple command-line interface for scanning ports and performing additional tasks such as banner grabbing and reverse DNS lookup.

## Features
- **Multi-threaded Port Scanning**: Skopefsis utilizes multiple threads for parallel scanning of ports, improving speed and efficiency.
- **Banner Grabbing**: Retrieve banners from open ports to identify services running on those ports.
- **Reverse DNS Lookup**: Perform reverse DNS lookup to map IP addresses to hostnames.
- **Configurable Timeout**: Users can specify a custom timeout value for port scanning.
- **User-friendly Interface**: Skopefsis offers a straightforward command-line interface for initiating port scans and viewing results.
- **Integration with Network Scanners**: Integrate with popular network scanning tools to leverage their advanced scanning capabilities.
- **Integration with Vulnerability Scanners**: Integrate with vulnerability scanners to perform comprehensive security assessments.
- **Custom Plugin Support**: Support for custom plugins or scripts to extend functionality according to specific requirements.
- **Integration with Threat Intelligence Platforms**: Integrate with threat intelligence platforms to enrich scan results with information about known threats.
- **Dynamic Port Range Adjustment**: Automatically adjust the port range based on initial scan results or user feedback.
- **IPv6 Support**: Option to enable IPv6 support for scanning.

## Usage
1. Compile the source files:
    ```
    javac -cp src src/mi/m4x/project/skopefsis/PortScanner.java src/mi/m4x/project/skopefsis/PortScannerEngine.java src/mi/m4x/project/skopefsis/PortScannerThread.java src/mi/m4x/project/skopefsis/PortScannerConstants.java src/mi/m4x/project/skopefsis/PortScannerUtils.java
    ```

2. Run the main class:
   ```
    java -cp src mi.m4x.project.skopefsis.PortScanner <host> [<startPort> <endPort>] [<protocol>] [<outputFormat>] [<useUDP>] [<numThreads>] [<useIPv6>]
   ```
   Replace `<host>` with the hostname or IP address of the target host.

Replace `<host>` with the hostname or IP address of the target host. Additional optional arguments can be provided as needed.

3. Follow the prompts to initiate the port scan and view the results.

## Additional Tasks
- **Perform Banner Grabbing**: Retrieve banners from open ports to identify services running on those ports.
- **Perform Reverse DNS Lookup**: Perform reverse DNS lookup to map IP addresses to hostnames.

### LICENSE

This project is under the MIT LICENSE - see the [LICENSE](LICENSE.txt) file for details.