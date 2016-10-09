/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.User;

/**
 *
 * @author qmatejka
 */
public class ClientChatService implements Runnable{

    private User user;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientChatService(User user, Socket s, ObjectInputStream input, ObjectOutputStream output) {
        System.out.println("creation service");
        this.user = user;
        this.socket = s;
        in = input;
        out = output;
    }
    
    public ClientChatService(Socket s){
        this.user = new User();
        this.socket = s;
    }
    
    
    @Override
    public void run() {
        System.out.println("init streams");
        System.out.println("streams ok");
        Thread emission = new Thread(new ClientEmission(out, user));
        emission.start();
        Thread reception = new Thread(new ClientReception(in));
        reception.start();  
    }
    
}
