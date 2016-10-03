/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author qmatejka
 */
public class ClientAuthentification implements Runnable{
    
    private boolean authentifier = false;
    private Socket socket = null;
    private HashMap<String, String> clientsPasswords;
    private Scanner sc;
    private PrintWriter output = null;
    private BufferedReader input = null;	
    private String login = null, password;

    /**
     * Reception login et mot de passe client 
     * Checking match with users.txt
     * Send positiv or negative answer to client
     * @param s 
     * @param dataBank 
     */
    public ClientAuthentification(Socket s, HashMap<String, String> dataBank) {
        socket = s;
        clientsPasswords = dataBank;
    }
    
    @Override
    public void run() {
        try {
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);
            

            while(!authentifier ){
                output.println("LOGIN : ");
                output.flush();
                login = input.readLine();

                output.println("PASSWORD : ");
                output.flush();
                password = input.readLine();

                if(checkUser(login, password)){
                    System.out.println(login + " connecté "); 
                    authentifier = true;
                    output.println("connecte");
                    output.flush();
                } else {
                    output.println("Vos informations sont incorrectes "); 
                    output.flush();
                }
            }
        } catch (IOException e) {
            System.err.println(login+" ne répond pas !");
        }
	
    }
    
    public boolean checkUser(String login, String password) throws IOException{
        return clientsPasswords.containsKey(login)&&
               clientsPasswords.get(login).equals(password);
    }
    
    public void userSummary() throws IOException{
        output.println("Choose a conversation : \n"
                + "1 - Conversation with test");
        output.flush();
        String choice = input.readLine();
        if (Integer.valueOf(choice). == 1)
            
    }
    
}
