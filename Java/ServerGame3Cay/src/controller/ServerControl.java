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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.Player;

public class ServerControl {

    private Connection con;
    private ServerSocket myServer;
    private int serverPort = 8888;

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
            Socket clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            Message rcd_msg = (Message) ois.readObject();
            if (rcd_msg.getMessage().equalsIgnoreCase("check login")) {
                Player player = (Player) rcd_msg.getObject();
                boolean result = checkUser(player);
                Message send_msg = new Message();
                if (result == true) {
                    send_msg.setMessage("ok");
                    send_msg.setObject(player);
                    oos.writeObject(send_msg);
                } else if (result == false) {
                    send_msg.setMessage("false");
                    send_msg.setObject(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkUser(Player player) {
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
}
