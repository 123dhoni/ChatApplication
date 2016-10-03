/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conversation;
import model.Message;
import model.StringMessage;
import model.User;

/**
 *
 * @author qmatejka
 */
public class ClientEmission implements Runnable{

    private ObjectOutputStream out;
    private Scanner sc = null;
    private User user;
    private Conversation conv;

    public ClientEmission(ObjectOutputStream out, User user, Conversation conv) {
        this.out = out;	
        this.user = user;
        this.conv = conv;
    }
    
    @Override
	public void run() {
            sc = new Scanner(System.in);
            String text = null;
            Message m = null;
            while(true){
                text = sc.nextLine();
                m = new StringMessage(user, text);
                conv.addMessage(m);
                try {
                    out.writeObject(conv);
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(ClientEmission.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
	}
    
}
