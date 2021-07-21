package GUI;

import Skripts.Datum;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZrusOkno {

    public ZrusOkno(Datum datum) {
        super();

        Stage zrusOkno = new Stage();

        zrusOkno.initModality(Modality.APPLICATION_MODAL);  // zabezpečuje to, aby užívateľ nevstupoval do iných okien
        zrusOkno.setTitle("ZRUSENIE ZMENY");

        VBox layout = new VBox(10);

        Scene scene = new Scene(layout, 400, 200);
        zrusOkno.setScene(scene);
        zrusOkno.setResizable(false);  // zakázanie menenia veľkosti okna
        zrusOkno.showAndWait();  // zobrazí okno a čakania, kým sa nezavrie
    }
}
