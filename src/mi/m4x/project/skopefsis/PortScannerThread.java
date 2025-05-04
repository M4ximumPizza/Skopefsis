package mi.m4x.project.skopefsis;

import java.net.*;

public class PortScannerThread implements Runnable {
    private final String host;
    private final int port;
    private final String protocol;
    private final boolean useIPv6;

    public PortScannerThread(String host, int port, String protocol, boolean useIPv6) {
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.useIPv6 = useIPv6;
    }

    @Override
    public void run() {
        try {
            if ("UDP".equalsIgnoreCase(protocol)) {
                scanUDP();
            } else {
                scanTCP();
            }
        } catch (Exception e) {
            System.out.println("Error scanning port " + port + ": " + e.getMessage());
        }
    }

    private void scanTCP() throws Exception {
        SocketAddress sockaddr = useIPv6
                ? new InetSocketAddress(Inet6Address.getByName(host), port)
                : new InetSocketAddress(host, port);
        try (Socket socket = new Socket()) {
            socket.connect(sockaddr, 1000);
            System.out.println("TCP Port " + port + " is open");
        }
    }

    private void scanUDP() throws Exception {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(new byte[0], 0, address, port);
            socket.send(packet);
            socket.setSoTimeout(1000);
            socket.receive(packet);
            System.out.println("UDP Port " + port + " is open");
        } catch (SocketTimeoutException e) {
            // Port is closed or timed out
        }
    }
}