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
public class Ufo {
    private String aika;
    private String paikka;
    private String osavaltio;
    private String maa;
    private String muoto;
    private Double kesto;
    private Double leveys;
    private Double pituus;
    private String kuvaus;
    
    public Ufo(String aika, String paikka, String osavaltio, String maa, String muoto, Double kesto, Double leveys, Double pituus, String kuvaus) {
        this.aika=aika;
        this.paikka=paikka;
        this.osavaltio=osavaltio;
        this.maa=maa;
        this.muoto=muoto;
        this.kesto=kesto;
        this.leveys=leveys;
        this.pituus=pituus;
        this.kuvaus=kuvaus;
    }
    
    public String getAika() {
        return this.aika;
    }
    
    public String getPaikka() {
        return this.paikka;
    }
    
    public String getOsavaltio() {
        return this.osavaltio;
    }
    
    public String getMaa() {
        return this.maa;
    }
    
    public String getMuoto() {
        return this.muoto;
    }
    
    public String getKuvaus() {
        return this.kuvaus;
    }
    
    public Double getKesto() {
        return this.kesto;
    }
}
