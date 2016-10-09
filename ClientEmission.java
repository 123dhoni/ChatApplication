/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.StringMessage;
import model.User;

/**
 *
 * @author qmatejka
 */
public class ClientEmission implements Runnable{
    
    private ObjectOutputStream sOutput;
    private User user;

    public ClientEmission(ObjectOutputStream sOutput, User user) {
        this.sOutput = sOutput;
        this.user = user;
        System.out.println("\t" + Calendar.getInstance().getTime() + " [Emission's Constructor]");
    }
    
    @Override
    public void run() {
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        while(!msg.equals("/quit")){
            try {
                sOutput.writeObject(new StringMessage(user.getPseudo(), msg,UUID.randomUUID()));
                sOutput.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientEmission.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.print(">");
            msg = sc.nextLine();
        }
    }
    
    
    
}
