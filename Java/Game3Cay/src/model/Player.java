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
public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private int point;
    private String status;
    private int firstRankTime;
    private int secondRankTime;
    private int thirdRankTime;
    private int lastRankTime;

    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(int id, String username, String password, int point, String status, int firstRankTime, int secondRankTime, int thirdRankTime, int lastRankTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.point = point;
        this.status = status;
        this.firstRankTime = firstRankTime;
        this.secondRankTime = secondRankTime;
        this.thirdRankTime = thirdRankTime;
        this.lastRankTime = lastRankTime;
    }
      
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFirstRankTime() {
        return firstRankTime;
    }

    public void setFirstRankTime(int firstRankTime) {
        this.firstRankTime = firstRankTime;
    }

    public int getSecondRankTime() {
        return secondRankTime;
    }

    public void setSecondRankTime(int secondRankTime) {
        this.secondRankTime = secondRankTime;
    }

    public int getThirdRankTime() {
        return thirdRankTime;
    }

    public void setThirdRankTime(int thirdRankTime) {
        this.thirdRankTime = thirdRankTime;
    }

    public int getLastRankTime() {
        return lastRankTime;
    }

    public void setLastRankTime(int lastRankTime) {
        this.lastRankTime = lastRankTime;
    }
    
    public Object[] toObject(){
        return new Object[]{
          username, point
        };
    }
    
}
