/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author qmatejka
 */
public class StringMessage implements Serializable, Message{

    private static final long serialVersionUID = 1L;
      
    private String senderPseudo;
    private String content;

    public StringMessage(String sender, String content) {
        this.senderPseudo = sender;
        this.content = content;
    }

    @Override
    public String getSender() {
        return senderPseudo;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "StringMessage{" + senderPseudo + ": " + content + '}';
    }
    
    

}
