/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Conversation;
import model.StringMessage;

/**
 *
 * @author qmatejka
 */
public class ClientReception implements Runnable{

    private ObjectInputStream sInput;

    public ClientReception(ObjectInputStream sInput) {
        this.sInput = sInput;
        System.out.println("\t"+Calendar.getInstance().getTime() +" [Reception's controller]");
    }
    
    @Override
    public void run() {
            String msg="";
            while(true){
                try {
                	Object dataReceived = sInput.readObject();
                	System.out.println(dataReceived.getClass().getSimpleName());
                	if(dataReceived.getClass().getSimpleName().equals(StringMessage.class.getSimpleName()))
                		msg = ((StringMessage)dataReceived).toString();
                	else if(dataReceived.getClass().getSimpleName().equals(Stack.class.getSimpleName())){
                		Stack<Conversation> conversations = ((Stack<Conversation>) dataReceived);
                		msg="\\***** Conversations ****/ \n \t Choose where you want to chat  ! \n";
                		int i=1;
                		for (Conversation conversation : conversations) {
                			msg+=i+". || nbMessages : "+conversation.getMessages().size() +" ||"+conversation.getTitle()+"\n" ;
							i++;
						}
                	}
                	
                } catch (IOException ex) {
                    Logger.getLogger(ClientReception.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientReception.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("ClientReception :"+ msg);
            }
        
    }
    
    
}
