package com.cmc.demo.socket.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPServer implements Server {

    private static final Logger LOG = LoggerFactory.getLogger(UDPServer.class);

    private static final int UDP_SERVER_PORT = 9999;

    @Override
    public void start() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(UDP_SERVER_PORT);
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            while (true) {
                datagramSocket.receive(datagramPacket);
                this.log(new String(buffer, 0, datagramPacket.getLength()));
            }
        } catch (SocketException e) {
            this.logError(e);
        } catch (IOException e) {
            this.logError(e);
        } finally {
            datagramSocket.close();
        }
    }

    private void logError(Exception e) {
        LOG.error("", e);
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        new UDPServer().start();
    }

}