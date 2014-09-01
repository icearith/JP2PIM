/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.awt.Color;

/**
 *
 * @author arith
 */
public class MessageStyle {

    private Color messageTimeColor;
    private Color messageNickNameColor;
    private Color messageIpAdressColor;
    private Color messageServerPortColor;
    private Color MessageTextColor;

    public MessageStyle() {
        this.messageTimeColor = Color.BLUE;
        this.messageNickNameColor = Color.BLACK;
        this.messageIpAdressColor = Color.BLACK;
        this.messageServerPortColor = Color.BLACK;
        this.MessageTextColor = Color.BLACK;
    }

    public Color getMessageTimeColor() {
        return messageTimeColor;
    }

    public void setMessageTimeColor(Color messageTimeColor) {
        this.messageTimeColor = messageTimeColor;
    }

    public Color getMessageNickNameColor() {
        return messageNickNameColor;
    }

    public void setMessageNickNameColor(Color messageNickNameColor) {
        this.messageNickNameColor = messageNickNameColor;
    }

    public Color getMessageIpAdressColor() {
        return messageIpAdressColor;
    }

    public void setMessageIpAdressColor(Color messageIpAdressColor) {
        this.messageIpAdressColor = messageIpAdressColor;
    }

    public Color getMessageServerPortColor() {
        return messageServerPortColor;
    }

    public void setMessageServerPortColor(Color messageServerPortColor) {
        this.messageServerPortColor = messageServerPortColor;
    }

    public Color getMessageTextColor() {
        return MessageTextColor;
    }

    public void setMessageTextColor(Color MessageTextColor) {
        this.MessageTextColor = MessageTextColor;
    }
}
