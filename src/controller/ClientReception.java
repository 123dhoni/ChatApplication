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
    }
    
    @Override
    public void run() {
            String msg="";
            while(true){
                try {
                    msg = "\t\t\t\t" + ((StringMessage)sInput.readObject()).toString() + "<";
                    System.out.println(msg);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ClientReception.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        
    }
    
    
}
