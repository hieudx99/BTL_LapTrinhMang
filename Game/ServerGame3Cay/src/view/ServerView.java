/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ServerControl;

/**
 *
 * @author hieudx
 */
public class ServerView {

    public ServerView() {
        new ServerControl();
        
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
