package Skripts;

public class Test {

    public static void main(String[] args) {
        System.out.println("SPŮŠTANIE TESTOVANIA PRE VLOŽENIE SMIEN");

        Smeny smeny = new Smeny();
        User user = new User("1.12.2000", true, 3.6, 1);
        smeny.getNaplanovaneSmeny().add(new Smena(user, new Datum(1, 7, 2021), 16, 0, 22, 0));
    }
}
