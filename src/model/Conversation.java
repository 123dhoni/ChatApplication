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

    
}
