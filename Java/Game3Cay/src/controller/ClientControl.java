/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Message;
import model.Player;

/**
 *
 * @author hieudx
 */
public class ClientControl {

    private Socket mySocket;
    private String serverHost = "localhost";
    private int serverPort = 8888;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null; 
    
    public ClientControl() {
        
    }
    
    public Socket openConnection() {
        try {
            mySocket = new Socket(serverHost, serverPort);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return mySocket;
    }
    
    public boolean sendData(Message message) {
        try {
            if (oos == null) {
                try {
                    oos = new ObjectOutputStream(mySocket.getOutputStream());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
             
            oos.writeObject(message);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Message receiveData() {
        Message result = null;
        try {
            ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            if (o instanceof Message) {
                result = (Message) o;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }
    
    public boolean closeConnection() {
        try {
            mySocket.close();
            ois.close();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
