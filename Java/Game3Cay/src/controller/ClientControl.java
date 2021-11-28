/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.Player;
import view.LoginView;
import view.PlayerHomeView;
import view.PlayingRoomView;

/**
 *
 * @author hieudx
 */
public class ClientControl {

    private LoginView loginView;
    private PlayerHomeView playerHomeView;
    private PlayingRoomView playingRoomView;
    private Socket mySocket;
    private String serverHost = "localhost";
    private int serverPort = 8888;
    //private ObjectOutputStream oos = null;
    //private ObjectInputStream ois = null;

    public ClientControl() {
        openConnection();
        ServerHandler serverHandler = new ServerHandler(mySocket);
        serverHandler.start();
        this.loginView = new LoginView();
        loginView.setVisible(true);
        loginView.addActionListener(new LoginListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(loginView.getBtnLogin())) {
                Player player = loginView.getPlayer();
                Message send_msg = new Message();
                send_msg.setMsg("login");
                send_msg.setObj(player);
                sendData(send_msg);

            }
        }
    }
    
    class PlayerHomeViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(playerHomeView.getBtnInvite())) {
                Player p = playerHomeView.getPlayerToInvite();
                //
                Message send_msg = new Message();
                send_msg.setMsg("inviteToPlay");
                send_msg.setObj(p);
                sendData(send_msg);
            }
        }
        
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

        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(message);
            System.out.println("send message: " + message.getMsg()) ;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

//    public static Message listenning() {
//        Message result = null;
//        try {
//            ois = new ObjectInputStream(mySocket.getInputStream());
//            Object o = ois.readObject();
//            if (o instanceof Message) {
//                result = (Message) o;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return result;
//    }
//    
//    public Message receiveData() {
//        Message result = null;
//        try {
//            ois = new ObjectInputStream(mySocket.getInputStream());
//            Object o = ois.readObject();
//            if (o instanceof Message) {
//                result = (Message) o;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return result;
//    }

    public boolean closeConnection() {
        try {
            mySocket.close();
            //ois.close();
            //oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    class ServerHandler extends Thread {

        private Socket server;
        private Player player;
        //ObjectInputStream ois;

        public ServerHandler(Socket server) {
            this.server = server;
            //ois = new ObjectInputStream(this.server.getInputStream());
        }

        @Override
        public void run() {
            //ObjectInputStream ois = null;
            try {
                while (true) {   
                    ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
                    //oos = new ObjectOutputStream(server.getOutputStream());
                    Message rcd_msg = (Message) ois.readObject();
                    switch (rcd_msg.getMsg()) {
                        case "login":
                            if (rcd_msg.getObj() != null) {
                                this.player = (Player) rcd_msg.getObj();
                                loginView.showMessage("login successfully");
                                loginView.dispose();
                                //sau khi dang nhap thanh cong gui request den server yeu cau lay list player online
                                Message send_msg = new Message();
                                send_msg.setMsg("getListPlayer");
                                send_msg.setObj(null);
                                ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                                //sendData(send_msg);
                                oos.writeObject(send_msg);
                            } else {
                                loginView.showMessage("wrong username or password");
                            }
                            break;
                        case "getListPlayer":
                            if (rcd_msg.getObj() != null) {
                                List<Player> listPlayer = (List<Player>) rcd_msg.getObj();
                                playerHomeView = new PlayerHomeView(this.player, listPlayer);
                                playerHomeView.addActionListener(new PlayerHomeViewListener());
                                playerHomeView.setVisible(true);
                            }
                            break;
                        //Sau khi co 1 player dang nhap thi cac player khac reload list player online
                        case "loadListPlayer": 
                            if (playerHomeView.isVisible()) {
                                List<Player> listPlayer = (List<Player>) rcd_msg.getObj();
                                playerHomeView.setListPlayer(listPlayer);
                                playerHomeView.loadListPlayer();
                            }
                            break;
                        case "inviteToPlay":
                            Player fromPlayer = (Player) rcd_msg.getObj();
                            if (playerHomeView.showConfirmDialog(fromPlayer)) {
                                playerHomeView.dispose();
                                playingRoomView = new PlayingRoomView();
                                playingRoomView.setVisible(true);
                                Message send_msg = new Message();
                                send_msg.setMsg("acceptToPlay");
                                send_msg.setObj(fromPlayer);
                                //sendData(send_msg);
                                ObjectOutputStream oos1 = new ObjectOutputStream(server.getOutputStream());
                                oos1.writeObject(send_msg);
                                
                            }
                            break;
                        case "acceptToPlay": 
                            playerHomeView.dispose();
                            playingRoomView = new PlayingRoomView();
                            playingRoomView.setVisible(true);
                            break;
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
