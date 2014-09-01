/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import com.gui.ChatFrame;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
public class FileReceive implements Runnable {

    Socket socket;
    ChatFrame chatWindow;
    int id;

    public FileReceive(Socket socket, ChatFrame chatWindow, int id) {
        this.socket = socket;
        this.chatWindow = chatWindow;

    }

    @Override
    public void run() {
        for (;;) {
            try {
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                BufferedReader inbr = new BufferedReader(new InputStreamReader(in));

                String fileName = inbr.readLine();
                if (fileName == null) {
                    System.out.println("Connection broken!");
                }
                int n = new JOptionPane().showConfirmDialog(new Frame(), "Accept to receive file " + fileName, "File Transfer", JOptionPane.YES_NO_OPTION);

                out.writeByte((byte) n);
                out.flush();

                int total = in.readInt();
                int available = total;
                if (n == 1) {
                    socket.close();
                    break;
                } else {
                    chatWindow.updateFileReceiveList(fileName);

                    File newFile = new File(new Date().toString() + fileName);
                    FileOutputStream to = new FileOutputStream(newFile);
                    DataOutputStream fout = new DataOutputStream(to);

                    while (true) {

                        byte y = in.readByte();
                        int x = in.readInt();
                        byte[] bq = new byte[500];
                        int offset = 0;
                        while (offset < x) {
                            int len = in.read(bq, offset, x - offset);
                            available -= len;
                            offset += len;
                        }
                        fout.write(bq, 0, x);
                        if (y == 2) {
                            break;
                        }
                        int rate = available < 500 ? 100 : (int) (100 * (1 - ((float) available / (float) total)));
                        chatWindow.updateReceiveFileListView(fileName, id, rate);
                    }

                }


            } catch (IOException e) {
                e.getMessage();
            }
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(FileReceive.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
