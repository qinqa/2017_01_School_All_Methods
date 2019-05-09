/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitus_yksi;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

/**
 *
 * @author Thomas
 */
public class Harjoitus_yksi {
    Scanner lukija = new Scanner(System.in);
    List<Ufo> ufolista = new ArrayList<>();
    TreeMap<String, Integer> muodot = new TreeMap<>();
    List<String> aikalista = new ArrayList<>();
    List<String> kuvauslista = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Harjoitus_yksi().ohjelma();
    }
    
    public void ohjelma() {
        System.out.print("tiedosto: ");
        String tiedosto = lukija.nextLine();
        try(Scanner lukija = new Scanner(new File(tiedosto))) {
        
            while(lukija.hasNextLine()) {
            
                String rivi = lukija.nextLine();
                String[] osa = rivi.split(";");
                
                String aika = osa[0];
                String paikka = osa[1];
                String osavaltio = osa[2];
                String maa = osa[3];
                String muoto = osa[4];
                Double kesto = Double.valueOf(osa[5]);
                Double leveys = Double.valueOf(osa[6]);
                Double pituus = Double.valueOf(osa[7]);
                String kuvaus = osa[8];
                
                Ufo ufo = new Ufo(aika, paikka, osavaltio, maa, muoto, kesto, leveys, pituus, kuvaus);
                ufolista.add(ufo);
                
                aikalista.add(aika);
                kuvauslista.add(kuvaus);
                
                if(!muodot.containsKey(osa[4])) {
                    muodot.put(osa[4], 1);
                } else {
                    int value = muodot.get(osa[4]);
                    value++;
                    muodot.put(osa[4], value);
                }
            }
            
            //Metodit:
            havainnot();
            muodot();
            vuosittain();
            tunnit();
            kesto();
            tunnitKutenKestoMetodi();
            sanat();
            parit();
            
        } catch(Exception e) {
            System.out.println("*** TIEDOSTOA EI VOITU LUKEA ***");
        }
    }
    
    public void havainnot() {
        
        double lyhyt = ufolista.get(0).getKesto();
        double pitka = ufolista.get(0).getKesto();
        double keski = ufolista.get(0).getKesto();
        
        for(int i=1;i<ufolista.size();i++) {
            if(ufolista.get(i).getKesto()<lyhyt) {
                lyhyt = ufolista.get(i).getKesto();
            }
            if(ufolista.get(i).getKesto()>pitka) {
                pitka = ufolista.get(i).getKesto();
            }
            keski = keski + ufolista.get(i).getKesto();
        }
        
        System.out.println("\nHavaintoja: " + ufolista.size());
        System.out.println("Lyhimmän havainnon kesto sekuntteina: " + lyhyt);
        System.out.println("Pisimmän havainnon kesto sekuntteina: " + pitka);
        System.out.println("Keskimääräinen havainnon kesto sekuntteina: " + keski/ufolista.size());
            
    }
    
    public void muodot() {
    
        int iso = 0;
        String isoNimi = "";
        System.out.println("\nEsineiden muodot");
        for(Map.Entry<String, Integer> item : muodot.entrySet()) {
            String nimi = item.getKey();
            Integer maara = item.getValue();
            System.out.println("      " + nimi + ", havaintoja " + maara);
            if(maara > iso) {
                iso = maara;
                isoNimi = nimi;
            }
        }
        System.out.println("Eniten havaintoja: " + isoNimi + ", " + iso + " kertaa");
    }
    
    public void vuosittain() {
        TreeMap<Integer, Integer> temp = new TreeMap<>();
        
        for(int i=0;i<aikalista.size();i++) {
            String aika = aikalista.get(i);
            String[] osa = aika.split("/");
            Integer vuosi = Integer.valueOf(osa[2].substring(0, 4));
            if(!temp.containsKey(vuosi)) {
                temp.put(vuosi, 1);
            } else {
                int value = temp.get(vuosi);
                value++;
                temp.put(vuosi, value);
            }
        }
        
        System.out.println("\nHavainnot vuosittain");
        
        for(Integer vuosi : temp.keySet()) {
            int maara = temp.get(vuosi);
            System.out.println("      " + vuosi + ", havaintoja " + maara);
        }
    }
    
    public void kesto() {
        TreeMap<String, Muoto> temp = new TreeMap<>();
        List<Muoto> muotolista = new ArrayList<>();
        int lyhyt = 0;
        int keski = 0;
        int pitka = 0;
        
        for(int i=0;i<ufolista.size();i++) {
            if(ufolista.get(i).getKesto() <= 60.0) {
                Muoto muoto = new Muoto(ufolista.get(i).getMuoto(), 1, 0, 0);
                muotolista.add(muoto);
                if(!temp.containsKey(muotolista.get(i).getMuoto())) {
                    temp.put(muotolista.get(i).getMuoto(), muoto);
                } else {
                    muotolista.get(i).setLyhyt();
                    temp.put(muotolista.get(i).getMuoto(), muoto);
                }
                lyhyt++;
            } else if(ufolista.get(i).getKesto() > 60.0 && ufolista.get(i).getKesto() < 1200.0) {
                Muoto muoto = new Muoto(ufolista.get(i).getMuoto(), 0,1,0);
                muotolista.add(muoto);
                if(!temp.containsKey(muotolista.get(i).getMuoto())) {
                    temp.put(muotolista.get(i).getMuoto(), muoto);
                } else {
                    muotolista.get(i).setLyhyt();
                    temp.put(muotolista.get(i).getMuoto(), muoto);
                }
                keski++;
            } else {
                Muoto muoto = new Muoto(ufolista.get(i).getMuoto(), 0,0,1);
                muotolista.add(muoto);
                if(!temp.containsKey(muotolista.get(i).getMuoto())) {
                    temp.put(muotolista.get(i).getMuoto(), muoto);
                } else {
                    muotolista.get(i).setLyhyt();
                    temp.put(muotolista.get(i).getMuoto(), muoto);
                }
                pitka++;
            }
        }
        
        System.out.println("\nMuodot havainnon mukaan ryhmiteltynä");
        System.out.println("lyhyt: 0-60.0, havaintoja: " + lyhyt);
        System.out.println("keskipitkä: 60.0-1200.0, havaintoja: " + keski);
        System.out.println("pitkä: 1200.0-3600.0, havaintoja: " + pitka);
        
        System.out.println("\n      muoto lyhyt keskipitkä pitkä");
        for(int i=0;i<temp.size();i++) {
            
        }
        for(String muoto : temp.keySet()) {
            
            System.out.println("      " + muoto + " " + temp.get(muoto).getLyhyt() + " " + temp.get(muoto).getKeski() + " " + temp.get(muoto).getPitka());
        }
    }
    
    public void tunnit() {
        TreeMap<Integer, Integer> temp = new TreeMap<>();
        List<Tunnit> tuntilista = new ArrayList<>();
        
        for(int i=0;i<ufolista.size();i++) {
            String aika = ufolista.get(i).getAika();
            String[] osa = aika.split("/");
            Integer tunti = Integer.valueOf(osa[2].substring(5, 7));

            if(!temp.containsKey(tunti)) {
                temp.put(tunti, 1);
            } else {
                int value = temp.get(tunti);
                value++;
                temp.put(tunti, value);
            }
        }
        
        System.out.println("\nHavainnot tunnettain");
        
        for(Map.Entry<Integer, Integer> item : temp.entrySet()) {
            Integer aika = item.getKey();
            Integer maara = item.getValue();
            Double result = (Double.valueOf(maara)/ufolista.size()) * 100;
            if(aika+1 < 25) {
                System.out.println("      " + aika + "-" + (aika+1) + ", havaintoja " + maara + ", " + result + "%");
            } else {
                System.out.println("      24-1, havaintoja " + maara+ ", " + result + "%");
            }
        }
    }
    
    public void tunnitKutenKestoMetodi() {
        TreeMap<Integer, Tunnit> temp = new TreeMap<>();
        List<Tunnit> tuntilista = new ArrayList<>();
        
        for(int i=0;i<ufolista.size();i++) {
            String aika = ufolista.get(i).getAika();
            String[] osa = aika.split("/");
            Integer tunti = Integer.valueOf(osa[2].substring(5, 7));
            
            if(ufolista.get(i).getKesto() <= 60.0) {
                Tunnit tunnit = new Tunnit(tunti, 1, 0, 0);
                tuntilista.add(tunnit);
                if(!temp.containsKey(tunti)) {
                    temp.put(tunti, tunnit);
                } else {
                    tuntilista.get(i).setLyhyt();
                    temp.put(tunti, tunnit);
                }
            } else if(ufolista.get(i).getKesto() > 60.0 && ufolista.get(i).getKesto() < 1200.0) {
                Tunnit tunnit = new Tunnit(tunti, 0, 1, 0);
                tuntilista.add(tunnit);
                if(!temp.containsKey(tunti)) {
                    temp.put(tunti, tunnit);
                } else {
                    tuntilista.get(i).setKeski();
                    temp.put(tunti, tunnit);
                }
            } else {
                Tunnit tunnit = new Tunnit(tunti, 0, 0, 1);
                tuntilista.add(tunnit);
                if(!temp.containsKey(tunti)) {
                    temp.put(tunti, tunnit);
                } else {
                    tuntilista.get(i).setPitka();
                    temp.put(tunti, tunnit);
                }
            }
        }
        
        System.out.println("\nHavainnot tunnettain");
        System.out.println("      tunti lyhyt keskipitkä pitkä");
        for(Map.Entry<Integer, Tunnit> item : temp.entrySet()) {
            Integer aika = item.getKey();
            int lyhyt = item.getValue().getLyhyt();
            int keski = item.getValue().getKeski();
            int pitka = item.getValue().getPitka();
            if(aika+1 < 25) {
                if(aika < 10) {
                    System.out.println("      " + aika + "-" + (aika+1) + "   " + lyhyt + "     " + keski + "    " + pitka);
                } else {
                    System.out.println("      " + aika + "-" + (aika+1) + " " + lyhyt + "     " + keski + "    " + pitka);
                }
                
            } else {
                System.out.println("      24-1, havaintoja " + lyhyt + "     " + keski + "    " + pitka);
            }
        }
    }
    
    public void sanat() {
        TreeMap<String, Integer> temp = new TreeMap<>();
        
        for(int i=0;i<kuvauslista.size();i++) {
            String rivi = kuvauslista.get(i).toLowerCase();
            String[] osa = rivi.split(" ");
            String siivottu = "";
            for(int j = 0; j<osa.length;j++) {
                siivottu = osa[j].replaceAll("[^a-zA-Z]+", "");
                if(!temp.containsKey(siivottu.trim())) {
                    temp.put(siivottu.trim(), 1);
                } else {
                    int value = temp.get(siivottu.trim());
                    value++;
                    temp.put(siivottu.trim(), value);
                }
            }
        }
        
        Map<String, Integer> sorted = temp.entrySet().stream().sorted(comparingByValue())
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        
        System.out.println("\nYleisimmät sanat esiintymiskertojen mukaan järjestettynä");
        
        int count = 0;
        for(String sana : sorted.keySet()) {
            int value = sorted.get(sana);
            if(kuvauslista.size()<15) {
                if(!sana.matches("")) {
                    System.out.println("      " + sana + ", " + value);
                }
            } else {
                if(count == 15) {
                    break;
                }
                if(!sana.matches("")) {
                    System.out.println("      "+ sana + ", " + value);
                }
                count++;
            }
        }
    }
    
    public void parit() {
        
        int sanaMaara = 0;
        
        TreeMap<String, Integer> sanaLista = new TreeMap<>();
        List<String> testiLista = new ArrayList<>();
        
        String sanat = "";

        //Kaikki sanat haltuun
        for(int i=0;i<ufolista.size();i++) {
            String rivit = this.ufolista.get(i).getKuvaus().replaceAll("[0-9]", "").replaceAll("[,?!/:;\\-\\_\\.]", " ").trim();
            String[] rivi = rivit.split("[ \n\t\r]");
            for(int j=0;j<rivi.length;j++) {
                sanat = sanat + rivi[j] + " ";
                testiLista.add(rivi[j].toLowerCase());
                sanaMaara++;
            }  
        }
        
        System.out.println("\nYleisimmät sanaparit esiintymiskertojen mukaan järjestettynä");
        
        if((testiLista.size()/2) % 2 == 0) {
            for(int i=0;i<testiLista.size();i+=2) {
                String pari = "";
                pari = pari + testiLista.get(i) + " " + testiLista.get(i+1) + " ";
                if(!sanaLista.containsKey(pari)) {
                    sanaLista.put(pari, 1);
                } else {
                    int value = sanaLista.get(pari);
                    value++;
                    sanaLista.put(pari, value);
                }
            }
        } else if((testiLista.size()/2) % 2 != 0) {
            testiLista.remove(testiLista.size()-1);
            for(int i=0;i<testiLista.size();i+=2) {
                String pari = "";
                pari = pari + testiLista.get(i) + " " + testiLista.get(i+1) + " ";
                if(!sanaLista.containsKey(pari)) {
                    sanaLista.put(pari, 1);
                } else {
                    int value = sanaLista.get(pari);
                    value++;
                    sanaLista.put(pari, value);
                }
            }
        }
        
        Map<String, Integer> sortedLista = sanaLista.entrySet().stream().sorted(comparingByValue())
        .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        
        int count=0;
        for(String avain : sanaLista.keySet()) {
            if(testiLista.size()>=20) {
                if(count==10) {
                    break;
                }
                int value = sanaLista.get(avain);
                System.out.println("      " + avain.trim() + ", " + value);
                count++;
            } else {
                int value = sanaLista.get(avain);
                System.out.println("      " + avain.trim() + ", " + value);
            }
        }
    }
}
