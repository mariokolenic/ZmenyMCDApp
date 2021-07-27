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
        if(doHodiny < odHodiny) {
            dlzkaSmeny = (doHodiny + 24 + (doMinuty/60)) - (odHodiny + (odMinuty/60));
        }
        else {
            dlzkaSmeny = (doHodiny + (doMinuty/60)) - (odHodiny + (odMinuty/60));
        }
        if(user.getVek() < 18 && dlzkaSmeny >= 4 ||
                user.getVek() >= 18 && dlzkaSmeny >= 6) {
            dlzkaSmeny -= 0.5;
        }
    }

    public void vypocetPriplatku() {
        if(datum.getDayFromDate(datum.getDen(), datum.getMesiac(), datum.getRok()).equals("So")) {  // Sobota
            priplatok += dlzkaSmeny * 1.79;
        }
        else if(datum.getDayFromDate(datum.getDen(), datum.getMesiac(), datum.getRok()).equals("Ne")) {  // Nedela
            priplatok += dlzkaSmeny * 3.58;
        }

        if(doHodiny < odHodiny) {
            doHodiny += 24;
            for(int hodina = odHodiny; hodina <= doHodiny; hodina++) {
                if(hodina >= 22)
                    priplatok += 1.42;
            }
            doHodiny -= 24;
        }
        else {
            for(int hodina = odHodiny; hodina <= doHodiny; hodina++) {
                if(hodina >= 22)
                    priplatok += 1.42;
            }
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

    public String getHodinDokopy() {
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
