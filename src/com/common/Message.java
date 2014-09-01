/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import com.common.Buddy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author arith
 */
public class Message {

    private Buddy buddy;
    private MessageContent messageContent;
    private MessageStyle messageStyle;

    public Message(Buddy buddy, MessageContent messageContent, MessageStyle messageStyle) {
        this.buddy = buddy;
        this.messageContent = messageContent;
        this.messageStyle = messageStyle;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public String getMessagePattern() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return this.buddy.getNickName() + " " + df.format(this.messageContent.getDate()) + "\n" + this.messageContent.getText() + "\n\n";
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public MessageContent getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(MessageContent messageContent) {
        this.messageContent = messageContent;
    }

    public MessageStyle getMessageStyle() {
        return messageStyle;
    }

    public void setMessageStyle(MessageStyle messageStyle) {
        this.messageStyle = messageStyle;
    }
}
