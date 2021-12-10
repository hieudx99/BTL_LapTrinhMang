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
public class Match implements Serializable{
    private static final long serialVersionUID = 4L;
    private int id;
    private String date;
    private Table table;
    private List<DealtCard> listDealtCard = new ArrayList<>();

    public Match() {
    }

    public Match(int id, String date, Table table) {
        this.id = id;
        this.date = date;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<DealtCard> getListDealtCard() {
        return listDealtCard;
    }

    public void setListDealtCard(List<DealtCard> listDealtCard) {
        this.listDealtCard = listDealtCard;
    }
}
