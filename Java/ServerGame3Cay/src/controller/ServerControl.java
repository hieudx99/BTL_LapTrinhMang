/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author hieudx
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Credential;
import model.Message;
import model.Player;

public class ServerControl {

    private Connection con;
    private ServerSocket myServer;
    private int serverPort = 8888;
    private List<Credential> listClient = new ArrayList<>();

    public ServerControl() {
        getDBConnection("laptrinhmang", "root", "b18dcdt073");
        openServer(serverPort);
        while (true) {
            listenning();
        }
    }

    private void getDBConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, username, password);
            if (con == null) {
                System.out.println("Connect to DB fail");
            } else {
                System.out.println("Connected to DB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openServer(int portNumber) {
        try {
            myServer = new ServerSocket(portNumber);
            if (myServer == null) {
                System.out.println("Open port fail");
            } else {
                System.out.println("Port was opened");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void listenning() {
        try {
            System.out.println("Listenning...");
            Socket client = myServer.accept();
            System.out.println("Đã kết nối với: " + client);
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    private boolean checkLogin(Player player) {
        boolean result = false;
        String sql = "SELECT * FROM tblPlayer WHERE username = '"
                + player.getUsername() + "' AND password = '"
                + player.getPassword() + "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int point = rs.getInt("point");
                String status = rs.getString("status");
                int firstRankTime = rs.getInt("firstRankTime");
                int secondRankTime = rs.getInt("secondRankTime");
                int thirdRankTime = rs.getInt("thirdRankTime");
                int lastRankTime = rs.getInt("lastRankTime");
                player.setId(id);
                player.setPoint(point);
                player.setStatus(status);
                player.setFirstRankTime(firstRankTime);
                player.setSecondRankTime(secondRankTime);
                player.setThirdRankTime(thirdRankTime);
                player.setLastRankTime(lastRankTime);
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    private List<Player> getListPlayer() {
        List<Player> listPlayer = new ArrayList<>();
        for (Credential cred : listClient) {
            listPlayer.add(cred.getPlayer());
        }
        return listPlayer;
    }

    class ClientHandler extends Thread {

        private Socket client;
        private Player player;
        //ObjectInputStream ois;
        //ObjectOutputStream oos;

        public ClientHandler(Socket client) {
            this.client = client;
            //this.ois = new ObjectInputStream(client.getInputStream());
            //this.oos = new ObjectOutputStream(client.getOutputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    //ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    Message rcd_msg = (Message) ois.readObject();
                    //Tao send_message gui client co Msg = receive message
                    Message send_msg = new Message();
                    switch (rcd_msg.getMsg()) {
                        case "login":
                            Player p = (Player) rcd_msg.getObj();
                            boolean result = checkLogin(p);
                            if (result == true) {
                                send_msg.setMsg(rcd_msg.getMsg());
                                send_msg.setObj(p);
                                this.player = p;
                                Credential credential = new Credential(client, player);
                                listClient.add(credential);
                            } else {
                                send_msg.setMsg(rcd_msg.getMsg());
                                send_msg.setObj(null);
                            }
                            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                            oos.writeObject(send_msg);
                            break;
                        case "getListPlayer":
                            List<Player> listPlayer = getListPlayer();
                            send_msg.setMsg(rcd_msg.getMsg());
                            send_msg.setObj(listPlayer);
                            ObjectOutputStream oos1 = new ObjectOutputStream(client.getOutputStream());
                            oos1.writeObject(send_msg);
                            for (Credential cred : listClient) {
                                if (cred.getClient() != client) {
                                    send_msg.setMsg("loadListPlayer");
                                    ObjectOutputStream oos2 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                    oos2.writeObject(send_msg);
                                }
                            }
                            break;
                        case "inviteToPlay":
                            Player toPlayer = (Player) rcd_msg.getObj();
                            Player fromPlayer = null;
                            for (Credential cred : listClient) {
                                if (client == cred.getClient()) {
                                    fromPlayer = cred.getPlayer();
                                    break;
                                }
                            }
                            for (Credential cred : listClient) {
                                if (cred.getPlayer().getId() == toPlayer.getId()) {
                                    send_msg.setMsg(rcd_msg.getMsg());
                                    send_msg.setObj(fromPlayer);
                                    ObjectOutputStream oos3 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                    oos3.writeObject(send_msg);
                                    break;
                                }
                            }
                            break;
                        //no la thang nay
                        case "acceptToPlay":
                            Player fromPlayer1 = (Player) rcd_msg.getObj();
                            for (Credential cred : listClient) {
                                if (cred.getPlayer().getId() == fromPlayer1.getId()) {
                                    send_msg.setMsg(rcd_msg.getMsg());
                                    send_msg.setObj(null);
                                    ObjectOutputStream oos4 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                    oos4.writeObject(send_msg);
                                    break;
                                }
                            }
                           break; 
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
