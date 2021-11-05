package com.moma.fans.gui;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * Vista con panel principal de opciones.
 * @author JonanC
 */
public class HomeView extends VBox {

    public HomeView() {

        HBox topControls = new HBox();
        topControls.setAlignment(Pos.CENTER_RIGHT);
        Hyperlink hlLogout = new Hyperlink("Cerrar sesión");
        hlLogout.setBorder(Border.EMPTY);
        topControls.getChildren().add(hlLogout);

        TextFlow tFlow = new TextFlow();
        Text t1 = new Text("Panel de ");
        Text t2 = new Text("@Usuario");
        t2.setStyle("-fx-font-weight: bold");
        tFlow.getChildren().addAll(t1, t2);
        tFlow.setTextAlignment(TextAlignment.CENTER);

        SplitPane splitPane = new SplitPane();


        TableView<String> tblSessions = new TableView<>();

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab createdChallenges = new Tab("Mis retos");
        Tab acceptedChallenges = new Tab("Retos aceptados");
        Tab availableChallenges = new Tab("Retos disponibles");

        ListView<String> challengeList = new ListView<>();
        challengeList.getItems().addAll("Reto 1", "Reto 2", "Reto 3");

        createdChallenges.setContent(challengeList);

        tabPane.getTabs().add(createdChallenges);
        tabPane.getTabs().add(acceptedChallenges);
        tabPane.getTabs().add(availableChallenges);

        splitPane.getItems().addAll(tblSessions, tabPane);




        this.getChildren().addAll(topControls, tFlow, splitPane);

    }
}
