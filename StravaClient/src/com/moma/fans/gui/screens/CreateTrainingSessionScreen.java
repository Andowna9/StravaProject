package com.moma.fans.gui.screens;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.moma.fans.controllers.TrainingSessionController;
import com.moma.fans.controllers.UserController;
import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.gui.Screen;
import com.moma.fans.gui.ScreenController;
import com.moma.fans.gui.components.StravaAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista que permite crear una sesión de entrenamiento.
 * @author Julen396
 * @author JonanC
 */
public class CreateTrainingSessionScreen implements Screen {

    private UserController userController;
    private TrainingSessionController trainingSessionController;

    private TextField tfName;
    private TextField tfStartime;
    private TextField tfDuration;
    private TextField tfDistance;
    private ComboBox<String> cbxSports;
    private DatePicker dpStartDate;

    private Parent view;

	public CreateTrainingSessionScreen(UserController userController, TrainingSessionController trainingSessionController) {

        this.userController = userController;
        this.trainingSessionController = trainingSessionController;

        view = createView();

	}

    private Parent createView() {

        // Vertical
        VBox root = new VBox();
        root.setSpacing(25);

        //Creación de labels
        Label lblTitle = new Label("Crear nueva sesión de entrenamiento");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblName = new Label("Nombre");
        Label lblSport = new Label("Deporte");
        Label lblStartDate = new Label("Fecha inicio");
        Label lblStartime = new Label("Hora de inicio");
        Label lblDuration = new Label("Duracion (Minutos)");
        Label lblDistance = new Label("Distancia (Kilometros)");

        //Creación de Textfields
        tfName = new TextField();
        tfName.setPromptText("Titulo de la sesion");
        tfStartime = new TextField();
        tfStartime.setPromptText("hh:mm");
        tfDuration = new TextField();
        tfDuration.setPromptText("min");
        tfDistance = new TextField();
        tfDistance.setPromptText("km");

        // Creación ComboBox
        ObservableList<String> sports = FXCollections.observableArrayList();
        sports.addAll("Running", "Ciclismo");
        cbxSports = new ComboBox<>(sports);
        cbxSports.getSelectionModel().selectFirst();
        cbxSports.setMinWidth(181);

        //Creación de Buttons
        Button btnCreate = new Button("Crear sesion");
        btnCreate.setStyle("-fx-background-color: #99ff99;");
        btnCreate.setMinWidth(80);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setStyle("-fx-background-color: #ff6666;");
        btnCancel.setMinWidth(80);

        // Creación calendarios
        dpStartDate = new DatePicker();

        dpStartDate.setValue(LocalDate.now());

        //Creación del Grid \-------------------/
        GridPane gridPane = new GridPane();


        //Ajustes visuales
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);

        //Añadir los nodos al grid
        gridPane.add(lblName, 0, 0);
        gridPane.add(tfName, 0, 1);
        gridPane.add(lblSport, 1, 0);
        gridPane.add(cbxSports, 1, 1);
        gridPane.add(lblStartDate, 0, 2);
        gridPane.add(lblStartime, 1, 2);
        gridPane.add(dpStartDate, 0, 3);
        gridPane.add(tfStartime, 1, 3);
        gridPane.add(lblDuration, 0, 4);
        gridPane.add(lblDistance, 1, 4);
        gridPane.add(tfDuration, 0, 5);
        gridPane.add(tfDistance, 1, 5);

        // Crear cajas inferiores
        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);
        buttonsBox.getChildren().addAll(btnCreate, btnCancel);

        //Añadir secciones al vertical (vista)
        root.getChildren().addAll(lblTitle, gridPane, buttonsBox);

        root.setAlignment(Pos.CENTER);

        // Eventos |----------------------------------|
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {

                    TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();
                    trainingSessionDTO.setTitle(tfName.getText());
                    trainingSessionDTO.setSport(cbxSports.getValue());
                    trainingSessionDTO.setDateTime(LocalDateTime.of(dpStartDate.getValue(), LocalTime.parse(tfStartime.getText())));
                    trainingSessionDTO.setDuration(Duration.ofMinutes(Long.parseLong(tfDuration.getText())));
                    trainingSessionDTO.setDistance(Double.parseDouble(tfDistance.getText()));
                    trainingSessionController.createTrainingSession(userController.getToken(), trainingSessionDTO);
                    ScreenController.getInstance().setScreen(ScreenController.State.HOME);

                }

                catch (RemoteException e) {

                    Alert alert = new StravaAlert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error al crear sesión de entrenamiento");
                    alert.setContentText(e.getCause().getMessage());
                    alert.showAndWait();
                }


            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ScreenController.getInstance().setScreen(ScreenController.State.HOME);
            }
        });

        return root;

    }

    @Override
    public void initialize() {

        tfName.clear();
        tfStartime.clear();
        tfDuration.clear();
        tfDistance.clear();
        dpStartDate.setValue(null);
    }

    @Override
    public Parent getView() {

        return view;
    }
}
