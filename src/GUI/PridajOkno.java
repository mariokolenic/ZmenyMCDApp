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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PridajOkno {

    public PridajOkno(User user, Smeny smeny, Datum datum) {
        super();

        Stage pridajOkno = new Stage();

        pridajOkno.initModality(Modality.APPLICATION_MODAL);  // zabezpečuje to, aby užívateľ nevstupoval do iných okien
        pridajOkno.setTitle("PRIDANIE ZMENY");

        Label datumLabel = new Label("DÁTUM : ");
        Label odLabel = new Label("OD : ");
        Label doLabel = new Label("DO : ");
        Label bodka1 = new Label(" .");
        Label bodka2 = new Label(" . ");
        Label dvojbodka1 = new Label(" : ");
        Label dvojbodka2 = new Label(" : ");

        TextField denText = new TextField();
        TextField mesiacText = new TextField();
        TextField rokText = new TextField();
        TextField odHodinyText = new TextField();
        TextField doHodinyText = new TextField();
        TextField odMinutyText = new TextField("00");
        TextField doMinutyText = new TextField("00");

        // nastavenie promtTextov tak, aby už bol ponúknutý dnešný deň
        denText.setPromptText(String.format("%02d", datum.getDen()));
        mesiacText.setPromptText(String.format("%02d", datum.getMesiac()));
        rokText.setPromptText(String.format("%02d", datum.getRok()));
        odHodinyText.setPromptText("14");
        doHodinyText.setPromptText("22");

        denText.setMaxWidth(40);
        mesiacText.setMaxWidth(40);
        rokText.setMaxWidth(80);
        odHodinyText.setMaxWidth(40);
        doHodinyText.setMaxWidth(40);
        odMinutyText.setMaxWidth(40);
        doMinutyText.setMaxWidth(40);

        datumLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        odLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        doLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        bodka1.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        bodka2.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        dvojbodka1.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        dvojbodka2.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;");
        denText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        mesiacText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        rokText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        odHodinyText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        odMinutyText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        doHodinyText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        doMinutyText.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");

        // tlačidlo ktoré nastaví zadanú smenu s ošetrením zlé zadaného času (kritérium)
        Button pridatButton = new Button("PRIDAŤ");
        pridatButton.setOnAction(e -> {
            smeny.pridajSmenu(user, Integer.parseInt(denText.getText()), Integer.parseInt(mesiacText.getText()),
                    Integer.parseInt(rokText.getText()), Integer.parseInt(odHodinyText.getText()), Integer.parseInt(odMinutyText.getText()),
                    Integer.parseInt(doHodinyText.getText()), Integer.parseInt(doMinutyText.getText()));
            pridajOkno.close();
        });
        // rozmiestnenie elementov
        pridatButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pridatButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: green;" + "-fx-font-size: 20;" + "-fx-text-fill: aliceblue;" + "-fx-background-radius: 10");

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

        GridPane.setConstraints(odLabel, 0,1);
        GridPane.setConstraints(odHodinyText, 1,1);
        GridPane.setConstraints(dvojbodka1, 2, 1);
        GridPane.setConstraints(odMinutyText, 3, 1);

        GridPane.setConstraints(doLabel, 0,2);
        GridPane.setConstraints(doHodinyText, 1,2);
        GridPane.setConstraints(dvojbodka2, 2, 2);
        GridPane.setConstraints(doMinutyText, 3, 2, 1, 1);

        GridPane.setConstraints(pridatButton, 0, 3, 6, 1);

        gridPane.getChildren().addAll(datumLabel, denText, mesiacText, rokText, odLabel, odHodinyText, odMinutyText,
                doLabel, doHodinyText, doMinutyText, bodka1, bodka2, dvojbodka1, dvojbodka2, pridatButton);

        Scene scene = new Scene(gridPane, 450, 300);
        pridajOkno.setScene(scene);
        pridajOkno.setResizable(false);  // zakázanie menenia veľkosti okna
        pridajOkno.showAndWait();  // zobrazí okno a čakania, kým sa nezavrie
    }
}
