package mi.m4x.project.skopefsis;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class PortScannerEngine {
    private final String host;
    private final int startPort;
    private final int endPort;
    private final String protocol;
    private final int numThreads;
    private final boolean useIPv6;

    public PortScannerEngine(String host, int startPort, int endPort, String protocol, int numThreads, boolean useIPv6) {
        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
        this.protocol = protocol;
        this.numThreads = numThreads;
        this.useIPv6 = useIPv6;
    }

    public void scanPorts() {
        System.out.println("Scanning ports " + startPort + " to " + endPort + " on host " + host + "...\n");

        CompletableFuture<?>[] futures = IntStream.rangeClosed(startPort, endPort)
                .mapToObj(port -> CompletableFuture.runAsync(() -> new PortScannerThread(host, port, protocol, useIPv6).run()))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        System.out.println("Port scanning completed.");
    }
}