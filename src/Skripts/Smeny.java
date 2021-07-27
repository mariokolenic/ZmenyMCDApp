package Skripts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Smeny {

    private ArrayList<Smena> smeny = new ArrayList<>();

    public ArrayList<Smena> getSmeny() {
        return smeny;
    }

    public void pridajSmenu(User user, int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        System.out.println("Datum: " + den + "." + mesiac + "." + rok);
        System.out.println("Od: " + odHodiny + " : " + odMinuty);
        System.out.println("Do: " + doHodiny + " : " + doMinuty);

        for(int i = 0; i < smeny.size(); i++) {
            if(smeny.get(i).getDatum().getMesiac() == mesiac) {
                if(smeny.get(i).getDatum().getDen() < den)
                    continue;
                smeny.add(i, new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
                System.out.println("Zmena bola úspešne pridaná.");
                return;
            }
        }
        smeny.add(new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
        System.out.println("Zmena bola úspešne pridaná.");
    }

    public void zmenSmenu() {

    }

    public void zrusSmenu() {

    }

    public ObservableList<Smena> getNaplanovaneSmenyObservable(Datum datum) {
        ArrayList<Smena> naplanovaneSmeny = new ArrayList<>();

        for(int i = 0; i < smeny.size(); i++) {
            if(datum.getDen() <= smeny.get(i).getDatum().getDen() &&
                    datum.getMesiac() == smeny.get(i).getDatum().getMesiac() &&
                    datum.getRok() == smeny.get(i).getDatum().getRok()) {
                naplanovaneSmeny.add(smeny.get(i));
            }
        }

        ObservableList<Smena> naplanovaneSmenyObservable = FXCollections.observableArrayList(naplanovaneSmeny);
        return naplanovaneSmenyObservable;
    }

    public ObservableList<Smena> getOdrobeneSmenyObservable(Datum datum) {
        ArrayList<Smena> odrobeneSmeny = new ArrayList<>();

        for(int i = 0; i < smeny.size(); i++) {
            if(datum.getDen() > smeny.get(i).getDatum().getDen() &&
                    datum.getMesiac() == smeny.get(i).getDatum().getMesiac() &&
                    datum.getRok() == smeny.get(i).getDatum().getRok()) {
                odrobeneSmeny.add(smeny.get(i));
            }
        }

        ObservableList<Smena> odrobeneSmenyObservable = FXCollections.observableArrayList(odrobeneSmeny);
        return odrobeneSmenyObservable;
    }

    public String vypocitajVyplatu(String druhSmeny, int mesiac, int rok, User user) {
        double celkovaVyplata = 0;
        double hodinDokopy = 0;
        if(druhSmeny.equals("NAPLÁNOVANÉ")) {
            for(int den = 1; den <= 31; den++) {
                for(Smena smena : smeny) {
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
                for(Smena smena : smeny) {
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
