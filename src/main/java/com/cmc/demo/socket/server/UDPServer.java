package com.cmc.demo.socket.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UDP Server.
 * @author Thomas Lee
 * @version 2017年4月21日 下午4:15:35
 */
public class UDPServer implements Server {

    private static final Logger LOG = LoggerFactory.getLogger(UDPServer.class);
    private static final int UDP_SERVER_PORT = 9999;

    public static void main(String[] args) {
        new UDPServer().start();
    }

    @Override
    public void start() {
        try (DatagramSocket datagramSocket = new DatagramSocket(UDP_SERVER_PORT)) {
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            while (true) {
                datagramSocket.receive(datagramPacket);
                System.out.println(new String(buffer, 0, datagramPacket.getLength()));
            }
        } catch (SocketException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}