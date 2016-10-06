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
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.StringMessage;

/**
 *
 * @author qmatejka
 */
public class ServerClientAuthentification implements Runnable{
    
    private boolean authentifier = false;
    private Socket socket = null;
    private HashMap<String, String> clientsPasswords;
    private Scanner sc;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;	
    private String login, password;

    /**
     * Reception login et mot de passe client 
     * Checking match with users.txt
     * Send positiv or negative answer to client
     * @param s 
     * @param dataBank 
     */
    public ServerClientAuthentification(Socket s, HashMap<String, String> dataBank) {
        socket = s;
        clientsPasswords = dataBank;
    }
    
    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            sc = new Scanner(System.in);
            String userInfos = null;

            while(!authentifier ){
                output.writeObject(new StringMessage("", "LOGIN : "));
                output.flush();

                output.writeObject(new StringMessage("", "PASSWORD : "));
                output.flush();
                
                userInfos = (String)((StringMessage)input.readObject()).getContent();

                if(checkUser(userInfos)){
                    System.out.println(login + " connecté "); 
                    authentifier = true;
                    output.writeObject(new StringMessage(null, "connecte"));
                    output.flush();
                } else {
                    output.writeObject(new StringMessage(null, "Vos informations sont incorrectes "));
                    output.flush();
                }
            }
            String msg ="";
            while(true){
                msg = ((StringMessage)input.readObject()).toString();
                System.out.println(msg);
            }
        } catch (IOException e) {
            System.err.println("user ne répond pas !");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerClientAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }
	
    }
    
    public boolean checkUser(String userInfos) throws IOException{
        String[] values = userInfos.split("/");
        login = values[0];
        password = values[1];
        return clientsPasswords.containsKey(login)&&
               clientsPasswords.get(login).equals(password);
    }
    
    public void userSummary() throws IOException{

        
    }
    
}
