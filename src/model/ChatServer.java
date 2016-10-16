package model;


import controller.IdentificationException;
import controller.ServerClientAuthentification;
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
                    myServer.waitingForClients();
		} catch (IdentificationException | IOException e) {
                    System.out.println(e.getMessage());
		}
	}
	
	private HashMap<String,String> clientLoginPassword;
	private Stack<Socket> socketClients;
	private ServerSocket socketServer;
	private Stack<Conversation> conversations;
	
	public ChatServer(int port) throws IdentificationException, IOException  {
            this.socketServer=new ServerSocket(port);

    		System.out.println(""+Calendar.getInstance().getTime() +" : SERVER'S INITIALISATION ");
            initClients();
            initConversations();
	}

        public void waitingForClients() throws IOException{
            Socket s;
            while(true){
                s = socketServer.accept();
                Thread connection = new Thread(new ServerClientAuthentification(s, clientLoginPassword,conversations));
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
                System.out.println("\t"+Calendar.getInstance().getTime() +" : [User hashmap have finished to load.] ");
                System.out.println("\t\t"+this.clientLoginPassword.toString());
	}
	/**
	 * Init the conversation from a file where Conversation are serialized.
	 *  We can expect that the server takes time to launch due to data to load.
	 */
	private void initConversations(){
		this.conversations= new Stack<Conversation>();
		this.conversations.push(new Conversation(new User("Admin"), "Bienvenue"));
		this.conversations.push(new Conversation(new User("Admin"), "Geek"));
		this.conversations.push(new Conversation(new User("Admin"), "Wow c'était mieux avant ?"));
		
		System.out.println("\t"+Calendar.getInstance().getTime() +" : [Conversations have finished to load.] ");
		
	}
}