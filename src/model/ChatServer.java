package model;


import controller.IdentificationException;
import controller.AcceptConnection;
import java.io.IOException;
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
			
		} catch (IdentificationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	private HashMap<String,String> clients;
	private Stack<Socket> socketClients;
	private ServerSocket socketServer;
	
	
	public ChatServer(int port) throws IdentificationException, IOException  {
		this.socketServer=new ServerSocket(port);
		initClients();
		Thread connection = new Thread(new AcceptConnection(this, "Allan", "test"));
		connection.run();
	}

	public boolean canConnect(String login, String password){
		if(clients.containsKey(login)&&clients.get(login).toString().equals(password)) return true;
		return false;
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
	
	private void initClients(){
		this.clients= new HashMap<String,String>();
		this.clients.put("Allan", "test");
	}
}