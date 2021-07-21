package Skripts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Smeny {
    // private boolean firstTimeOpenApp = true;
    private ArrayList<Smena> naplanovaneSmeny = new ArrayList<>();
    private ArrayList<Smena> odrobeneSmeny = new ArrayList<>();

    public ArrayList<Smena> getNaplanovaneSmeny() {
        return naplanovaneSmeny;
    }

    public ArrayList<Smena> getOdrobeneSmeny() {
        return odrobeneSmeny;
    }

    public void pridajNaplanovanuSmenu(int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        System.out.println("Datum: " + den + "." + mesiac + "." + rok);
        System.out.println("Od: " + odHodiny + " : " + odMinuty);
        System.out.println("Do: " + doHodiny + " : " + doMinuty);

        naplanovaneSmeny.add(new Smena(new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
        System.out.println("Zmena bola úspešne pridaná do naplánovaných zmien.");
    }

    public void pridajOdrobenuSmenu(int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        System.out.println("Datum: " + den + "." + mesiac + "." + rok);
        System.out.println("Od: " + odHodiny + " : " + odMinuty);
        System.out.println("Do: " + doHodiny + " : " + doMinuty);

        odrobeneSmeny.add(new Smena(new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
        System.out.println("Zmena bola úspešne pridaná do odrobených zmien.");
    }

    public void zrusNaplanovanuSmenu(int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {

    }

    public void zrusOdrobenuSmenu(int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {

    }

    public ObservableList<Smena> getNaplanovanaSmena() {
        ObservableList<Smena> naplanovanaSmena = FXCollections.observableArrayList(naplanovaneSmeny);
        return naplanovanaSmena;
    }

    public ObservableList<Smena> getOdrobenaSmena() {
        ObservableList<Smena> odrobenaSmena = FXCollections.observableArrayList(odrobeneSmeny);
        return odrobenaSmena;
    }
}
