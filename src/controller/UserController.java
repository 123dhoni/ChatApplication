/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.Socket;
import model.User;

/**
 *
 * @author qmatejka
 */
public class UserController {
    
    private User user;
    private Socket socket = null;

    public UserController(User user) {
        this.user = user;
    }
    
    public void connectTo(String serverIP, int serverPort) throws IOException{
        //Connexion au serveur
        socket = new Socket(serverIP, serverPort);
        System.out.println("Connexion réseau établie!");
    }
    
    public boolean userIdentification(String login, String password){
        return true;
    }
}
