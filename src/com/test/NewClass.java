/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.awt.Frame;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author arith
 */
public class NewClass {

    public static void main(String[] args) throws UnknownHostException, IOException {
        new JOptionPane().showMessageDialog(new JFrame(), "Unknow Host\nuse 127.0.0.1 as default address");
    }
}
