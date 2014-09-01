/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.client.ClientInputStream;
import com.common.Buddy;
import com.common.Message;
import com.common.MessageContent;
import com.common.MessageStyle;
import com.gui.ChatFrame;
import com.gui.MainFrame;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arith
 */
public class ServerInputStream implements Runnable {

    private Message message;
    private Socket socket;
    private ChatFrame chatWindow;
    private MainFrame mainWindow;

    public MainFrame getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    public ServerInputStream(Message message, Socket socket, ChatFrame chatWindow, MainFrame mainWindow) {
        this.message = message;
        this.socket = socket;
        this.chatWindow = chatWindow;
        this.mainWindow = mainWindow;
    }

    private Message generateMessage(String str) {
        MessageContent messageContent = new MessageContent(str, new Date());
        MessageStyle messageStyle = new MessageStyle();
        Buddy buddy = new Buddy(socket.getInetAddress().toString(), socket.getInetAddress().getHostAddress(), socket.getPort());
        Message message = new Message(buddy, messageContent, messageStyle);

        return message;
    }

    @Override
    public void run() {
        try {

            while (true) {

                try {
                    socket.sendUrgentData(0xFF);
                    BufferedReader inBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str = inBufferedReader.readLine();
                    if (!(str == null) || str.equals("")) {
                        Message message = generateMessage(str);
                        chatWindow.archiveChattingHistory(message);
                    }
                } catch (Exception e) {
                    socket.close();
                    new JOptionPane().showMessageDialog(new Frame(), "Connection Broke!");
                    break;
                }


            }
        } catch (IOException ex) {
            Logger.getLogger(ClientInputStream.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error!");
        }
    }
}
