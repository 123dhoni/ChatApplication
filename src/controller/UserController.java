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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.StringMessage;
import model.User;

/**
 *
 * @author qmatejka
 */
public class UserController {
    
    private User user;
    private Socket socket = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public UserController(User user) {
        this.user = user;
    }
    
    public void connectTo(String serverIP, int serverPort) throws IOException{
        //Connexion au serveur
        socket = new Socket(serverIP, serverPort);
        System.out.println("Connexion réseau établie!");
    }
    
    public boolean userIdentification() throws IOException, ClassNotFoundException{
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());	
	Scanner sc = new Scanner(System.in);
	boolean connect = false;
        String login, password;
        
        while(!connect){
            System.out.println(readMessage());
            login = sc.nextLine();
            System.out.println(readMessage());
            password = sc.nextLine();
            output.writeObject(new StringMessage(user.getPseudo(), login + "/" + password));
            output.flush();

            if(readMessage().equals("connecte")){
                user.setPseudo(login);
                connect = true;
            } else {
                System.err.println("Vos informations sont incorrectes "); 
            }
        }
        
        return true;
    }
    
    //Création des threads emissions receptions
    public void startChat(){
        System.out.println("Bienvenue dans la salle de chat " + user.getPseudo() + "!");
        Thread service = new Thread(new ClientChatService(user, socket, input, output));
        service.start();
    }
    
    public String readMessage(){
        try {
            return (String)((StringMessage)input.readObject()).getContent();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error message";
    }
}
