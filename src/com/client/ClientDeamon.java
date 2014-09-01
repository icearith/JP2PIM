/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.common.Buddy;
import com.common.Message;
import com.gui.ChatFrame;
import java.awt.Frame;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author arith
 */
public class ClientDeamon implements Runnable {

    private List<Buddy> buddyList;
    private Message message;
    private ChatFrame chatWinow;

    public void setMessage(Message message) {
        this.message = message;
    }
    private List<Socket> socketList;

    public ClientDeamon(List<Buddy> buddyList, Message message, ChatFrame chatWindow) {

        try {
            this.buddyList = buddyList;

            this.message = message;
            this.chatWinow = chatWindow;
            this.socketList = generateSocketList(buddyList);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientDeamon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientDeamon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void activeDeamon() {
        Iterator<Socket> iterator = socketList.iterator();
        while (iterator.hasNext()) {
            Socket socket = iterator.next();
            ClientOutputStream out = new ClientOutputStream(socket, message);
            new Thread(out).start();

//            ClientInputStream in = new ClientInputStream(socket);
//            in.setChatWindow(chatWinow);
//            new Thread(in).start();
        }

    }

    private List<Socket> generateSocketList(List<Buddy> buddyList) throws UnknownHostException, IOException {
        List<Socket> list = new ArrayList<Socket>();
        Socket socket = new Socket();
        try {
            Iterator<Buddy> iterator = buddyList.iterator();

            while (iterator.hasNext()) {
                Buddy buddy = iterator.next();
                socket = new Socket(buddy.getIpAdress(), buddy.getServerPort());
                list.add(socket);

                ClientInputStream in = new ClientInputStream(socket);
                in.setChatWindow(chatWinow);
                new Thread(in).start();
                this.chatWinow.setVisible(true);
            }
        } catch (UnknownHostException e) {
            chatWinow.setVisible(false);
            socket.close();
            new JOptionPane().showMessageDialog(new JFrame(), "Unkown Host:" + e.getMessage());


        } catch (ConnectException e) {
            chatWinow.setVisible(false);
            socket.close();
            new JOptionPane().showMessageDialog(new JFrame(), "Remote host:" + e.getMessage());
        } catch (NoRouteToHostException e) {
            chatWinow.setVisible(false);
            socket.close();
            new JOptionPane().showMessageDialog(new JFrame(), "Local host:" + e.getMessage());
        }

        return list;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public void run() {
        activeDeamon();
    }

    public ChatFrame getChatWinow() {
        return chatWinow;
    }

    public void setChatWinow(ChatFrame chatWinow) {
        this.chatWinow = chatWinow;
    }
}
