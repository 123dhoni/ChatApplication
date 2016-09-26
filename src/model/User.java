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
    
    private String SERVER_IP = "127.0.0.1";
    private int SERVER_PORT = 2009;
    
    private Thread connexion;
    
    private int ID;
    private String pseudo;
    private String description;

    public User(String pseudo) throws IOException {
        this.pseudo = pseudo;         
    }
    
    public static void main(String[]args) throws IOException{
        User test = new User("test");
        UserController ucon = new UserController(test);
        ucon.connectTo("127.0.0.1", 2009);
    }
    
    
}
