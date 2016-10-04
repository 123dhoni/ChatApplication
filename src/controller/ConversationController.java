/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conversation;

/**
 *
 * @author qmatejka
 */
public class ConversationController implements Runnable{
    
    private Conversation conv;
    private ArrayList<Socket> sockets = new ArrayList<Socket>();

    public ConversationController(Conversation conv, ArrayList<Socket> sockets) {
        this.conv = conv;
        this.sockets = sockets;
    }

    @Override
    public void run() {
        Runnable bridge1, bridge2;
        ObjectInputStream in1, in2;
        ObjectOutputStream out1, out2;
        for(int i=0;i<sockets.size()-1;i++){
            try {
                in1 = new ObjectInputStream(sockets.get(i).getInputStream());
                out1 = new ObjectOutputStream(sockets.get(i).getOutputStream());
                in2 = new ObjectInputStream(sockets.get(i+1).getInputStream());
                out2 = new ObjectOutputStream(sockets.get(i+1).getOutputStream());

                bridge1 = new ServerBridge(out2, in1);
                bridge1.run();
                bridge2 = new ServerBridge(out1, in2);
                bridge2.run();
            } catch (IOException ex) {
                Logger.getLogger(ConversationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
}
