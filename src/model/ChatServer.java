package model;


import controller.IdentificationException;
import controller.AcceptConnection;
import controller.ClientAuthentification;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @since 3.2
 *
 */
public class ChatServer {
	public static void main(String[] args) {
		try {
                    ChatServer myServer = new ChatServer(2009);
                    myServer.waitsForClients();
		} catch (IdentificationException | IOException e) {
                    System.out.println(e.getMessage());
		}
	}
	
	private HashMap<String,String> clientLoginPassword;
	private Stack<Socket> socketClients;
	private ServerSocket socketServer;
	
	
	public ChatServer(int port) throws IdentificationException, IOException  {
            this.socketServer=new ServerSocket(port);
            initClients();
	}

        public void waitsForClients() throws IOException{
            Socket s;
            while(true){
                s = socketServer.accept();
                linkClient(s);
                Thread connection = new Thread(new ClientAuthentification(s, clientLoginPassword));
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