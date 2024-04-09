package mi.m4x.project.skopefsis;

import java.net.*;

public class UDPPortScannerThread implements Runnable {
    private final String host;
    private final int startPort;
    private final int endPort;
    private final int rateLimit;
    private final Proxy proxy;
    private final boolean useIPv6;

    public UDPPortScannerThread(String host, int startPort, int endPort, int rateLimit, Proxy proxy, boolean useIPv6) {
        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
        this.rateLimit = rateLimit;
        this.proxy = proxy;
        this.useIPv6 = useIPv6;
    }

    @Override
    public void run() {
        for (int port = startPort; port <= endPort; port++) {
            long startTime = System.currentTimeMillis();
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress address = InetAddress.getByName(host);
                DatagramPacket packet = new DatagramPacket(new byte[0], 0, address, port);
                socket.send(packet);
                socket.setSoTimeout(PortScannerConstants.DEFAULT_TIMEOUT);
                socket.receive(packet);
                socket.close();
                System.out.println("UDP Port " + port + " is open");
            } catch (SocketTimeoutException e) {
                // Port is closed or timed out
            } catch (Exception e) {
                System.out.println("Error scanning UDP port " + port + ": " + e.getMessage());
            } finally {
                long elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime < rateLimit) {
                    try {
                        Thread.sleep(rateLimit - elapsedTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}

