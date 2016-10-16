/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;



import java.io.IOException;

import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.scene.SceneUtils;

import controller.UserController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;
import view.UserView;

/**
 *
 * @author qmatejka
 */
public class ChatApplication extends Application {

	private static String SERVER_IP = "127.0.0.1";
	private static int SERVER_PORT = 2009;
	private GridPane root;
	private Button btn;
	Label labelUser= new Label("User");
	TextField userField = new TextField();
	Label labelPass= new Label("Password");
	PasswordField  passwordField = new PasswordField();
	@Override
	public void start(Stage primaryStage) {
	
		initPanelConnection();

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {   
				User user = new User();
				UserController ucon = new UserController(user);
				try {
					ucon.connectTo(SERVER_IP, SERVER_PORT);
					System.out.println(userField.getText()+"/"+passwordField.getText());
					if(ucon.userIdentification(userField.getText(),passwordField.getText()));
						ucon.startChat();
						FlowPane pane = new FlowPane();
						pane.setPrefSize(500, 400);
						pane.getChildren().add(new Label("DEUXIEME VU"));
						
						Scene sceneUser = new UserView(pane, pane.getWidth(), pane.getWidth(), ucon);
						root.getChildren().removeAll();
						primaryStage.setTitle("Chat Application Client - " + user.getPseudo());
						primaryStage.setScene(sceneUser);
						primaryStage.show();
						
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		Scene scene = new Scene(root, root.getWidth(), root.getHeight());

		primaryStage.setTitle("Chat Application Client");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	private void initPanelConnection(){
		
		btn= new Button();
		labelUser= new Label("User");
		userField = new TextField();
		labelPass= new Label("Password");
		passwordField = new PasswordField();


		btn.setText("Connection");


		 root = new GridPane();
		root.setPrefSize(500, 400);
		System.out.println(root.getPrefHeight() +"   /  "+root.getPrefWidth());
		root.autosize();
		root.setPadding(new Insets(root.getPrefHeight()/10, 10,  10, root.getPrefWidth()/3));
		root.setVgap(5);
		root.setHgap(5);


		root.getChildren().addAll(labelUser,userField);
		GridPane.setConstraints(labelUser, 0, 1);
		GridPane.setConstraints(userField, 0, 2);
		root.getChildren().addAll(labelPass,passwordField);
		GridPane.setConstraints(labelPass, 0, 3);
		GridPane.setConstraints(passwordField, 0, 4);
		root.getChildren().add(btn);

		GridPane.setConstraints(btn, 0, 5);
		
	}
}
