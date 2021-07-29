package GUI;

import Skripts.Datum;
import Skripts.Smena;
import Skripts.Smeny;
import Skripts.User;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MyMCD extends Application {
    private Stage hlavneOkno;
    private ImageView imageView = new ImageView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage okno) throws FileNotFoundException {
        Datum datum = new Datum();
        Smeny smeny = new Smeny();
        User user = new User("1.12.2000", true, 3.6, 1);
        smeny.nacitajSmeny();

        hlavneOkno = okno;
        hlavneOkno.setTitle("MCD ZMENY");

        // vrchná časť
        Label titleLabel = new Label("ZMENY");
        titleLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 30;" + "-fx-background-color: green;" + "-fx-text-fill: white;");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setMinWidth(360);

        Image image = new Image(new FileInputStream("./Pictures/ProfilIcon.png"));
        ImageView imageView = new ImageView(image);
        imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setImage(image);
        Button profilButton = new Button();
        profilButton.setGraphic(imageView);
        profilButton.setMaxWidth(40);
        profilButton.setMaxHeight(40);
        profilButton.setPadding(new Insets(0,0,0,0));
        profilButton.setStyle("-fx-background-radius: 40;" + "-fx-focus-color: transparent;");
        profilButton.setOnAction(e -> new ProfilOkno());

        HBox topContainer = new HBox();
        topContainer.setMinHeight(60);
        topContainer.setAlignment(Pos.CENTER_RIGHT);
        topContainer.setPadding(new Insets(0,20,0,0));
        topContainer.setStyle("-fx-background-color: green;");
        topContainer.getChildren().addAll(titleLabel, profilButton);

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
        naplanovaneTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        naplanovaneTable.setPlaceholder(new Label("Zatiaľ nie sú pridané žiadne zmeny"));

        TableColumn<Smena, String> datumColumnNaplanovane = new TableColumn<>("Dátum");
        datumColumnNaplanovane.setStyle("-fx-alignment: CENTER;");
        datumColumnNaplanovane.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatumString()));

        TableColumn<Smena, String> denColumnNaplanovane = new TableColumn<>("Deň");
        denColumnNaplanovane.setStyle("-fx-alignment: CENTER;");
        denColumnNaplanovane.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDenString()));

        TableColumn<Smena, String> odColumnNaplanovane = new TableColumn<>("Od");
        odColumnNaplanovane.setStyle("-fx-alignment: CENTER;");
        odColumnNaplanovane.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOd()));

        TableColumn<Smena, String> doColumnNaplanovane = new TableColumn<>("Do");
        doColumnNaplanovane.setStyle("-fx-alignment: CENTER;");
        doColumnNaplanovane.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDo()));

        TableColumn<Smena, String> hodinColumnNaplanovane = new TableColumn<>("Hodín");
        hodinColumnNaplanovane.setStyle("-fx-alignment: CENTER;");
        hodinColumnNaplanovane.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHodinDokopy(user)));

        naplanovaneTable.getColumns().addAll(datumColumnNaplanovane, denColumnNaplanovane, odColumnNaplanovane, doColumnNaplanovane, hodinColumnNaplanovane);
        naplanovaneTable.setItems(smeny.getNaplanovaneSmenyObservable(datum));

        TableView<Smena> odrobeneTable = new TableView<>();
        odrobeneTable.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 15;");
        odrobeneTable.setEditable(false);
        odrobeneTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        odrobeneTable.setPlaceholder(new Label("Zatiaľ nie sú pridané žiadne zmeny"));

        TableColumn<Smena, String> datumColumnOdrobene = new TableColumn<>("Dátum");
        datumColumnOdrobene.setStyle("-fx-alignment: CENTER;");
        datumColumnOdrobene.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatumString()));

        TableColumn<Smena, String> denColumnOdrobene = new TableColumn<>("Deň");
        denColumnOdrobene.setStyle("-fx-alignment: CENTER;");
        denColumnOdrobene.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDenString()));

        TableColumn<Smena, String> odColumnOdrobene = new TableColumn<>("Od");
        odColumnOdrobene.setStyle("-fx-alignment: CENTER;");
        odColumnOdrobene.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOd()));

        TableColumn<Smena, String> doColumnOdrobene = new TableColumn<>("Do");
        doColumnOdrobene.setStyle("-fx-alignment: CENTER;");
        doColumnOdrobene.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDo()));

        TableColumn<Smena, String> hodinColumnOdrobene = new TableColumn<>("Hodín");
        hodinColumnOdrobene.setStyle("-fx-alignment: CENTER;");
        hodinColumnOdrobene.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHodinDokopy(user)));

        odrobeneTable.getColumns().addAll(datumColumnOdrobene, denColumnOdrobene, odColumnOdrobene, doColumnOdrobene, hodinColumnOdrobene);
        odrobeneTable.setItems(smeny.getOdrobeneSmenyObservable(datum));

        Tab naplanovaneTab = new Tab("NAPLÁNOVANÉ");
        naplanovaneTab.setClosable(false);
        naplanovaneTab.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: green;" + "-fx-text-base-color: white;");

        VBox naplanovaneBox = new VBox();
        naplanovaneBox.setAlignment(Pos.CENTER);
        naplanovaneBox.getChildren().addAll(naplanovaneTable);
        naplanovaneTab.setContent(naplanovaneBox);

        Tab odrobeneTab = new Tab("ODROBENÉ");
        odrobeneTab.setClosable(false);
        odrobeneTab.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: white;");

        VBox odrobeneBox = new VBox();
        odrobeneBox.setAlignment(Pos.CENTER);
        odrobeneBox.getChildren().addAll(odrobeneTable);
        odrobeneTab.setContent(odrobeneBox);

        TabPane tabPane = new TabPane();
        tabPane.setMaxWidth(400);
        tabPane.setMinHeight(400);
        tabPane.getTabs().addAll(naplanovaneTab, odrobeneTab);
        tabPane.setStyle("-fx-tab-min-width: 171;" + "-fx-background-radius: 10;" + "-fx-focus-color: transparent;");

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        //System.out.println(ov.getValue().getText());
                        t.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: white;");
                        t1.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-font-size: 20;" + "-fx-background-color: green;" + "-fx-text-base-color: white;");
                    }
                }
        );

        Label vyplataLabel = new Label("VÝPLATA: ");
        vyplataLabel.setAlignment(Pos.CENTER_LEFT);
        vyplataLabel.setMinWidth(190);
        vyplataLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: white;" + "-fx-font-size: 20;" + "-fx-background-radius: 10;");

        Label vyplataHodnotaLabel = new Label(smeny.vypocitajVyplatu(mounthChoice.getSelectionModel().getSelectedIndex(), yearChoice.getSelectionModel().getSelectedItem(), user) + " €");
        vyplataHodnotaLabel.setAlignment(Pos.CENTER_RIGHT);
        vyplataHodnotaLabel.setMinWidth(190);
        vyplataHodnotaLabel.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: white;" + "-fx-font-size: 20;" + "-fx-background-radius: 10;");

        Button pridatButton = new Button("PRIDAŤ");
        Button zrusitButton = new Button("ZRUŠIŤ");
        pridatButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: green;" + "-fx-font-size: 25;" + "-fx-text-fill: aliceblue;" + "-fx-background-radius: 10");
        zrusitButton.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: firebrick;" + "-fx-font-size: 25;" + "-fx-text-fill: white;" + "-fx-background-radius: 10");

        pridatButton.setOnAction(e -> {
            // System.out.println("Vybraný tab: " + tabPane.getSelectionModel().getSelectedItem().getText() + " SMENY");
            new PridajOkno(user, smeny, datum);

            //refresh tabulky
            naplanovaneTable.getItems().clear();
            odrobeneTable.getItems().clear();
            naplanovaneTable.getItems().addAll(smeny.getNaplanovaneSmenyObservable(datum));
            odrobeneTable.getItems().addAll(smeny.getOdrobeneSmenyObservable(datum));

            vyplataHodnotaLabel.setText(smeny.vypocitajVyplatu(mounthChoice.getSelectionModel().getSelectedIndex(), yearChoice.getSelectionModel().getSelectedItem(), user) + " €");
            smeny.ulozSmeny();
        });

        zrusitButton.setOnAction(e -> {
            new ZrusOkno(smeny, datum);

            //refresh tabulky
            naplanovaneTable.getItems().clear();
            odrobeneTable.getItems().clear();
            naplanovaneTable.getItems().addAll(smeny.getNaplanovaneSmenyObservable(datum));
            odrobeneTable.getItems().addAll(smeny.getOdrobeneSmenyObservable(datum));

            vyplataHodnotaLabel.setText(smeny.vypocitajVyplatu(mounthChoice.getSelectionModel().getSelectedIndex(), yearChoice.getSelectionModel().getSelectedItem(), user) + " €");
            smeny.ulozSmeny();
        });

        // spodná časť
        HBox vyplataBox = new HBox();
        vyplataBox.setMinHeight(40);
        vyplataBox.setMaxWidth(380);
        vyplataBox.setAlignment(Pos.CENTER);
        vyplataBox.setPadding(new Insets(0, 10, 0, 10));
        vyplataBox.setStyle("-fx-font-family: 'Source Sans Pro';" + "-fx-background-color: white;" + "-fx-font-size: 20;" + "-fx-background-radius: 10;");
        vyplataBox.getChildren().addAll(vyplataLabel, vyplataHodnotaLabel);

        HBox tlacidlaBox = new HBox(20);
        tlacidlaBox.setAlignment(Pos.CENTER);
        tlacidlaBox.getChildren().addAll(pridatButton, zrusitButton);

        // container
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(topContainer, obdobieContainer, tabPane, vyplataBox, tlacidlaBox);
        vBox.setStyle("-fx-background-color: lightgray;");

        hlavneOkno.setScene(new Scene(vBox, 500, 700));
        hlavneOkno.setResizable(false);
        hlavneOkno.show();
    }
}