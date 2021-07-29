package Skripts;

import java.io.*;

public class UserController {
    private User user;

    public UserController(User user) {
        this.user = user;
    }

    public UserController(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean firstLogin() {
        if(user.getDatumNarodenia().equals("1.1.1") || user.getMzda() == 0 || user.getOsatne() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void ulozUsera() {
        try {
            // vytvorenie (alebo hladanie uz vytvoreneho) suboru, kde sa bude serializovat
            FileOutputStream fileOut = new FileOutputStream("user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);  // pridavanie jednotlivych objektov
            out.close();
            fileOut.close();  // zatvorenie suboru
            System.out.println("Údaje boli uložené (user.ser)");
        } catch (IOException i) {  // ak sa nahodou nepodari subor vytvorit alebo pre inu chybu
            i.printStackTrace();  // vypis vynimky (problemu)
        }
    }

    public void nacitajUsera() {
        this.user = null;
        try {
            FileInputStream fileIn = new FileInputStream("user.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            user = (User) in.readObject();

            in.close();
            fileIn.close();
            System.out.println("Užívateľ je načítaný");
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
