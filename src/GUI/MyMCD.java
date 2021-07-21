package GUI;

import Skripts.Datum;
import Skripts.Smena;
import Skripts.Smeny;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MyMCD extends Application {
    private Stage hlavneOkno;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage okno) {
        Datum datum = new Datum();
        Smeny smeny = new Smeny();

        hlavneOkno = okno;
        hlavneOkno.setTitle("MCD ZMENY");

        // vrchná časť
        Label titleLabel = new Label("ZMENY");
        titleLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 30;" + "-fx-background-color: green;" + "-fx-text-fill: white;");
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setMinHeight(60);
        titleLabel.setAlignment(Pos.CENTER);

        // stredná časť
        ChoiceBox<Integer> yearChoice = new ChoiceBox<>();
        yearChoice.getItems().addAll(datum.getYears());  // z controllera datumu zistím aké roky treba vypísať
        yearChoice.setMinWidth(120);
        yearChoice.setValue(yearChoice.getItems().get(1));  // nastavenie defaulneho roku ktorý je teraz
        yearChoice.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;" + "-fx-background-radius: 10");

        ChoiceBox<String> mounthChoice = new ChoiceBox<>();
        mounthChoice.getItems().addAll("Január", "Február", "Marec", "Apríl", "Máj", "Jún", "Júl", "August", "September", "Október", "November", "December");
        mounthChoice.setMinWidth(120);
        mounthChoice.setValue(mounthChoice.getItems().get(datum.getMesiac()-1));  // nastavenie defaulneho mesiaca čo je teraz
        mounthChoice.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;" + "-fx-background-radius: 10");

        HBox obdobieContainer = new HBox(20);
        obdobieContainer.setAlignment(Pos.CENTER);
        obdobieContainer.getChildren().addAll(mounthChoice, yearChoice);

        TableView<Smena> naplanovaneTable = new TableView<>();
        naplanovaneTable.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        naplanovaneTable.setEditable(false);
        naplanovaneTable.setPlaceholder(new Label("Zatiaľ nie sú pridané žiadne zmeny"));

        TableColumn<Smena, String> datumColumn = new TableColumn<>("Dátum");
        datumColumn.setStyle("-fx-alignment: CENTER;");
        datumColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatum()));

        TableColumn<Smena, String> denColumn = new TableColumn<>("Den");
        denColumn.setStyle("-fx-alignment: CENTER;");
        denColumn.setMaxWidth(78);
        denColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDen()));

        TableColumn<Smena, String> odColumn = new TableColumn<>("Od");
        odColumn.setStyle("-fx-alignment: CENTER;");
        odColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOd()));

        TableColumn<Smena, String> doColumn = new TableColumn<>("Do");
        doColumn.setStyle("-fx-alignment: CENTER;");
        doColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDo()));

        TableColumn<Smena, String> hodinColumn = new TableColumn<>("Hodín");
        hodinColumn.setStyle("-fx-alignment: CENTER;");
        hodinColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHodinDokopy()));

        naplanovaneTable.getColumns().add(datumColumn);
        naplanovaneTable.getColumns().add(denColumn);
        naplanovaneTable.getColumns().add(odColumn);
        naplanovaneTable.getColumns().add(doColumn);
        naplanovaneTable.getColumns().add(hodinColumn);
        naplanovaneTable.setItems(smeny.getNaplanovanaSmena());

        TableView<Smena> odrobeneTable = new TableView<>();

        Label vyplataLabel = new Label("VÝPLATA: ");
        vyplataLabel.setAlignment(Pos.CENTER_LEFT);
        vyplataLabel.setPadding(new Insets(0, 10, 0, 10));
        vyplataLabel.setMinHeight(40);
        vyplataLabel.setMaxWidth(400);
        vyplataLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: white;" + "-fx-font-size: 20;" + "-fx-background-radius: 10;");

        Tab naplanovaneTab = new Tab("NAPLÁNOVANÉ");
        naplanovaneTab.setClosable(false);
        naplanovaneTab.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: white;");

        VBox naplanovaneBox = new VBox();
        naplanovaneBox.setAlignment(Pos.CENTER);
        naplanovaneBox.getChildren().addAll(naplanovaneTable);
        naplanovaneTab.setContent(naplanovaneBox);

        Tab odrobeneTab = new Tab("ODROBENÉ");
        odrobeneTab.setClosable(false);
        odrobeneTab.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: white;" + "-fx-min-width: 200");
        odrobeneTab.setContent(odrobeneTable);

        TabPane tabPane = new TabPane();
        tabPane.setMaxWidth(400);
        tabPane.setMinHeight(400);
        tabPane.getTabs().addAll(naplanovaneTab, odrobeneTab);
        tabPane.setStyle("-fx-tab-min-width: 171;" + "-fx-background-radius: 10");

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        System.out.println(ov.getValue().getText());
                        t.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: white;");
                        t1.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: green;" + "-fx-text-base-color: white;");
                    }
                }
        );

        Button pridatButton = new Button("PRIDAŤ");
        Button zrusitButton = new Button("ZRUŠIŤ");
        pridatButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: green;" + "-fx-font-size: 25;" + "-fx-text-fill: aliceblue;" + "-fx-background-radius: 10");
        zrusitButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: firebrick;" + "-fx-font-size: 25;" + "-fx-text-fill: white;" + "-fx-background-radius: 10");

        pridatButton.setOnAction(e -> {
            System.out.println("Vybraný tab: " + tabPane.getSelectionModel().getSelectedItem().getText() + " SMENY");
            new PridajOkno(tabPane.getSelectionModel().getSelectedItem().getText(), smeny, datum);

            //refresh tabulky
            naplanovaneTable.getItems().clear();
            naplanovaneTable.getItems().addAll(smeny.getNaplanovanaSmena());

            for(Smena smena : smeny.getNaplanovaneSmeny()) {
                System.out.println(smena.getDatum());
            }
        });

        zrusitButton.setOnAction(e -> {
            new ZrusOkno(datum);
        });

        // spodná časť
        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(pridatButton, zrusitButton);

        // container
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(titleLabel, obdobieContainer, tabPane, vyplataLabel, hBox);
        vBox.setStyle("-fx-background-color: lightgray;");

        hlavneOkno.setScene(new Scene(vBox, 500, 700));
        hlavneOkno.setResizable(false);
        hlavneOkno.show();
    }
}