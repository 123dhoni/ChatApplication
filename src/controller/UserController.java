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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author qmatejka
 */
public class UserController {
    
    private User user;
    private Socket socket = null;
    private PrintWriter output;
    

    public UserController(User user) {
        this.user = user;
    }
    
    public void connectTo(String serverIP, int serverPort) throws IOException{
        //Connexion au serveur
        socket = new Socket(serverIP, serverPort);
        System.out.println("Connexion réseau établie!");
    }
    
    public boolean userIdentification() throws IOException{
        output = new PrintWriter(socket.getOutputStream());
	BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	Scanner sc = new Scanner(System.in);
	boolean connect = false;
        String login, password;
        
        while(!connect){
            System.out.println(input.readLine());
            login = sc.nextLine();
            output.println(login);
            output.flush();

            System.out.println(input.readLine());
            password = sc.nextLine();
            output.println(password);
            output.flush();

            if(input.readLine().equals("connecte")){
                System.out.println("Je suis connecté"); 
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
        Thread service = new Thread(new ClientChatService(user, socket, output));
        System.out.println(service.getState());
        service.start();
        System.out.println(service.getState());
    }
}
