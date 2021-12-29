package com.moma.fans.gui.screens;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDate;

import com.moma.fans.controllers.ChallengeController;
import com.moma.fans.controllers.UserController;
import com.moma.fans.data.dto.challenge.ChallengeCreationDTO;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista que permite crear un reto.
 * @author UnaiCL
 */
public class CreateChallengeScreen implements Screen {

    private UserController userController;
    private ChallengeController challengeController;

    private TextField tfName;
    private ComboBox<String> cbxSports;
    private DatePicker dpStartDate;
    private DatePicker dpEndDate;

    private ToggleGroup group;
    private RadioButton rbDistance;
    private RadioButton rbTime;
    private TextField tfOptionNums;

    private Parent view;

	public CreateChallengeScreen(UserController userController, ChallengeController challengeController) {


		this.userController = userController;
        this.challengeController = challengeController;

        view = createView();

	}

    public Parent createView() {

        // Vertical
        VBox root = new VBox();
        root.setSpacing(25);

        //Creación de labels
        Label lblTitle = new Label("Crear nuevo reto");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblName = new Label("Nombre");
        Label lblSport = new Label("Deporte");
        Label lblStartDate = new Label("Fecha inicio");
        Label lblEndDate = new Label("Fecha fin");

        //Creación de Textfields
        tfName = new TextField();
        tfName.setPromptText("Título del reto");
        tfOptionNums = new TextField();
        tfOptionNums.setPromptText("kms/mins");

        // Creación ComboBox
        ObservableList<String> sports = FXCollections.observableArrayList();
        sports.addAll("Running", "Ciclismo");
        cbxSports = new ComboBox<>(sports);
        cbxSports.getSelectionModel().selectFirst();
        cbxSports.setMinWidth(181);

        //Creación de Buttons
        Button btnCreate = new Button("Crear");
        btnCreate.setStyle("-fx-background-color: #99ff99;");
        btnCreate.setMinWidth(80);

        Button btnCancel = new Button("Cancelar");
        btnCancel.setStyle("-fx-background-color: #ff6666;");
        btnCancel.setMinWidth(80);

        // Creación calendarios
        dpStartDate = new DatePicker();
        dpEndDate = new DatePicker();

        dpStartDate.setValue(LocalDate.now());
        dpEndDate.setValue(dpStartDate.getValue().plusDays(1));

        // Creacion radioButtons
        group = new ToggleGroup();
        VBox radioVB = new VBox();

        rbDistance = new RadioButton("km (Distancia)");
        rbDistance.setToggleGroup(group);
        rbDistance.setSelected(true);

        rbTime = new RadioButton("min (Tiempo)");
        rbTime.setToggleGroup(group);

        radioVB.getChildren().addAll(rbDistance, rbTime);

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
        gridPane.add(lblEndDate, 1, 2);
        gridPane.add(dpStartDate, 0, 3);
        gridPane.add(dpEndDate, 1, 3);

        // Crear cajas inferiores
        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(50);
        buttonsBox.getChildren().addAll(btnCreate, btnCancel);

        HBox radioBox = new HBox();
        radioBox.setAlignment(Pos.CENTER);
        radioBox.setSpacing(50);
        radioBox.getChildren().addAll(tfOptionNums, radioVB);

        //Añadir secciones al vertical (vista)
        root.getChildren().addAll(lblTitle, gridPane, radioBox, buttonsBox);

        root.setAlignment(Pos.CENTER);

        // Eventos |----------------------------------|
        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {

                    ChallengeCreationDTO challengeCreationDTO = new ChallengeCreationDTO();

                    challengeCreationDTO.setTitle(tfName.getText());
                    challengeCreationDTO.setStartDate(dpStartDate.getValue());
                    challengeCreationDTO.setEndDate(dpEndDate.getValue());
                    challengeCreationDTO.setSport(cbxSports.getValue());

                    if (group.getSelectedToggle() == rbDistance) {

                        challengeController.createDistanceChallenge(userController.getToken(),
                                challengeCreationDTO, Double.parseDouble(tfOptionNums.getText()));
                    }

                    else {

                        challengeController.createTimeChallenge(userController.getToken(),
                                challengeCreationDTO, Duration.ofMinutes(Long.parseLong(tfOptionNums.getText())));

                    }

                    ScreenController.getInstance().setScreen(ScreenController.State.HOME);
                }

                catch (RemoteException e) {

                    Alert alert = new StravaAlert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error al crear reto");
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
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
    }

    @Override
    public Parent getView() {

        return view;
    }
}
