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
import model.StringMessage;
import model.User;

/**
 *
 * @author qmatejka
 */
public class ClientEmission implements Runnable{
    
    //private ObjectOutputStream sOutput;
    private PrintWriter sOutput;
    private User user;

    public ClientEmission(PrintWriter sOutput, User user) {
        this.sOutput = sOutput;
        this.user = user;
        System.out.println("Constructor de l'emission");
    }
    
    @Override
    public void run() {
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        while(!msg.equals("/quit")){
            sOutput.println(msg);
            sOutput.flush();
            System.out.print(">");
            msg = sc.nextLine();
        }
    }
    
    
    
}
