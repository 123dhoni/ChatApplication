/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author qmatejka
 */
public class Conversation {
    
    private float id;
    private Stack<Message> messages = new Stack<Message>();
    private ArrayList<User> users = new ArrayList<User>();
    
    
    public Conversation(){
        
    }
    
    
    
}
