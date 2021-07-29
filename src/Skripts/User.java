package Skripts;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User implements Serializable {
    private String datumNarodenia = "";
    private int denNarodenia = 0;
    private int mesiacNarodenia = 0;
    private int rokNarodenia = 0;
    private int vek = 0;
    private boolean dbps;
    private double mzda = 0;
    private double osatne = 0;

    public User(String datumNarodenia, boolean dbps, double mzda, double osatne) {
        this.datumNarodenia = datumNarodenia;
        this.dbps = dbps;
        this.mzda = mzda;
        this.osatne = osatne;
        String[] arrOfStr = datumNarodenia.split("\\.", 3);  // ziskanie jednotlivých hodnot, ktore su oddelene bodkou do pola
        // kazdy index predstavuje jeden parameter
        this.denNarodenia = Integer.parseInt(arrOfStr[0]);
        this.mesiacNarodenia = Integer.parseInt(arrOfStr[1]);
        this.rokNarodenia = Integer.parseInt(arrOfStr[2]);
        vypocitajVek();
    }

    public void vypocitajVek() {
        LocalDate today = LocalDate.now();  // trieda ktora spracuva cas (lokalny)
        // naplnenie hodnotami datumu aby sa zistili narodeniny
        LocalDate birthday = LocalDate.of(this.rokNarodenia, this.mesiacNarodenia, this.denNarodenia);

        // na zaklade narodenia a dnesneho datumu sa vypocita vek zamestnanca
        Period period = Period.between(birthday, today);

        this.vek = period.getYears();  // vratenie vypocitaneho veku
    }

    public String getDatumNarodenia() {
        return datumNarodenia;
    }

    public boolean isDbps() {
        return dbps;
    }

    public double getMzda() {
        return mzda;
    }

    public double getOsatne() {
        return osatne;
    }

    public int getVek() {
        return vek;
    }
}
