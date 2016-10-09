/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author qmatejka
 */
public class StringMessage implements Serializable, Message{

    private static final long serialVersionUID = 1L;
      
    private String senderPseudo;
    private String content;
    private UUID idConversation;
    /**
     * 
     * @param sender
     * 	Name of the User who sent the message
     * @param content
     * 	Content of the messsage
     * @param idConversation
     * 	Id of the conversation where the message is sent. 
     * 	The uuid is given by the conversation where the user send the message
     */
    public StringMessage(String sender, String content,UUID idConversation) {
        this.senderPseudo = sender;
        this.content = content;
        this.idConversation=idConversation;
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
        return "StringMessage{" + senderPseudo + ": " + content +" IDConv :"+ this.idConversation+'}';
    }
    
    

}
