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
public class Tunnit {
    private int tunti;
    private int lyhyt;
    private int keski;
    private int pitka;
    
    public Tunnit(int tunti,int lyhyt, int keski, int pitka) {
        this.tunti=tunti;
        this.lyhyt=lyhyt;
        this.keski=keski;
        this.pitka=pitka;
    }
    
    public Integer getTunti() {
        return this.tunti;
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
