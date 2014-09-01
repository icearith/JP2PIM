/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.common.Buddy;
import com.common.Message;
import com.common.MessageContent;
import com.common.MessageStyle;
import com.gui.ChatFrame;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arith
 */
public class ClientInputStream implements Runnable {

    private Socket socket;
    private ChatFrame chatWindow;

    public ClientInputStream(Socket socket) {
        this.socket = socket;
    }

    private Message generateMessage(String str) {
        MessageContent messageContent = new MessageContent(str, new Date());
        MessageStyle messageStyle = new MessageStyle();
        Buddy buddy = getBuddy();
        Message message = new Message(buddy, messageContent, messageStyle);

        return message;
    }

    private Buddy getBuddy() {
        List<Buddy> buddyList = chatWindow.getBuddyList();
        Iterator<Buddy> iterator = buddyList.iterator();
        Buddy buddy = new Buddy(null, null, 0);
        while (iterator.hasNext()) {
            buddy = iterator.next();
            if (buddy.getIpAdress().equals(socket.getInetAddress().getHostAddress()) && socket.getPort() == buddy.getServerPort()) {
                break;
            }
        }
        return buddy;

    }

    @Override
    public void run() {
        try {


            while (true) {
                BufferedReader inBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try {
                    socket.sendUrgentData(0xFF);
                    String str = inBufferedReader.readLine();
                    if (!(str == null || str.equals(""))) {
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

    public ChatFrame getChatWindow() {
        return chatWindow;
    }

    public void setChatWindow(ChatFrame chatWindow) {
        this.chatWindow = chatWindow;
    }
}
