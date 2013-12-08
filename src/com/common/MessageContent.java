/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.util.Date;

/**
 *
 * @author arith
 */
public class MessageContent {

    private String text;

    public MessageContent(String text, Date date) {
        this.text = text;
        this.date = date;
    }
    private Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
