package mi.m4x.project.skopefsis;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PortScannerEngine {
    private static final int NUM_THREADS = 5; // Number of threads for parallel scanning
    private final String host;

    public PortScannerEngine(String host) {
        this.host = host;
    }

    public void scanPorts() {
        System.out.println("Scanning ports 1 to " + PortScannerConstants.MAX_PORT_NUMBER + " on host " + host + "...\n");

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        ConcurrentLinkedQueue<Future<?>> futures = new ConcurrentLinkedQueue<>();

        int portsPerThread = PortScannerConstants.MAX_PORT_NUMBER / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int startPort = i * portsPerThread + 1;
            int endPort = (i == NUM_THREADS - 1) ? PortScannerConstants.MAX_PORT_NUMBER : (i + 1) * portsPerThread;
            Future<?> future = executor.submit(new PortScannerThread(host, startPort, endPort));
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

        PortScannerUtils.performAdditionalTasks(host);
    }
}

