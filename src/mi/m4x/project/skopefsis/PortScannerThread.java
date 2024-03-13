package mi.m4x.project.skopefsis;

import java.io.IOException;
import java.net.*;

public class PortScannerThread implements Runnable {
    private final String host;
    private final int startPort;
    private final int endPort;

    public PortScannerThread(String host, int startPort, int endPort) {
        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
    }

    @Override
    public void run() {
        for (int port = startPort; port <= endPort; port++) {
            try {
                SocketAddress sockaddr = new InetSocketAddress(host, port);
                Socket socket = new Socket();
                socket.connect(sockaddr, PortScannerConstants.DEFAULT_TIMEOUT);
                socket.close();
                System.out.println("Port " + port + " is open.");
                PortScannerUtils.performBannerGrabbing(host, port);
            } catch (ConnectException e) {
                // Port is closed
            } catch (SocketTimeoutException e) {
                // Port is closed due to timeout
            } catch (IOException e) {
                System.out.println("Error connecting to port " + port + ": " + e.getMessage());
            }
        }
    }
}

