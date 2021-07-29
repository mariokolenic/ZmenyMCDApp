package Skripts;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Datum implements Serializable {
    private int den;
    private int mesiac;
    private int rok;
    private String denString;

    public Datum() {
        String[] datum = zistiDatum().split("\\.", 3);  // rozdelenie podla bodiek
        this.den = Integer.parseInt(datum[0]);  // prva zistena hodnota stringu predsstavuje den
        this.mesiac = Integer.parseInt(datum[1]);  // druha zistena hodnota stringu predsstavuje mesiac
        this.rok = Integer.parseInt(datum[2]);  // tretia zistena hodnota stringu predsstavuje rok
    }

    public Datum(int den, int mesiac, int rok) {
        this.den = den;
        this.mesiac = mesiac;
        this.rok = rok;
        this.denString = getDayFromDate(den, mesiac, rok);
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    public int getMesiac() {
        return mesiac;
    }

    public void setMesiac(int mesiac) {
        this.mesiac = mesiac;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String zistiDatum() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");  // vytvorenie formatu pre datum
        LocalDateTime now = LocalDateTime.now();  // zistenie aktualneho datumu
        return dtf.format(now);  // vratenie hodnoty zisteneho datumu v zadanom formate
    }

    /**
     * Zistenie dni v zadanom mesiaci.
     * @param mesiac cislo mesiaca pomocou ktoreho sa vypocita pocet dni
     * @return pole ktore obsahuje ciselne hodnoty dni
     */
    // funkcia pre zistenie poctu dni v zadanom mesiaci
    public int[] getDays(int mesiac) {  // ráta s tým, že mesiac ide od 0
        Calendar cal = Calendar.getInstance();  // instancia kalendaru
        cal.set(Calendar.MONTH, mesiac);  // nastavenie mesiaca
        cal.set(Calendar.DAY_OF_MONTH, 1);  // nastavenie prveho dna ako 1 (nie 0)
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  // zistenie maximalneho poctu dni v mesiaci
        int[] days = new int[maxDay];
        SimpleDateFormat df = new SimpleDateFormat("dd");  // formatovanie dna s dvoma ciframi
        for (int i = 0; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            days[i] = Integer.parseInt(df.format(cal.getTime()));  // naplnanie pola cislami dni
        }

        return days;
    }

    public String getMesiacString(int mesiac) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("LLLL");
        LocalDate now = LocalDate.of(2021, mesiac+1, 1);
        return dtf.format(now);
    }

    /**
     * Zistenie rokov, ktore su 4 roky dozadu a 1 rok dopredu od aktualneho roku).
     * @return pole ciselnych hodnot zistenych rokov
     */
    // funkcia ktora vrati pocet rokov (4 roky dozadu a jeden dopredu)
    public Integer[] getYears() {
        Integer[] years = new Integer[6];  // deklaracia pola rokov

        Calendar cal = Calendar.getInstance(); // instancia kalendaru
        int year = cal.get(Calendar.YEAR);  // zistenie aktualneho mesiaca
        for (int index = 0; index < years.length;) {
            years[index++] = year+2 - index;  // pridanie roku do pola rokov
        }
        return years;
    }

    public String getDayFromDate(int den, int mesiac, int rok) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E");
        LocalDate now = LocalDate.of(rok, mesiac, den);
        return dtf.format(now);
    }
}
