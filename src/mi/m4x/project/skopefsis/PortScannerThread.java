package mi.m4x.project.skopefsis;

import java.io.IOException;
import java.net.*;

public class PortScannerThread implements Runnable {
    private final String host;
    private final int startPort;
    private final int endPort;
    private final String protocol;
    private final String outputFormat;
    private final int rateLimit;
    private final Proxy proxy;
    private final boolean useIPv6;

    public PortScannerThread(String host, int startPort, int endPort, String protocol, String outputFormat,
                             int rateLimit, Proxy proxy, boolean useIPv6) {
        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
        this.protocol = protocol;
        this.outputFormat = outputFormat;
        this.rateLimit = rateLimit;
        this.proxy = proxy;
        this.useIPv6 = useIPv6;
    }

    @Override
    public void run() {
        for (int port = startPort; port <= endPort; port++) {
            // Implement rate limiting
            long startTime = System.currentTimeMillis();
            try {
                SocketAddress sockaddr;
                if (useIPv6) {
                    sockaddr = new InetSocketAddress(Inet6Address.getByName(host), port);
                } else {
                    sockaddr = new InetSocketAddress(host, port);
                }
                Socket socket = new Socket(proxy);
                socket.connect(sockaddr, PortScannerConstants.DEFAULT_TIMEOUT);
                socket.close();
                long elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime < rateLimit) {
                    Thread.sleep(rateLimit - elapsedTime);
                }
            } catch (ConnectException | SocketTimeoutException e) {
                // Port is closed or timed out
            } catch (Exception e) {
                System.out.println("Error connecting to port " + port + ": " + e.getMessage());
            }
        }
    }
}