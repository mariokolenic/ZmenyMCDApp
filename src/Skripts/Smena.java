package Skripts;

import java.io.Serializable;

public class Smena implements Serializable {
    private Datum datum;
    private int odHodiny;
    private int doHodiny;
    private int odMinuty;
    private int doMinuty;

    private double dlzkaSmeny;
    private double priplatok;

    public Smena(User user, Datum datum, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        this.odHodiny = odHodiny;
        this.doHodiny = doHodiny;
        this.odMinuty = odMinuty;
        this.doMinuty = doMinuty;
        this.datum = datum;
        vypocetDlzkySmeny(user, odHodiny, odMinuty, doHodiny, doMinuty);
        vypocetPriplatku();
    }

    public void vypocetDlzkySmeny(User user, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        // počítam do minút a na konci to vydelím zasa do hodín
        odHodiny *= 60;
        doHodiny *= 60;

        if(doHodiny < odHodiny) {
            dlzkaSmeny = (doHodiny + 1440 + doMinuty) - (odHodiny + odMinuty);  // 1440 minút je 24 hodín
        }
        else {
            dlzkaSmeny = (doHodiny + doMinuty) - (odHodiny + odMinuty);
        }
        dlzkaSmeny /= 60;
        if(user.getVek() < 18 && dlzkaSmeny >= 4 ||
                user.getVek() >= 18 && dlzkaSmeny >= 7) {
            dlzkaSmeny -= 0.5;
        }
    }

    public void vypocetPriplatku() {
        priplatok = 0;
        // sobota nedela
        if(datum.getDayFromDate(datum.getDen(), datum.getMesiac(), datum.getRok()).equals("So")) {  // Sobota
            priplatok += dlzkaSmeny * 1.79;
        }
        else if(datum.getDayFromDate(datum.getDen(), datum.getMesiac(), datum.getRok()).equals("Ne")) {  // Nedela
            priplatok += dlzkaSmeny * 3.58;
        }

        // nočný
        odHodiny *= 60;
        doHodiny *= 60;
        int zaciatok = odHodiny + odMinuty;
        int koniec = doHodiny + doMinuty;
        if(koniec < zaciatok) {
            koniec += 1440;  // 1440 je v minútach --> 24 hodín
            for(int hodina = zaciatok; hodina <= koniec; hodina++) {
                if(hodina > 1320) {  // 1320 minút - 22 hodín
                    priplatok += (1.42 / 60);
                }
            }
        }
        else {
            for(int hodina = zaciatok; hodina <= koniec; hodina++) {
                if(hodina > 1320)  // 1320 minút - 22 hodín
                    priplatok += (1.42 / 60);
            }
        }
        odHodiny /= 60;
        doHodiny /= 60;

        // sviatky
        int den = datum.getDen();
        int mesiac = datum.getMesiac();
        if(den == 1 && mesiac == 1 ||
                den == 6 && mesiac == 1 ||
                den == 2 && mesiac == 4 ||  // velká noc
                den == 5 && mesiac == 4 ||  // velká noc
                den == 1 && mesiac == 5 ||
                den == 8 && mesiac == 5 ||
                den == 5 && mesiac == 7 ||
                den == 29 && mesiac == 8 ||
                den == 1 && mesiac == 9 ||
                den == 15 && mesiac == 9 ||
                den == 1 && mesiac == 11 ||
                den == 17 && mesiac == 11 ||
                den == 24 && mesiac == 12 ||
                den == 25 && mesiac == 12 ||
                den == 26 && mesiac == 12) {
            priplatok += dlzkaSmeny * 3.58;
        }
    }

    public Datum getDatum() {
        return datum;
    }

    public String getDatumString() {
        return datum.getDen() + "." + datum.getMesiac() + "." + datum.getRok();
    }

    public String getDenString() {
        return datum.getDayFromDate(datum.getDen(), datum.getMesiac(), datum.getRok());
    }

    public String getOd() {
        return String.format("%02d", odHodiny) + ":" + String.format("%02d", odMinuty);
    }

    public String getDo() {
        return String.format("%02d", doHodiny) + ":" + String.format("%02d", doMinuty);
    }

    public String getHodinDokopy(User user) {
        vypocetDlzkySmeny(user, odHodiny, odMinuty, doHodiny, doMinuty);
        return String.format("%.1f", dlzkaSmeny);
    }

    public double getDlzkaSmeny() {
        return dlzkaSmeny;
    }

    public double getPriplatok() {
        return priplatok;
    }

    public void setOdHodiny(int odHodiny) {
        this.odHodiny = odHodiny;
    }

    public void setDoHodiny(int doHodiny) {
        this.doHodiny = doHodiny;
    }

    public void setOdMinuty(int odMinuty) {
        this.odMinuty = odMinuty;
    }

    public void setDoMinuty(int doMinuty) {
        this.doMinuty = doMinuty;
    }
}
