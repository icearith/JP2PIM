/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import com.gui.ChatFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arith
 */
public class FileReveiveDeamon implements Runnable {
    private String ipAdress;
    private int port;
    private ChatFrame chatWindow;

    public FileReveiveDeamon(int port, ChatFrame chatWindow) {
        this.chatWindow = chatWindow;
        this.port = port;
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            int fileId = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                chatWindow.switchToFileReceiveTab();
                new Thread(new FileReceive(socket, chatWindow, fileId)).start();
                fileId++;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileReveiveDeamon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
