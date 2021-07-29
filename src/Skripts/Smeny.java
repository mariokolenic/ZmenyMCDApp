package Skripts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Smeny implements Serializable {

    private ArrayList<Smena> smeny = new ArrayList<>();

    public void pridajSmenu(User user, int den, int mesiac, int rok, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        System.out.println("\n----------------  PRIDANIE SMENY  ----------------");
        System.out.println("Datum: " + den + "." + mesiac + "." + rok);
        System.out.println("Od: " + odHodiny + " : " + odMinuty);
        System.out.println("Do: " + doHodiny + " : " + doMinuty);

        for(int i = 0; i < smeny.size(); i++) {  // musí byť takýto for a nie foreach
            if(smeny.get(i).getDatum().getMesiac() == mesiac) {
                if(smeny.get(i).getDatum().getDen() < den)
                    continue;
                if(smeny.get(i).getDatum().getDen() == den)
                    smeny.remove(i);
                smeny.add(i, new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
                System.out.println("Zmena bola úspešne pridaná.");
                return;
            }
        }
        smeny.add(new Smena(user, new Datum(den, mesiac, rok), odHodiny, odMinuty, doHodiny, doMinuty));
        System.out.println("Zmena bola úspešne pridaná.");
    }

    public void zrusSmenu(int den, int mesiac, int rok) {
        System.out.println("\n----------------  ZRUŠENIE SMENY  ----------------");
        for(Smena smena : smeny) {
            if(smena.getDatum().getDen() == den &&
                    smena.getDatum().getMesiac() == mesiac &&
                    smena.getDatum().getRok() == rok) {
                smeny.remove(smena);
                System.out.println("Smena bola úspešne vymazaná");
                return;
            }
        }
    }

    public ObservableList<Smena> getNaplanovaneSmenyObservable(Datum datum, int mesiac, int rok) {
        ArrayList<Smena> naplanovaneSmeny = new ArrayList<>();

        for(int i = 0; i < smeny.size(); i++) {
            if((mesiac+1) > datum.getMesiac() && rok >= datum.getRok()) {
                if(smeny.get(i).getDatum().getMesiac() == (mesiac+1) &&
                        smeny.get(i).getDatum().getRok() == rok) {
                    naplanovaneSmeny.add(smeny.get(i));
                }
            }
            else if((mesiac+1) == datum.getMesiac() && rok == datum.getRok()){
                if(smeny.get(i).getDatum().getDen() >= datum.getDen() &&
                        smeny.get(i).getDatum().getMesiac() == (mesiac+1) &&
                        smeny.get(i).getDatum().getRok() == rok) {
                    naplanovaneSmeny.add(smeny.get(i));
                }
            }
        }

        ObservableList<Smena> naplanovaneSmenyObservable = FXCollections.observableArrayList(naplanovaneSmeny);
        return naplanovaneSmenyObservable;
    }

    public ObservableList<Smena> getOdrobeneSmenyObservable(Datum datum, int mesiac, int rok) {
        ArrayList<Smena> odrobeneSmeny = new ArrayList<>();

        for(int i = 0; i < smeny.size(); i++) {
            if((mesiac+1) < datum.getMesiac() && rok <= datum.getRok()) {
                if(smeny.get(i).getDatum().getMesiac() == (mesiac+1) &&
                        smeny.get(i).getDatum().getRok() == rok) {
                    odrobeneSmeny.add(smeny.get(i));
                }
            }
            else if((mesiac+1) == datum.getMesiac() && rok == datum.getRok()){
                if(smeny.get(i).getDatum().getDen() < datum.getDen() &&
                        smeny.get(i).getDatum().getMesiac() == (mesiac+1) &&
                        smeny.get(i).getDatum().getRok() == rok) {
                    odrobeneSmeny.add(smeny.get(i));
                }
            }
        }

        ObservableList<Smena> odrobeneSmenyObservable = FXCollections.observableArrayList(odrobeneSmeny);
        return odrobeneSmenyObservable;
    }

    public String vypocitajVyplatu(Datum datum, int mesiac, int rok, User user) {
        System.out.println("\n----------------  VÝPOČET VÝPLATY  ----------------");
        System.out.println("----------------  OBDOBIE: " + datum.getMesiacString(mesiac) + "  ----------------");
        double celkovaVyplata = 0;
        double hodinDokopy = 0;
        for(Smena smena : smeny) {
            if(smena.getDatum().getMesiac() == (mesiac+1) && smena.getDatum().getRok() == rok) {
                hodinDokopy += smena.getDlzkaSmeny();
                celkovaVyplata += smena.getDlzkaSmeny() * user.getMzda();
                celkovaVyplata += smena.getPriplatok();
            }
        }

        if(user.isDbps()) {
            System.out.println("Hodín dokopy: " + hodinDokopy);
            System.out.println("Celkový príjem: " + celkovaVyplata);
            celkovaVyplata -= (Math.max(0, (celkovaVyplata-200)) / 100) * 4;  // starobné poistenie
            System.out.println("Starobné poistenie: " + celkovaVyplata);
            celkovaVyplata -= (Math.max(0, (celkovaVyplata-200)) / 100) * 3;  // invalidné poistenie
            System.out.println("Invalidné poistenie: " + celkovaVyplata);
            celkovaVyplata -= (Math.max(0, (celkovaVyplata - 375.95)) / 100) * 20;
            System.out.println("Čistý príjem: " + celkovaVyplata);
            celkovaVyplata += hodinDokopy;
            System.out.println("Na účet: " + celkovaVyplata);
        }
        else {
            System.out.println("Toto treba zrobiť :(");
        }

        return String.format("%.02f", celkovaVyplata);
    }

    public void ulozSmeny() {
        try {
            // vytvorenie (alebo hladanie uz vytvoreneho) suboru, kde sa bude serializovat
            FileOutputStream fileOut = new FileOutputStream("smeny.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(smeny);  // pridavanie jednotlivych objektov
            out.close();
            fileOut.close();  // zatvorenie suboru
            System.out.println("Údaje boli uložené (smeny.ser)");
        } catch (IOException i) {  // ak sa nahodou nepodari subor vytvorit alebo pre inu chybu
            i.printStackTrace();  // vypis vynimky (problemu)
        }
    }

    public void nacitajSmeny() {
        smeny = null;
        try {
            // pripravenie suboru kde su serializovane denne smeny
            FileInputStream fileIn = new FileInputStream("smeny.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // naplnenie zoznamu pre controller
            smeny = (ArrayList<Smena>) in.readObject();

            in.close();
            fileIn.close();
            for(Smena smena : smeny) {
                smena.vypocetPriplatku();
            }
            System.out.println("Smeny sú načítané");
        } catch (IOException i) {  // chyba pri deserializacii
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {  // chyba nenajdeneho suboru so serializaciou
            System.out.println("Shifts class not found");
            c.printStackTrace();
            return;
        }
    }
}
