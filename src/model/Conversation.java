/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

/**
 *
 * @author qmatejka
 */
public class Conversation {
    
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
    
    public void addUser(User u){
        users.add(u);
    }
    
    public void addMessage(Message m){
        messages.add(m);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Stack<Message> getMessages() {
        return messages;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    @Override
    public String toString(){
        Message last = messages.pop();
        String desc = "|************************\n"
                    + "| " + title + "\n"
                    + "|************************\n"
                    + "|\n"
                    + "|" + last.getSender() + ": " + last.getContent();
        return desc;
    }

}
