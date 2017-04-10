package com.cmc.demo.socket.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MultiThreading Server Instance
 * @author Thomas
 * @version 2017年1月19日 下午8:18:47
 */
public class MultiThreadTCPServer implements Server {

    private static final Logger LOG = LoggerFactory.getLogger(MultiThreadTCPServer.class);
    private static final int TCP_SERVER_PORT = 8888;

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(TCP_SERVER_PORT)) {
            LOG.info("Server is listening...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new MultiThreadTCPServer().new Runner(socket)).start();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        new MultiThreadTCPServer().start();
    }

    /**
     * 多线程TCP Socket
     * @author Thomas Lee
     * @version 2017年3月28日 下午7:37:26
     */
    private class Runner implements Runnable {

        private Socket socket;

        public Runner(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream()) {
                try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
                    LOG.info(dataInputStream.readUTF());
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

}