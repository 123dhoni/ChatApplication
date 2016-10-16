/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UserController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author qmatejka
 */
public class UserView extends Scene{
	private UserController uc;
	public UserView(Parent root, double width, double height, UserController uc) {
		super(root, width, height);
		this.uc=uc;
	}
    
}
