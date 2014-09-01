/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.common.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arith
 */
public class ClientOutputStream implements Runnable {

    private Socket socket;
    private Message message;

    public ClientOutputStream(Socket socket, Message message) {
        this.message = message;
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            send(this.socket, this.message);
        } catch (IOException ex) {
            Logger.getLogger(ClientOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void send(Socket socket, Message message) throws IOException {

        PrintWriter outPrintWriter = new PrintWriter(socket.getOutputStream());

        String str = message.getMessageContent().getText();
        outPrintWriter.println(str);
        outPrintWriter.flush();

    }
}
