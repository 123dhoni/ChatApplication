/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author qmatejka
 */
public interface Message {
    
    public Object getContent();
    
    public User getSender();

    @Override
    public String toString();
    
    
}
