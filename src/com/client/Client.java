/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.common.Buddy;
import com.common.Message;
import com.common.NetAffair;
import com.gui.MainFrame;
import com.server.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arith
 */
public class Client {

    private Buddy myself;

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

                    MainFrame mainWindow = new MainFrame();
                    mainWindow.setVisible(true);



                    String localServerIpAddress = NetAffair.getHostIpAdress();
                    int localServerPort = NetAffair.getRandomFreePort();
                    mainWindow.setLocalServerPort(localServerPort);
                    mainWindow.setLocalServerAddress(localServerIpAddress);



                    // String serverIpAdress = mainWindow.Server server = new Server(null, port);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public Client() {
    }

    public Buddy getMyself() {
        return myself;
    }

    public void setMyself(Buddy myself) {
        this.myself = myself;
    }
}
