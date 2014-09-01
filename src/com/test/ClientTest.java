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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arith
 */
public class ClientTest implements Runnable {

    public static void main(String[] args) {
        ClientTest client = new ClientTest();
        client.run();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 5678);
            PrintWriter outPrintWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader inBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader wtBufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Please input:");
            while (true) {
                String str = wtBufferedReader.readLine();
                outPrintWriter.println(str);
                outPrintWriter.flush();

                if (str.equals("end")) {
                    break;
                }
                System.out.println(inBufferedReader.readLine());
            }
            outPrintWriter.close();
            socket.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
