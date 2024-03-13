package mi.m4x.project.skopefsis;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.*;
import javax.net.ssl.*;

public class PortScanner {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java PortScanner <host>");
            return;
        }

        String host = args[0];
        PortScannerEngine scanner = new PortScannerEngine(host);
        scanner.scanPorts();
    }
}