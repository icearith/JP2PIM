/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.common.Message;
import com.gui.ChatFrame;
import com.gui.MainFrame;
import java.net.Socket;

/**
 *
 * @author arith
 */
public class ServerDaemon implements Runnable {

    Socket socket;
    ChatFrame chatWindow;
    Message message;
    MainFrame mainWindow;

    public ServerDaemon(Socket socket, ChatFrame chatWindow, MainFrame mainWindow) {
        this.socket = socket;
        this.chatWindow = chatWindow;
        this.mainWindow = mainWindow;

    }


    @Override
    public void run() {
        new Thread(new ServerInputStream(message, socket, chatWindow, mainWindow)).start();
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
