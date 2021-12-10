/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.DealtCard;
import model.Match;
import model.Player;
import model.Table;

/**
 *
 * @author hieudx
 */
public class PlayingRoomView extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form PlayingRoom
     */
    private ImageIcon image1;
    private Table playTable;
    private Player player;
    private Match match;
    private int isMaster;
    

    public PlayingRoomView(Table playTable, Player player, int isMaster) {
        super("Room");
        initComponents();
        //this.setLocationRelativeTo(null);
        this.playTable = playTable;
        this.player = player;
        this.isMaster = isMaster;
        lbUsername.setText(player.getUsername());
        resetCards();
        disableBtnStart();
    }

    public void setChatArea(String message) {
        String currentChat = txtChatArea.getText();
        currentChat = currentChat + "you: " + message + "\n";
        txtChatArea.setText(currentChat);
        txtChat.setText("");
    }

    public void setChatFromOther(Player chatFrom, String chatMsg) {
        String currentChat = txtChatArea.getText();
        currentChat = currentChat + chatFrom.getUsername() + ": " + chatMsg + "\n";
        txtChatArea.setText(currentChat);
        txtChat.setText("");
    }

    public String getChat() {
        String msg = txtChat.getText();
        return msg;
    }

    public int setCards() {
        int position = -1;
        for (DealtCard dc : match.getListDealtCard()) {
            if (dc.getPlayer().getId() == player.getId()) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("pic/" + dc.getListPlayerCard().get(0).getCard().getId() + ".PNG").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
                Card1.setIcon(imageIcon);
                ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("pic/" + dc.getListPlayerCard().get(1).getCard().getId() + ".PNG").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
                Card2.setIcon(imageIcon2);
                ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("pic/" + dc.getListPlayerCard().get(2).getCard().getId() + ".PNG").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
                Card3.setIcon(imageIcon3);
                lbYourPoint.setText("Point: " + dc.getTotalValue());
                position = dc.getPosition();
                
            } else {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("pic/" + dc.getListPlayerCard().get(0).getCard().getId() + ".PNG").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
                Card4.setIcon(imageIcon);
                ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("pic/" + dc.getListPlayerCard().get(1).getCard().getId() + ".PNG").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
                Card5.setIcon(imageIcon2);
                ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("pic/" + dc.getListPlayerCard().get(2).getCard().getId() + ".PNG").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
                Card6.setIcon(imageIcon3);
                lbOtherPoint.setText("Point: " + dc.getTotalValue());
            }
            
        }
        
        switch (position) {
            case 0:
                lbResult.setText("you win");
                break;
            case 1:
                lbResult.setText("you lose");
                break;
            default:
                lbResult.setText("draw");
                break;
        }
        //doi 4s 
        TimeUnit time = TimeUnit.SECONDS;
        try {
            time.sleep(4L);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //end doi 4s
        return JOptionPane.showConfirmDialog(this, "Continue", "", JOptionPane.YES_NO_OPTION);
        
    }
    public void resetCards() {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("pic/0.jpg").getImage().getScaledInstance(103, 146, Image.SCALE_DEFAULT));
        Card1.setIcon(imageIcon);
        Card2.setIcon(imageIcon);
        Card3.setIcon(imageIcon);
        Card4.setIcon(imageIcon);
        Card5.setIcon(imageIcon);
        Card6.setIcon(imageIcon);
        lbResult.setText("");
        lbYourPoint.setText("");
        lbOtherPoint.setText("");
    }
    
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    
    public void disableBtnStart() {
        if (this.isMaster == 1) {
            btnReady.setText("Start");
            btnReady.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReady = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbUsername = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChatArea = new javax.swing.JTextArea();
        txtChat = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Card4 = new javax.swing.JLabel();
        Card5 = new javax.swing.JLabel();
        Card6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Card1 = new javax.swing.JLabel();
        Card2 = new javax.swing.JLabel();
        Card3 = new javax.swing.JLabel();
        lbResult = new javax.swing.JLabel();
        lbOtherPoint = new javax.swing.JLabel();
        lbYourPoint = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnReady.setText("Sẵn sàng");

        btnExit.setText("Thoát");

        lbUsername.setText("jLabel1");

        txtChatArea.setEditable(false);
        txtChatArea.setColumns(20);
        txtChatArea.setRows(5);
        jScrollPane1.setViewportView(txtChatArea);

        btnSend.setText("Gửi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSend))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(lbUsername)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Card4.setText("jLabel1");

        Card5.setText("jLabel1");

        Card6.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Card4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(Card5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Card6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Card4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Card5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Card6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Card1.setText("jLabel1");

        Card2.setText("jLabel1");

        Card3.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Card1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(Card2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Card3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Card1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Card2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Card3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        lbResult.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lbOtherPoint.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        lbYourPoint.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbResult, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(lbOtherPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReady)
                                .addGap(47, 47, 47)
                                .addComponent(btnExit)
                                .addGap(42, 42, 42))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lbYourPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(lbOtherPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(lbResult, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(lbYourPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExit)
                            .addComponent(btnReady))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void addActionListener(ActionListener log) {
        btnReady.addActionListener(log);
        btnSend.addActionListener(log);
        btnExit.addActionListener(log);
        txtChat.addActionListener(log);
    }

    //getter setter
    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }

    public JButton getBtnReady() {
        return btnReady;
    }

    public void setBtnReady(JButton btnReady) {
        this.btnReady = btnReady;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(JButton btnSend) {
        this.btnSend = btnSend;
    }

    public Table getPlayTable() {
        return playTable;
    }

    public void setPlayTable(Table playTable) {
        this.playTable = playTable;
    }

    public JTextField getTxtChat() {
        return txtChat;
    }

    public void setTxtChat(JTextField txtChat) {
        this.txtChat = txtChat;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Card1;
    private javax.swing.JLabel Card2;
    private javax.swing.JLabel Card3;
    private javax.swing.JLabel Card4;
    private javax.swing.JLabel Card5;
    private javax.swing.JLabel Card6;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReady;
    private javax.swing.JButton btnSend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbOtherPoint;
    private javax.swing.JLabel lbResult;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JLabel lbYourPoint;
    private javax.swing.JTextField txtChat;
    private javax.swing.JTextArea txtChatArea;
    // End of variables declaration//GEN-END:variables

}
