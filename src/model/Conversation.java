/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

/**
 *
 * @author qmatejka
 */
public class Conversation implements Serializable {
    

	private static final long serialVersionUID = 1L;

	private UUID id;
    private String title;
    private Stack<Message> messages = new Stack<Message>();
    private ArrayList<User> users = new ArrayList<User>();
    
    
    public Conversation(ArrayList<User> joiners){
        this.id = UUID.randomUUID();
        this.users=joiners;
        for(int i=0;i<users.size();i++){
            if(i!=0)
                this.title += ", ";
            this.title += users.get(i).getPseudo();
        }
    }
    public Conversation(User creator, String title){
    	this.id=UUID.randomUUID();
    	this.title=title;
    	this.messages.push(new StringMessage(creator.getPseudo(), "Bienvenue dans la salle de discussion : "+ title , this.id));
    }
    
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Stack<Message> getMessages() {
		return messages;
	}
	public void setMessages(Stack<Message> messages) {
		this.messages = messages;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    
    
}
