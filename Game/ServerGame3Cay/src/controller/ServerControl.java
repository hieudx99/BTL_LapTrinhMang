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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.Card;
import model.Credential;
import model.DealtCard;
import model.Match;
import model.Message;
import model.Player;
import model.PlayerCard;
import model.Table;

public class ServerControl {

    private Connection con;
    private ServerSocket myServer;
    private int serverPort = 8888;
    private List<Credential> listClient = new ArrayList<>();
    private Table playTable;
    private Set<Integer> existedCard = new HashSet<Integer>();

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
        } catch (ClassNotFoundException | SQLException e) {
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
        }
    }

    private void listenning() {
        try {
            System.out.println("Listenning...");
            Socket client = myServer.accept();
            System.out.println("Đã kết nối với: " + client);
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandler.start();
        } catch (IOException e) {
        }
    }

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
                int winTotal = rs.getInt("winTotal");
                int loseTotal = rs.getInt("loseTotal");
                player.setId(id);
                player.setPoint(point);
                player.setWinTotal(winTotal);
                player.setLoseTotal(loseTotal);
                result = true;
            }
        } catch (SQLException ex) {
            result = false;
        }
        return result;
    }

    private List<Player> getListPlayer() {
        List<Player> listPlayer = new ArrayList<>();
        for (Credential cred : listClient) {
            cred.getPlayer().setStatus("Online");
            if (playTable != null) {
                for (Player p : playTable.getListPlayerInTable()) {
                    if (cred.getPlayer().getId() == p.getId()) {
                        cred.getPlayer().setStatus("In game");
                    }
                }
            }
            listPlayer.add(cred.getPlayer());
        }
        return listPlayer;
    }

    private Match createNewMatch() {
        Match match = new Match();
        //set current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        match.setDate(time);
        //end set current date
        match.setTable(playTable);
        List<DealtCard> listDealtCards = new ArrayList<>();
        for (Player p : playTable.getListPlayerInTable()) {
            DealtCard dc = dealtCard(p);
            listDealtCards.add(dc);
        }
        //sau moi van clear set bai da chia 
        existedCard.clear();
        match.setListDealtCard(listDealtCards);
        //check totalValue(diem) de set position
        int player1Point = listDealtCards.get(0).getTotalValue();
        int player2Point = listDealtCards.get(1).getTotalValue();
        //position = 0 => win || 1 => lose || 2 => draw
        if (player1Point > player2Point) {
            listDealtCards.get(0).setPosition(0);
            listDealtCards.get(1).setPosition(1);
        } else if (player2Point > player1Point) {
            listDealtCards.get(0).setPosition(1);
            listDealtCards.get(1).setPosition(0);
        } else {
            listDealtCards.get(0).setPosition(2);
            listDealtCards.get(1).setPosition(2);
        }
        return match;
    }

    private DealtCard dealtCard(Player p) {
        DealtCard dealCard = new DealtCard();
        dealCard.setPlayer(p);
        List<PlayerCard> listPlayerCard = getRandomCards();
        dealCard.setListPlayerCard(listPlayerCard);
        int total = 0;
        for (PlayerCard pc : listPlayerCard) {
            total += pc.getCard().getNumber();
        }
        total = total % 10;
        dealCard.setTotalValue(total);
        return dealCard;
    }

    private List<PlayerCard> getRandomCards() {
        List<PlayerCard> listPlayerCard = new ArrayList<>();
        //random 1 so tu 1 - 36
        Random r = new Random();
        int low = 1;
        int high = 37;
        for (int i = 0; i < 3; i++) {
            int n = r.nextInt(high - low) + low;
            //neu bai da dc chia thi random so khac
            while (existedCard.contains(n)) {
                n = r.nextInt(high - low) + low;
            }
            existedCard.add(n);
            Card card = getCard(n);
            PlayerCard playerCard = new PlayerCard();
            playerCard.setCard(card);
            listPlayerCard.add(playerCard);
        }
        return listPlayerCard;

    }

    private Card getCard(int id) {
        Card card = null;
        String sql = "SELECT * FROM tblCard WHERE id = '" + id + "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int number = rs.getInt("number");
                String type = rs.getString("type");
                card = new Card(id, number, type);
            }
        } catch (SQLException ex) {
        }
        return card;
    }

    private boolean addMatch(Match match) {
        boolean result = false;
        String sqlAddMatch = "INSERT INTO tblMatch(date) VALUES(?)";
        String sqlAddDealtCard = "INSERT INTO tblDealtCard(position, totalValue, tblPlayerid, tblMatchid) VALUES(?,?,?,?)";
        String sqlAddPlayerCard = "INSERT INTO tblPlayerCard(tblCardid, tblDealtCardid) VALUES(?,?)";
        String sqlUpdatePlayerWin = "UPDATE tblPlayer SET point = ?, winTotal = ? WHERE id = ?";
        String sqlUpdatePlayerLose = "UPDATE tblPlayer SET loseTotal = ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sqlAddMatch, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, match.getDate());
            ps.executeUpdate();
            ResultSet generatedMatchID = ps.getGeneratedKeys();
            if (generatedMatchID.next()) {
                match.setId(generatedMatchID.getInt(1));
                for (DealtCard dc : match.getListDealtCard()) {
                    ps = con.prepareStatement(sqlAddDealtCard, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, dc.getPosition());
                    ps.setInt(2, dc.getTotalValue());
                    ps.setInt(3, dc.getPlayer().getId());
                    ps.setInt(4, match.getId());
                    ps.executeUpdate();
                    ResultSet generatedDealtCardID = ps.getGeneratedKeys();
                    if (generatedDealtCardID.next()) {
                        dc.setId(generatedDealtCardID.getInt(1));
                        for (PlayerCard pc : dc.getListPlayerCard()) {
                            ps = con.prepareStatement(sqlAddPlayerCard);
                            ps.setInt(1, pc.getCard().getId());
                            ps.setInt(2, dc.getId());
                            ps.executeUpdate();
                        }
                    }
                    if (dc.getPosition() == 0) {
                        //Cong diem cho player win                        
                        for (Credential cred : listClient) {
                            if (cred.getPlayer().getId() == dc.getPlayer().getId()) {
                                cred.getPlayer().setPoint(cred.getPlayer().getPoint() + 2);
                            }
                        }
                        //end
                        ps = con.prepareStatement(sqlUpdatePlayerWin);
                        ps.setInt(1, dc.getPlayer().getPoint() + 2);
                        ps.setInt(2, dc.getPlayer().getWinTotal() + 1);
                        ps.setInt(3, dc.getPlayer().getId());
                        ps.executeUpdate();
                    } else if (dc.getPosition() == 1) {
                        ps = con.prepareStatement(sqlUpdatePlayerLose);
                        ps.setInt(1, dc.getPlayer().getLoseTotal() + 1);
                        ps.setInt(2, dc.getPlayer().getId());
                        ps.executeUpdate();
                    }
                }
            }

            con.commit();
            result = true;
        } catch (SQLException e) {
            result = false;
            try {
                con.rollback();
            } catch (SQLException ex) {
                result = false;
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
            }
        }
        return result;

    }

    class ClientHandler extends Thread {

        private Socket client;
        private Player player;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    Message rcd_msg = (Message) ois.readObject();
                    //Tao send_message gui client co Msg = receive message
                    Message send_msg = new Message();
                    switch (rcd_msg.getMsg()) {
                        case "login":
                            Player p = (Player) rcd_msg.getObj();
                            boolean result = checkLogin(p);
                            if (result == true) {
                                this.player = p;
                                Credential credential = new Credential(client, player);
                                listClient.add(credential);
                                send_msg.setMsg(rcd_msg.getMsg());
                                send_msg.setObj(p);
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
                        case "refreshListPlayer":
                            List<Player> listPlayer1 = getListPlayer();
                            send_msg.setMsg(rcd_msg.getMsg());
                            send_msg.setObj(listPlayer1);
                            ObjectOutputStream oos3 = new ObjectOutputStream(client.getOutputStream());
                            oos3.writeObject(send_msg);
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
                                    ObjectOutputStream oos4 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                    oos4.writeObject(send_msg);
                                    break;
                                }
                            }
                            break;
                        case "acceptToPlay":
                            Player fromPlayer1 = (Player) rcd_msg.getObj();
                            Player toPlayer1 = null;
                            for (Credential cred : listClient) {
                                if (client == cred.getClient()) {
                                    toPlayer1 = cred.getPlayer();
                                    break;
                                }
                            }
                            playTable = new Table();
                            playTable.getListPlayerInTable().add(fromPlayer1);
                            playTable.getListPlayerInTable().add(toPlayer1);
                            for (Credential cred : listClient) {
                                if (cred.getPlayer().getId() == fromPlayer1.getId()) {
                                    send_msg.setMsg(rcd_msg.getMsg());
                                    send_msg.setObj(playTable);
                                    ObjectOutputStream oos4 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                    oos4.writeObject(send_msg);
                                    break;
                                }
                            }
                            break;
                        case "chatting":
                            String chatMsg = (String) rcd_msg.getObj();
                            Player chatFrom = null;
                            for (Player pl : playTable.getListPlayerInTable()) {
                                if (pl.getId() == player.getId()) {
                                    chatFrom = pl;
                                }
                            }
                            for (Credential cred : listClient) {
                                for (Player pl : playTable.getListPlayerInTable()) {
                                    if (pl.getId() != chatFrom.getId() && cred.getPlayer().getId() == pl.getId()) {
                                        send_msg.setMsg(rcd_msg.getMsg());
                                        List<Object> spChat = new ArrayList<>();
                                        spChat.add(chatFrom);
                                        spChat.add(chatMsg);
                                        send_msg.setObj(spChat);
                                        ObjectOutputStream oos2 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                        oos2.writeObject(send_msg);
                                    }
                                }
                            }
                            break;
                        case "readyToPlay":
                            Player p1 = (Player) rcd_msg.getObj();
                            int nbr_ready = playTable.getNbr_ready() + 1;
                            playTable.setNbr_ready(nbr_ready);
                            //neu ca 2 da san sang
                            if (nbr_ready == playTable.getListPlayerInTable().size()) {
                                Match match = createNewMatch();
                                if (addMatch(match)) {
                                    send_msg.setMsg("playing");
                                    send_msg.setObj(match);
                                    for (Credential cred : listClient) {
                                        for (Player pl : playTable.getListPlayerInTable()) {
                                            if (pl.getId() == cred.getPlayer().getId()) {
                                                ObjectOutputStream oos2 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                                oos2.writeObject(send_msg);
                                            }
                                        }
                                    }
                                    playTable.setNbr_ready(0);
                                }
                            } //neu moi chi co 1 nguoi san sang
                            else {
                                Player p2 = null;
                                for (Player pl : playTable.getListPlayerInTable()) {
                                    if (pl.getId() != p1.getId()) {
                                        p2 = pl;
                                    }
                                }
                                for (Credential cred : listClient) {
                                    if (cred.getPlayer().getId() == p2.getId()) {
                                        send_msg.setMsg(rcd_msg.getMsg());
                                        send_msg.setObj(null);
                                        ObjectOutputStream oos2 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                        oos2.writeObject(send_msg);
                                    }
                                }

                            }
                            break;
                        case "exitTable":
                            Player pl = (Player) rcd_msg.getObj();
                            for (Credential cred : listClient) {
                                for (Player p2 : playTable.getListPlayerInTable()) {
                                    if (p2.getId() != pl.getId() && cred.getPlayer().getId() == p2.getId()) {
                                        ObjectOutputStream oos2 = new ObjectOutputStream(cred.getClient().getOutputStream());
                                        send_msg.setMsg(rcd_msg.getMsg());
                                        send_msg.setObj(null);
                                        oos2.writeObject(send_msg);
                                    }
                                }
                            }
                            playTable = null;
                            break;
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }
}
