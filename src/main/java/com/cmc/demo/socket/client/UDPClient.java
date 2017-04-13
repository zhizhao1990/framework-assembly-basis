package com.cmc.demo.socket.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPClient implements Client {

    private static final Logger LOG = LoggerFactory.getLogger(UDPClient.class);

    private static final String UDP_SERVER_IP = "127.0.0.1";
    private static final int UDP_SERVER_PORT = 9999;
    private static final String FIRST_MSG = "Hello, Server.";

    /** 线程间隔时间1000ms */
    public static final long THREAD_INTERNAL_TIME = 1000;

    @Override
    public void send() {
        DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();
            byte[] buffer = new byte[1024];
            buffer = FIRST_MSG.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, new InetSocketAddress(UDP_SERVER_IP, UDP_SERVER_PORT));
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (SocketException e) {
            this.logError(e);
        } catch (IOException e) {
            this.logError(e);
        }
    }

    private void logError(Exception e) {
        LOG.error("", e);
    }

    public static void main(String[] args) {
        javaSETimer(new UDPClient());
    }

    // http://batitan.iteye.com/blog/253483 
    public static void javaSETimer(Client client) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                client.send();
            }
        };
        timer.schedule(timerTask, new Date(), THREAD_INTERNAL_TIME);
    }

}