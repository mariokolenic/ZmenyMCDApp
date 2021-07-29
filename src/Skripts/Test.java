package Skripts;

public class Test {

    public static void main(String[] args) {
        System.out.println("SPŮŠTANIE TESTOVANIA PRE VLOŽENIE SMIEN");

        Smeny smeny = new Smeny();
        User user = new User("1.1.1", false, 0, 0);
        UserController userController = new UserController(user);
        smeny.ulozSmeny();
        userController.ulozUsera();
    }
}
