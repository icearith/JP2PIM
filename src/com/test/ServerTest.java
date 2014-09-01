/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author arith
 */
public class ServerTest implements Runnable {

    private Socket socket;

    public ServerTest(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5678);
        while (true) {
            System.out.println("Waiting client to connect...");
            ServerTest server = new ServerTest(serverSocket.accept());
            server.run();
        }
    }
    @Override
    public void run() {
        try {
            BufferedReader inBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outPrintWriter = new PrintWriter(socket.getOutputStream());
            System.out.println("Wating for client to send string...");
            while (true) {
                String str = inBufferedReader.readLine();
                System.out.println(str);
                outPrintWriter.println("Server return:" + str);
                outPrintWriter.flush();
                if (str.equals("exit")) {
                    break;
                }
            }
            inBufferedReader.close();
            socket.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
