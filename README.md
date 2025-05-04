# Skopefsis - Multi-threaded Port Scanning Tool
Greek for "Scanner" or "Scout"

Skopefsis is a Java application designed to scan ports on a specified host using multiple threads for faster execution. It provides a simple command-line interface for scanning ports and supports additional features such as IPv6 scanning and configurable options.

## Features
- **Multi-threaded Port Scanning**: Leverages multiple threads for parallel scanning, improving speed and efficiency.
- **TCP and UDP Scanning**: Supports both TCP and UDP protocols for comprehensive port scanning.
- **Configurable Options**: Users can customize the port range, protocol, timeout, and number of threads via a configuration file or command-line arguments.
- **IPv6 Support**: Option to enable IPv6 scanning for modern network environments.
- **Dynamic Rate Limiting**: Ensures efficient scanning while avoiding excessive resource usage.
- **Error Handling and Logging**: Provides robust error handling and logging for better debugging and monitoring.
- **Lightweight and Modular**: Simplified design with minimal dependencies for easy integration and maintenance.

## Usage
1. **Compile the Source Files**:
    ```
    javac -cp src src/mi/m4x/project/skopefsis/PortScanner.java src/mi/m4x/project/skopefsis/PortScannerEngine.java src/mi/m4x/project/skopefsis/PortScannerThread.java src/mi/m4x/project/skopefsis/PortScannerConstants.java
    ```

2. **Run the Application**:
    ```
    java -cp src mi.m4x.project.skopefsis.PortScanner <host>
    ```
   Replace `<host>` with the hostname or IP address of the target host.

3. **Configuration**:
   - Modify the `config.properties` file to customize scanning options:
     ```ini
     startPort=1
     endPort=65535
     protocol=TCP
     numThreads=10
     useIPv6=false
     ```
   - Alternatively, pass arguments directly to the application:
     ```
     java -cp src mi.m4x.project.skopefsis.PortScanner <host> [<startPort> <endPort>] [<protocol>] [<numThreads>] [<useIPv6>]
     ```

4. **View Results**:
   - The application outputs the status of scanned ports directly to the console.

## Example
To scan all TCP ports on `127.0.0.1` using 10 threads:
```
java -cp src mi.m4x.project.skopefsis.PortScanner 127.0.0.1
```
## Configuration File
The `config.properties` file allows you to define default settings:
```ini
startPort=1
endPort=65535
protocol=TCP
numThreads=10
useIPv6=false
```

## Logging
Logging is configured via a logging.properties file. If not found, default logging settings are used.
## LICENSE
This project is under the MIT LICENSE - see the LICENSE file for details.