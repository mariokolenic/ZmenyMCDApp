package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfilOkno {

    public ProfilOkno() {
        super();

        Stage profilOkno = new Stage();

        profilOkno.initModality(Modality.APPLICATION_MODAL);  // zabezpečuje to, aby užívateľ nevstupoval do iných okien
        profilOkno.setTitle("PROFIL OKNO");

        Label nadpisLabel = new Label("Nastavenie profilu");
        nadpisLabel.setAlignment(Pos.TOP_CENTER);
        nadpisLabel.setMaxWidth(Double.MAX_VALUE);
        Label datumNarodeniaLabel = new Label("Dátum narodenia: ");
        Label zmluvaLabel = new Label("Zmluva: ");
        Label mzdaLabel = new Label("Hodinová mzda: ");
        Label osatneLabel = new Label("Ošatné: ");

        TextField narodenieText = new TextField();
        CheckBox dbpsCHBox = new CheckBox("Študent brigádnik");
        TextField mzdaText = new TextField();
        TextField osatneText = new TextField("1,00");

        nadpisLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        datumNarodeniaLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        zmluvaLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        mzdaLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        osatneLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        narodenieText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        dbpsCHBox.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        mzdaText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        osatneText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");

        Button ulozitButton = new Button("ULOŽIŤ");
        ulozitButton.setOnAction(e -> {
            profilOkno.close();
        });
        ulozitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ulozitButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: green;" + "-fx-font-size: 20;" + "-fx-text-fill: aliceblue;" + "-fx-background-radius: 10");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        GridPane.setConstraints(nadpisLabel, 0, 0, 2, 2);
        GridPane.setConstraints(datumNarodeniaLabel, 0, 3);
        GridPane.setConstraints(narodenieText, 1, 3);
        GridPane.setConstraints(zmluvaLabel, 0, 4);
        GridPane.setConstraints(dbpsCHBox, 1, 4);
        GridPane.setConstraints(mzdaLabel, 0, 5);
        GridPane.setConstraints(mzdaText, 1, 5);
        GridPane.setConstraints(osatneLabel, 0, 6);
        GridPane.setConstraints(osatneText, 1, 6);
        GridPane.setConstraints(ulozitButton, 0, 7, 2, 1);

        gridPane.getChildren().addAll(nadpisLabel, datumNarodeniaLabel, narodenieText,
                zmluvaLabel, dbpsCHBox, mzdaLabel, mzdaText, osatneLabel, osatneText, ulozitButton);

        ulozitButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: green;" + "-fx-font-size: 20;" + "-fx-text-fill: aliceblue;" + "-fx-background-radius: 10");

        Scene scene = new Scene(gridPane, 450, 300);
        profilOkno.setScene(scene);
        profilOkno.setResizable(false);  // zakázanie menenia veľkosti okna
        profilOkno.showAndWait();  // zobrazí okno a čakania, kým sa nezavrie
    }
}
