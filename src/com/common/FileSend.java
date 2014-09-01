/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import com.gui.ChatFrame;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author arith
 */
public class FileSend implements Runnable {

    private ChatFrame chatWindow;
    private File file;
    private Socket socket;
    FileInputStream from;
    ByteArrayOutputStream baos;
    private int id;
    long x;

    public FileSend(ChatFrame chatWindow, int id, File file, Socket socket) {
        this.chatWindow = chatWindow;
        this.file = file;
        this.socket = socket;
        this.id = id;
    }

    public ChatFrame getChatWindow() {
        return chatWindow;
    }

    public void setChatWindow(ChatFrame chatWindow) {
        this.chatWindow = chatWindow;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                DataOutputStream Do = new DataOutputStream(baos = new ByteArrayOutputStream());

                from = new FileInputStream(file);
                out.writeBytes(file.getName() + "\n");
                out.flush();
                out.writeBytes(Integer.toString(from.available()));
                out.flush();
                byte y = in.readByte();

                if (y == 0) {
                    out.writeInt(from.available());
                    out.flush();
                    byte[] rf = new byte[500];
                    try {
                        int total = from.available();
                        while (from.available() > 0) {
                            baos.reset();
                            x = from.available();
                            long len = from.read(rf); //read len bytes from file
                            if (x != len) {
                                Do.writeByte(1);
                                Do.writeInt((int) len);
                                Do.write(rf, 0, (int) len);
                            } else {
                                Do.writeByte(2);
                                Do.writeByte((int) len);
                                Do.write(rf, 0, (int) len);
                            }
                            Do.flush();

                            byte[] Q = baos.toByteArray();
                            out.write(Q, 0, (int) len + 5);
                            out.flush();

                            int rate = from.available() < 500 ? 100 : (int) (100 * (1 - ((float) from.available() / (float) total)));
                            chatWindow.updateSendFileListView(file.getName(), id, rate);
                        }
                    } catch (IOException e) {
                        new JOptionPane().showMessageDialog(new Frame(), "Remote host error or connection broken!");
                    }
                } else {
                    new JOptionPane().showMessageDialog(new Frame(), "File Transfer cancelled by your friends!");
                }
            } catch (Exception e) {
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {
                    new JOptionPane().showMessageDialog(new Frame(), "Error occured when close local host socket!");
                }
            }
        }
    }
}
