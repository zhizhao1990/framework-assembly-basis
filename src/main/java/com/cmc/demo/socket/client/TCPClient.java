package com.cmc.demo.socket.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPClient implements Client {

    private static final Logger LOG = LoggerFactory.getLogger(TCPClient.class);
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8888;
    private static final String MESSAGE = "Hello Server.";

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new TCPClient().send();
        }
    }

    @Override
    public void send() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
                    dataOutputStream.writeUTF(MESSAGE);
                }
            }
        } catch (UnknownHostException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}