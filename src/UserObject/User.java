package UserObject;

public class User {
    private String datumNarodenia;
    private String zmluva;
    private double mzda;
    private double osatne;

    public User(String datumNarodenia, String zmluva, double mzda, double osatne) {
        this.datumNarodenia = datumNarodenia;
        this.zmluva = zmluva;
        this.mzda = mzda;
        this.osatne = osatne;
    }

    public String getDatumNarodenia() {
        return datumNarodenia;
    }

    public void setDatumNarodenia(String datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    public String getZmluva() {
        return zmluva;
    }

    public void setZmluva(String zmluva) {
        this.zmluva = zmluva;
    }

    public double getMzda() {
        return mzda;
    }

    public void setMzda(double mzda) {
        this.mzda = mzda;
    }

    public double getOsatne() {
        return osatne;
    }

    public void setOsatne(double osatne) {
        this.osatne = osatne;
    }
}
