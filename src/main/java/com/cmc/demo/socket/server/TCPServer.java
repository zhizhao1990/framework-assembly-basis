package com.cmc.demo.socket.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TCP Server Instance
 *  
 * @author Thomas
 * @version 2017年1月19日 下午8:18:47
 */
public class TCPServer implements Server {

    private static final int TCP_SERVER_PORT = 8888;
    private static final Logger LOG = LoggerFactory.getLogger(TCPServer.class);

    @Override
    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(TCP_SERVER_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                LOG.info(dataInputStream.readUTF());
            }
        } catch (IOException e) {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException ce) {
                    this.logError(ce);
                }
            }
            this.logError(e);
        }
    }

    private void logError(Exception e) {
        LOG.error("", e);
    }

    public static void main(String[] args) {
        new TCPServer().start();
    }

}