/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.UserController;
import java.io.IOException;

/**
 * SERVEUR IP : 127.0.0.1
 * PORT : 2856
 * @author qmatejka
 */
public class User {
    
    private static String SERVER_IP = "127.0.0.1";
    private static int SERVER_PORT = 2009;
    
    private Thread connexion;
    
    private int ID;
    private String pseudo;
    private String description;

    public User(String pseudo) throws IOException {
        this.pseudo = pseudo;         
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    public static void main(String[]args) throws IOException{
        User test = new User("test");
        UserController ucon = new UserController(test);
        ucon.connectTo(SERVER_IP, SERVER_PORT);
        ucon.userIdentification();
    }
    
    
}
