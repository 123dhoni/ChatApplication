/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author qmatejka
 */
public class ClientChatService implements Runnable{

    private User user;
    private Socket socket;
    private ObjectInputStream in = null;
    //private ObjectOutputStream out = null;
    private PrintWriter out;

    public ClientChatService(User user, Socket s, PrintWriter out) {
        System.out.println("creation service");
        this.out = out;
        this.user = user;
        this.socket = s;
    }
    
    public ClientChatService(Socket s){
        try {
            this.user = new User("toto");
        } catch (IOException ex) {
            Logger.getLogger(ClientChatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.socket = s;
    }
    
    
    @Override
    public void run() {
        System.out.println("init streams");
        System.out.println("streams ok");
        Thread emission = new Thread(new ClientEmission(out, user));
        System.out.println(emission.getState());
        emission.start();
        System.out.println(emission.getState());
    }
    
}
