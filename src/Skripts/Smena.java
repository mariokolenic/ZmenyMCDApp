package Skripts;

public class Smena {
    private Datum datum;
    private int odHodiny;
    private int doHodiny;
    private int odMinuty;
    private int doMinuty;

    private double dlzkaSmeny;

    public Smena(Datum datum, int odHodiny, int odMinuty, int doHodiny, int doMinuty) {
        this.odHodiny = odHodiny;
        this.doHodiny = doHodiny;
        this.odMinuty = odMinuty;
        this.doMinuty = doMinuty;
        this.datum = datum;
    }

    public String getDatum() {
        return datum.getDen() + "." + datum.getMesiac() + "." + datum.getRok();
    }

    public String getDen() {
        return datum.getDayFromDate(datum.getDen(), datum.getMesiac(), datum.getRok());
    }

    public String getOd() {
        return String.format("%02d", odHodiny) + ":" + String.format("%02d", odMinuty);
    }

    public String getDo() {
        return String.format("%02d", doHodiny) + ":" + String.format("%02d", doMinuty);
    }

    public String getHodinDokopy() {
        return "7,5";  // zatiaľ !!!!!!!!!
    }
}
