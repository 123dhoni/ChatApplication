/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.StringMessage;

/**
 *
 * @author qmatejka
 */
public class ClientReception implements Runnable{

    private ObjectInputStream sInput;

    public ClientReception(ObjectInputStream sInput) {
        this.sInput = sInput;
        System.out.println("Constructor de reception");
    }
    
    @Override
    public void run() {
            String msg="";
            while(true){
                try {
                    msg = ((StringMessage)sInput.readObject()).toString();
                } catch (IOException ex) {
                    Logger.getLogger(ClientReception.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientReception.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println(msg);
            }
        
    }
    
    
}
