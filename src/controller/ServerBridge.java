/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

/**
 *
 * @author qmatejka
 */
public class ServerBridge implements Runnable{
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message = null, login = null;

    public ServerBridge(ObjectOutputStream out, ObjectInputStream in){
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        Message m = null;
        while(true){
            try {
                m = (Message)in.readObject();
                System.out.println("Message recu de " + m.getSender());
                out.writeObject(m);
                System.out.println("Message transféré");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
