package GUI;

import Skripts.Datum;
import Skripts.Smeny;
import Skripts.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZrusOkno {

    public ZrusOkno(Smeny smeny, Datum datum) {
        super();

        Stage zrusOkno = new Stage();

        zrusOkno.initModality(Modality.APPLICATION_MODAL);  // zabezpečuje to, aby užívateľ nevstupoval do iných okien
        zrusOkno.setTitle("ZRUŠENIE ZMENY");

        Label datumLabel = new Label("DÁTUM : ");
        Label bodka1 = new Label(" .");
        Label bodka2 = new Label(" . ");

        TextField denText = new TextField();
        TextField mesiacText = new TextField();
        TextField rokText = new TextField();

        // nastavenie promtTextov tak, aby už bol ponúknutý dnešný deň
        denText.setPromptText(String.format("%02d", datum.getDen()));
        mesiacText.setPromptText(String.format("%02d", datum.getMesiac()));
        rokText.setPromptText(String.format("%02d", datum.getRok()));

        denText.setMaxWidth(40);
        mesiacText.setMaxWidth(40);
        rokText.setMaxWidth(80);

        datumLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        bodka1.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        bodka2.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        denText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        mesiacText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        rokText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");

        // tlačidlo ktoré nastaví zadanú smenu s ošetrením zlé zadaného času (kritérium)
        Button zrusButton = new Button("ZRUŠIŤ");
        zrusButton.setOnAction(e -> {
            smeny.zrusSmenu(Integer.parseInt(denText.getText()), Integer.parseInt(mesiacText.getText()),
                    Integer.parseInt(rokText.getText()));
            zrusOkno.close();
        });
        // rozmiestnenie elementov
        zrusButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        zrusButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: firebrick;" + "-fx-font-size: 20;" + "-fx-text-fill: aliceblue;" + "-fx-background-radius: 10");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        GridPane.setConstraints(datumLabel, 0,0);
        GridPane.setConstraints(denText, 1,0);
        GridPane.setConstraints(bodka1, 2, 0);
        GridPane.setConstraints(mesiacText, 3,0);
        GridPane.setConstraints(bodka2, 4, 0);  // neviem
        GridPane.setConstraints(rokText, 5,0);

        GridPane.setConstraints(zrusButton, 0, 1, 6, 1);

        gridPane.getChildren().addAll(datumLabel, denText, mesiacText, rokText, bodka1, bodka2, zrusButton);

        Scene scene = new Scene(gridPane, 400, 200);
        zrusOkno.setScene(scene);
        zrusOkno.setResizable(false);  // zakázanie menenia veľkosti okna
        zrusOkno.showAndWait();  // zobrazí okno a čakania, kým sa nezavrie
    }
}
