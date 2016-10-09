/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChatServer;
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
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;	
    private String login, password;
    
    private ChatServer serv;

    /**
     * Reception login et mot de passe client 
     * Checking match with users.txt
     * Send positiv or negative answer to client
     * @param s 
     * @param dataBank 
     */
    public ServerClientAuthentification(Socket s, HashMap<String, String> dataBank, ChatServer server) {
        socket = s;
        clientsPasswords = dataBank;
        serv = server;
    }
    
    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
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
                    serv.addOutput(login, output);
                } else {
                    output.writeObject(new StringMessage(null, "Vos informations sont incorrectes "));
                    output.flush();
                }
            }
            Message message;
            while(true){
                message = (Message)input.readObject();
                System.out.println(((StringMessage)message).toString());
                serv.broadcast(message);
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
    
}
