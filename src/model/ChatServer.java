package model;


import controller.IdentificationException;
import controller.ServerClientAuthentification;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @since 3.2
 *
 */
public class ChatServer {
	public static void main(String[] args) {
		try {
                    ChatServer myServer = new ChatServer(2009);
                    myServer.waitingForClients();
		} catch (IdentificationException | IOException e) {
                    System.out.println(e.getMessage());
		}
	}
	
	private HashMap<String,String> clientLoginPassword;
        private HashMap<User,ObjectOutputStream> clientOutput;
	private Stack<Socket> socketClients;
	private ServerSocket socketServer;
	
	
	public ChatServer(int port) throws IdentificationException, IOException  {
            this.socketServer=new ServerSocket(port);
            this.clientOutput = new HashMap<>();
            initClients();
	}

        public void addOutput(String login, ObjectOutputStream cOutput){
            User u = new User();
            u.setPseudo(login);
            clientOutput.put(u, cOutput);
        }
        
        public void broadcast(Message msg){
            ObjectOutputStream cOutput;
            for(User u:clientOutput.keySet()){
                if(msg.getSender().equals(u.getPseudo())){}
                else{
                    try {
                        cOutput = clientOutput.get(u);
                        cOutput.writeObject(msg);
                        cOutput.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        public void waitingForClients() throws IOException{
            Socket s;
            while(true){
                s = socketServer.accept();
                Thread connection = new Thread(new ServerClientAuthentification(s, clientLoginPassword, this));
		connection.start();
            }
        }
        
	public void linkClient(Socket client){
            this.socketClients.add(client);
	}
        
	/**
	 * @return Returns the chatServer.
	 */
	public ServerSocket getSocketServer() {
            return socketServer;
	}
	
	private void initClients() throws FileNotFoundException, IOException{
		this.clientLoginPassword = new HashMap<>();
                BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream("users.txt")));
                String login, password;
                while(data.ready()){
                    String[] values = data.readLine().split(";");
                    login = values[0];
                    password = values[1];
                    this.clientLoginPassword.put(login, password);
                }
                System.out.println(this.clientLoginPassword.toString());
	}
}