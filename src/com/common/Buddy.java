/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.net.InetAddress;

/**
 *
 * @author arith
 */
public class Buddy {

    private String nickName;
    private String ipAdress;
    private int serverPort;

    public Buddy(String nickName, String ipAdress, int serverPort) {
        this.nickName = nickName;
        this.ipAdress = ipAdress;
        this.serverPort = serverPort;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
