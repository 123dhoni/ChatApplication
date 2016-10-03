package controller;



import model.ChatServer;
import java.io.IOException;
import java.net.Socket;

/**
 * @since 3.2
 *
 */

public class AcceptConnection implements Runnable {

		private ChatServer chat;
		private Socket socket;
		public Thread t1;
		
		/**
		 * @throws Exception 
		 * 
		 */
		public AcceptConnection(ChatServer chat, String login, String mdp) throws IdentificationException {
			// TODO Auto-generated constructor stub
			this.chat=chat;
			//if(!chat.canConnect(login, mdp)) throw new IdentificationException(login,mdp);
			//System.out.println("Allan connect� "+chat.getSocketServer().getLocalPort());
		
		}
		
		public void run(){
			System.out.println("Connexion");
			try{
                            while(true){
                                    //On r�cupere la socket de la premi�re connexion
                                    this.socket=chat.getSocketServer().accept();	
                                    System.out.println("Socket r�cup�r�"); 

                            }
				
			}catch(IOException e){
                            System.out.println(e);
			}
		}
}
