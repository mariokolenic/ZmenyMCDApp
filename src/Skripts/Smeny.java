package Skripts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Smeny {
    private ArrayList<Smena> naplanovaneSmeny = new ArrayList<>();
    private ArrayList<Smena> odrobeneSmeny = new ArrayList<>();

    public ArrayList<Smena> getNaplanovaneSmeny() {
        return naplanovaneSmeny;
    }

    public ArrayList<Smena> getOdrobeneSmeny() {
        return odrobeneSmeny;
    }

    public void pridajNaplanovanuSmenu(User user, int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        System.out.println("Datum: " + den + "." + mesiac + "." + rok);
        System.out.println("Od: " + odHodiny + " : " + odMinuty);
        System.out.println("Do: " + doHodiny + " : " + doMinuty);

        for(int i = 0; i < naplanovaneSmeny.size(); i++) {
            if(naplanovaneSmeny.get(i).getDatum().getMesiac() == mesiac) {
                if(naplanovaneSmeny.get(i).getDatum().getDen() < den)
                    continue;
                naplanovaneSmeny.add(i, new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
                System.out.println("Zmena bola úspešne pridaná do naplánovaných zmien.");
                return;
            }
        }
        naplanovaneSmeny.add(new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
        System.out.println("Zmena bola úspešne pridaná do naplánovaných zmien.");
    }

    public void pridajOdrobenuSmenu(User user, int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        System.out.println("Datum: " + den + "." + mesiac + "." + rok);
        System.out.println("Od: " + odHodiny + " : " + odMinuty);
        System.out.println("Do: " + doHodiny + " : " + doMinuty);

        for(int i = 0; i < odrobeneSmeny.size(); i++) {
            if(odrobeneSmeny.get(i).getDatum().getMesiac() == mesiac) {
                if(odrobeneSmeny.get(i).getDatum().getDen() < den)
                    continue;
                odrobeneSmeny.add(i, new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
                System.out.println("Zmena bola úspešne pridaná do odrobených zmien.");
                return;
            }
        }
        odrobeneSmeny.add(new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
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

    public String vypocitajVyplatu(String druhSmeny, int mesiac, int rok, User user) {
        double celkovaVyplata = 0;
        double hodinDokopy = 0;
        if(druhSmeny.equals("NAPLÁNOVANÉ")) {
            for(int den = 1; den <= 31; den++) {
                for(Smena smena : naplanovaneSmeny) {
                    if(smena.getDatum().getDen() == den &&
                            smena.getDatum().getMesiac() == (mesiac+1) &&
                            smena.getDatum().getRok() == rok) {
                        hodinDokopy += smena.getDlzkaSmeny();
                        celkovaVyplata += smena.getDlzkaSmeny() * user.getMzda();
                        celkovaVyplata += smena.getPriplatok();
                        break;
                    }
                }
            }
        }
        else if(druhSmeny.equals("ODROBENÉ")) {
            for(int den = 1; den <= 31; den++) {
                for(Smena smena : odrobeneSmeny) {
                    if(smena.getDatum().getDen() == den &&
                            smena.getDatum().getMesiac() == (mesiac+1) &&
                            smena.getDatum().getRok() == rok) {
                        hodinDokopy += smena.getDlzkaSmeny();
                        celkovaVyplata += smena.getDlzkaSmeny() * user.getMzda();
                        celkovaVyplata += smena.getPriplatok();
                        break;
                    }
                }
            }
        }

        celkovaVyplata += hodinDokopy;
        System.out.println("Hodín dokopy: " + hodinDokopy + "\nCelková výplata: " + celkovaVyplata);
        return String.format("%.02f", celkovaVyplata);
    }
}
