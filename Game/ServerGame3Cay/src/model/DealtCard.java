/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hieudx
 */
public class DealtCard implements Serializable{
    private static final long serialVersionUID = 4L;
    private int id;
    private int position;
    private int totalValue;
    private Player player;
    private List<PlayerCard> listPlayerCard = new ArrayList<>();

    public DealtCard() {
    }

    public DealtCard(int id, int position, int totalValue, Player player) {
        this.id = id;
        this.position = position;
        this.totalValue = totalValue;
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<PlayerCard> getListPlayerCard() {
        return listPlayerCard;
    }

    public void setListPlayerCard(List<PlayerCard> listPlayerCard) {
        this.listPlayerCard = listPlayerCard;
    }

    
    
    
    
}
