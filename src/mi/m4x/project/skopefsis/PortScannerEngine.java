package mi.m4x.project.skopefsis;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PortScannerEngine {
    private final String host;
    private final int startPort;
    private final int endPort;
    private final String protocol;
    private final String outputFormat;
    private final int numThreads;
    private final int rateLimit; // Maximum number of ports scanned per second
    private final Proxy proxy; // Proxy for scanning
    private final boolean useIPv6; // Flag for IPv6 support

    public PortScannerEngine(String host, int startPort, int endPort, String protocol, String outputFormat,
                             int numThreads, int rateLimit, Proxy proxy, boolean useIPv6) {
        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
        this.protocol = protocol;
        this.outputFormat = outputFormat;
        this.numThreads = numThreads;
        this.rateLimit = rateLimit;
        this.proxy = proxy;
        this.useIPv6 = useIPv6;
    }

    public void scanPorts() {
        System.out.println("Scanning ports " + startPort + " to " + endPort + " on host " + host + "...\n");

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        ConcurrentLinkedQueue<Future<?>> futures = new ConcurrentLinkedQueue<>();

        int portsPerThread = (endPort - startPort + 1) / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int threadStartPort = startPort + i * portsPerThread;
            int threadEndPort = (i == numThreads - 1) ? endPort : threadStartPort + portsPerThread - 1;
            Future<?> future = executor.submit(new PortScannerThread(host, threadStartPort, threadEndPort, protocol, outputFormat, rateLimit, proxy, useIPv6));
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
}

