/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitus_yksi;

/**
 *
 * @author Thomas
 */
public class Muoto {
    private String muoto;
    private int lyhyt;
    private int keski;
    private int pitka;
    
    public Muoto(String muoto,int lyhyt, int keski, int pitka) {
        this.muoto=muoto;
        this.lyhyt=lyhyt;
        this.keski=keski;
        this.pitka=pitka;
    }
    
    public String getMuoto() {
        return this.muoto;
    } 
    
    public Integer getLyhyt() {
        return this.lyhyt;
    }
    
    public Integer getKeski() {
        return this.keski;
    }
    
    public Integer getPitka() {
        return this.pitka;
    }
    
    public void setLyhyt() {
        this.lyhyt = this.lyhyt + 1;
    }
    
    public void setKeski() {
        this.keski = this.keski + 1;
    }
    
    public void setPitka() {
        this.pitka = this.pitka + 1;
    }
}
