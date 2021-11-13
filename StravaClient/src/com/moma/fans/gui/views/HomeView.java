package com.moma.fans.gui.views;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;

import com.moma.fans.controllers.UserController;

import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.gui.ScreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * Vista con panel principal de opciones.
 * @author JonanC
 * @author UnaiCL
 */
public class HomeView extends VBox {

	private UserController controller;
	
    public HomeView(UserController controller) {
    	this.controller = controller;
    	
    	// Cerrar sesión (Parte superior)
        HBox topControls = new HBox();
        topControls.setAlignment(Pos.CENTER_RIGHT);
        Hyperlink hlLogout = new Hyperlink("Cerrar sesión");
        hlLogout.setBorder(Border.EMPTY);
        hlLogout.setPadding(new Insets(10, 21, 0, 10));
        topControls.getChildren().add(hlLogout);

        // Texto superior, dedicado a cada usuario
        TextFlow tFlow = new TextFlow();
        Text t1 = new Text("Panel de ");
        Text t2 = new Text("@Usuario");
        t2.setStyle("-fx-font-weight: bold");
        tFlow.getChildren().addAll(t1, t2);
        tFlow.setTextAlignment(TextAlignment.CENTER);
        tFlow.setPadding(new Insets(0, 0, 15, 0));

        /*
         * |--------------------------------------------|
         *  Creación del splitpane
         */
        
        SplitPane splitPane = new SplitPane();

        // Creación y configuración de las cajas
        VBox sessionsBox = new VBox();
        VBox challengesBox = new VBox();
           
        sessionsBox.setAlignment(Pos.CENTER);
        sessionsBox.setPadding(new Insets(0, 0, 12, 0));
        sessionsBox.setSpacing(12);
        
        challengesBox.setAlignment(Pos.CENTER);
        challengesBox.setPadding(new Insets(0, 0, 12, 0));
        challengesBox.setSpacing(12);
        
        // Panel de sesiones
        TableView<TrainingSessionDTO> tblSessions = new TableView<>();

        TableColumn<TrainingSessionDTO, String> colTitle = new TableColumn<>("Título");
        TableColumn<TrainingSessionDTO, String> colSport = new TableColumn<>("Deporte");
        TableColumn<TrainingSessionDTO, Float> colDistance = new TableColumn<>("Distancia");
        TableColumn<TrainingSessionDTO, LocalDateTime> colDateTime = new TableColumn<>("Fecha/hora");
        TableColumn<TrainingSessionDTO, Duration> colDuration = new TableColumn<>("Tiempo total");

        tblSessions.getColumns().addAll(colTitle, colSport, colDistance, colDateTime, colDuration);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab createdChallenges = new Tab("Mis retos");
        Tab acceptedChallenges = new Tab("Retos aceptados");
        Tab availableChallenges = new Tab("Retos disponibles");

        // Panel de challenges
        ListView<String> challengeList = new ListView<>();
        challengeList.getItems().addAll("Reto 1", "Reto 2", "Reto 3");

        createdChallenges.setContent(challengeList);

        tabPane.getTabs().add(createdChallenges);
        tabPane.getTabs().add(acceptedChallenges);
        tabPane.getTabs().add(availableChallenges);

        // Botones
        Button btnCreateSession = new Button("Crear sesión de entrenamiento");
        Button btnCreateChallenge = new Button("Crear reto");
        
        // Añadir elementos a las VBox
        sessionsBox.getChildren().addAll(tblSessions, btnCreateSession);
        challengesBox.getChildren().addAll(tabPane, btnCreateChallenge);
        
        /*
         * |--------------------------------------------|
         *  FIN Creación del splitpane
         */
        
        // Añadir las VBox a los paneles
        splitPane.getItems().addAll(sessionsBox, challengesBox);
        
        
        this.getChildren().addAll(topControls, tFlow, splitPane);
        
        // Eventos |----------------------------------|
        btnCreateSession.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.TRAINING_SESSION_CREATION));
        btnCreateChallenge.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.CHALLENGE_CREATION));
        hlLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");

                ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN);

               try {
                    controller.logout();
                    ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN);
                } catch (RemoteException e) {
                    alert.setHeaderText("Error al iniciar sesión");
                    alert.setContentText(e.getCause().getMessage());
                    alert.showAndWait();
                }
            }
        });

    }

}
