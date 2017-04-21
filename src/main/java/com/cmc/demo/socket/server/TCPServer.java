package com.cmc.demo.socket.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TCP Server.
 * @author Thomas
 * @version 2017年1月19日 下午8:18:47
 */
public class TCPServer implements Server {

    private static final int TCP_SERVER_PORT = 8888;
    private static final Logger LOG = LoggerFactory.getLogger(TCPServer.class);

    public static void main(String[] args) {
        new TCPServer().start();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(TCP_SERVER_PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                System.out.println(dataInputStream.readUTF());
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}