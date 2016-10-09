/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conversation;

/**
 *
 * @author qmatejka
 */
public class ConversationController implements Runnable{
    
    private Conversation conv;
    private ArrayList<Socket> sockets = new ArrayList<Socket>();

    public ConversationController(Conversation conv, ArrayList<Socket> sockets) {
        this.conv = conv;
        this.sockets = sockets;
    }

    @Override
    public void run() {
            
        
    }
    
    
    
}
