package Skripts;

public class Test {

    public static void main(String[] args) {
        System.out.println("SPŮŠTANIE TESTOVANIA PRE VLOŽENIE SMIEN");

        Smeny smeny = new Smeny();
        smeny.getNaplanovaneSmeny().add(new Smena(new Datum(1, 7, 2021), 16, 00, 22, 00));

        /*System.out.println(smeny.getNaplanovaneSmeny().get(0).getDatum().getDen() + "." +
                smeny.getNaplanovaneSmeny().get(0).getDatum().getMesiac() + "." +
                smeny.getNaplanovaneSmeny().get(0).getDatum().getRok());*/
    }
}
