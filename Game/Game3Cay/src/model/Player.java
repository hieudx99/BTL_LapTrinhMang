/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author hieudx
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String status;
    private int point;
    private int winTotal;
    private int loseTotal;

    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(int id, String username, String password, String status, int point, int winTotal, int loseTotal) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.point = point;
        this.winTotal = winTotal;
        this.loseTotal = loseTotal;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getWinTotal() {
        return winTotal;
    }

    public void setWinTotal(int winTotal) {
        this.winTotal = winTotal;
    }

    public int getLoseTotal() {
        return loseTotal;
    }

    public void setLoseTotal(int loseTotal) {
        this.loseTotal = loseTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public Object[] toObject() {
        return new Object[]{
            username, point, status
        };
    }

}
