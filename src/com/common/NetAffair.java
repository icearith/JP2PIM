/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author arith
 */
public class NetAffair {

    public static String LocalHost;

    public static String getHostIpAdress() throws UnknownHostException {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress().toString();
        } catch (UnknownHostException e) {
            new JOptionPane().showMessageDialog(new JFrame(), "Unknow Host!");
        }
        return "127.0.0.1";
    }

    public static int getRandomFreePort() {
        return (int) (Math.round(Math.random() * 64511 + 1024));
    }
}
