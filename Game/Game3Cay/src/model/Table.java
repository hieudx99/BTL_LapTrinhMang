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
public class Table implements Serializable{
    private static final long serialVersionUID = 3L;
    private List<Player> listPlayerInTable;
    private int nbr_ready;
    
    public Table() {
        listPlayerInTable = new ArrayList<>();
    }

    public Table(List<Player> listPlayerInTable) {
        this.listPlayerInTable = listPlayerInTable;
    }

    public List<Player> getListPlayerInTable() {
        return listPlayerInTable;
    }

    public void setListPlayerInTable(List<Player> listPlayerInTable) {
        this.listPlayerInTable = listPlayerInTable;
    }

    public int getNbr_ready() {
        return nbr_ready;
    }

    public void setNbr_ready(int nbr_ready) {
        this.nbr_ready = nbr_ready;
    }
    
    
}
