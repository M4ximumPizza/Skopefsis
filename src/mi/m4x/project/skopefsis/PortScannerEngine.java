package mi.m4x.project.skopefsis;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Proxy;
import java.util.concurrent.*;

public class PortScannerEngine {
    private final String host;
    private final int startPort;
    private final int endPort;
    private final String protocol;
    private final String outputFormat;
    private final int numThreads;
    private final int rateLimit;
    private final Proxy proxy;
    private final boolean useIPv6;
    private final boolean useUDP;

    public PortScannerEngine(String host, int startPort, int endPort, String protocol, String outputFormat,
                             int numThreads, boolean useUDP, boolean useIPv6) {
        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
        this.protocol = protocol;
        this.outputFormat = outputFormat;
        this.numThreads = numThreads;
        this.useUDP = useUDP;
        this.rateLimit = 1000; // Default rate limit (1 port per second)
        this.proxy = Proxy.NO_PROXY; // Default proxy (no proxy)
        this.useIPv6 = useIPv6; // Initialize useIPv6
    }

    public void scanPorts() {
        System.out.println("Scanning ports " + startPort + " to " + endPort + " on host " + host + "...\n");

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        ConcurrentLinkedQueue<Future<?>> futures = new ConcurrentLinkedQueue<>();

        int portsPerThread = (endPort - startPort + 1) / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int threadStartPort = startPort + i * portsPerThread;
            int threadEndPort = (i == numThreads - 1) ? endPort : threadStartPort + portsPerThread - 1;
            Runnable scannerThread;
            if (useUDP) {
                scannerThread = new UDPPortScannerThread(host, threadStartPort, threadEndPort, rateLimit, proxy, useIPv6);
            } else {
                scannerThread = new PortScannerThread(host, threadStartPort, threadEndPort, protocol, outputFormat, rateLimit, proxy, useIPv6);
            }
            Future<?> future = executor.submit(scannerThread);
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        System.out.println("Port scanning completed.");
    }

    // Method to write results to file
    private void writeToFile(String result) {
        try (FileWriter fw = new FileWriter("scan_results.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
