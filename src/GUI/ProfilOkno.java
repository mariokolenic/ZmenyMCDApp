package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfilOkno {

    public ProfilOkno() {
        super();

        Stage pridajOkno = new Stage();

        pridajOkno.initModality(Modality.APPLICATION_MODAL);  // zabezpečuje to, aby užívateľ nevstupoval do iných okien
        pridajOkno.setTitle("PROFIL OKNO");

        GridPane gridPane = new GridPane();

        Scene scene = new Scene(gridPane, 450, 300);
        pridajOkno.setScene(scene);
        pridajOkno.setResizable(false);  // zakázanie menenia veľkosti okna
        pridajOkno.showAndWait();  // zobrazí okno a čakania, kým sa nezavrie
    }
}
