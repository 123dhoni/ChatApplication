/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author qmatejka
 */
public class StringMessage implements Message{
    
    private User sender;
    private String content;

    public StringMessage(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    @Override
    public User getSender() {
        return sender;
    }

    @Override
    public String getContent() {
        return content;
    }

}
