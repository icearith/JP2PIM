/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.common.Buddy;
import com.common.FileReveiveDeamon;
import com.gui.ChatFrame;
import com.gui.MainFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author arith
 */
public class Server implements Runnable {

    private String ipAddress;
    private int port;
    private boolean interrupt;
    private Buddy myself;
    private MainFrame mainWindow;

    public Server(String ipAddress, int port, Buddy myself, MainFrame mainWindow) {

        this.ipAddress = ipAddress;
        this.port = port;
        this.interrupt = false;
        this.myself = myself;
        this.mainWindow = mainWindow;

    }


    @Override
    public void run() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                mainWindow.updateServerConnectionCount(true);
                Buddy buddy = new Buddy(socket.getInetAddress().toString(), socket.getInetAddress().toString(), socket.getPort());
                ChatFrame chatWindow = new ChatFrame(buddy, socket, myself, mainWindow);
                chatWindow.setVisible(true);
                new Thread(new FileReveiveDeamon(port + 1, chatWindow)).start();
                new Thread(new ServerDaemon(socket, chatWindow, mainWindow)).start();


            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public boolean isInterrupt() {
        return interrupt;
    }

    public void setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
    }
}
