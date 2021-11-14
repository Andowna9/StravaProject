package com.moma.fans.gui.screens;

import com.moma.fans.controllers.UserController;
import com.moma.fans.data.dto.user.ProfileCreationDTO;
import com.moma.fans.gui.Screen;
import com.moma.fans.gui.ScreenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;

/**
 * Vista de creación de perfil de usuario.
 * @author AlexNitu
 * @author JonanC
 */
public class ProfileCreationScreen implements Screen {

    private UserController userController;

    DatePicker dpBirth;

    Spinner<Double> spinWeight;
    Spinner<Double> spinHeight;

    Slider sldMinHeartRate;
    Slider sldMaxHeartRate;

    Parent view;

    public ProfileCreationScreen(UserController userController) {

        this.userController = userController;

        view = createView();
    }

    private Parent createView() {

        VBox root = new VBox();

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(15);

        Label lblBirthDate = new Label("Fecha de nacimiento:");
        dpBirth = new DatePicker();

        Label lblWeight = new Label("Peso:");
        Label lblKg = new Label("kg");
        spinWeight = new Spinner<>();
        spinWeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,200, 45, 0.1));
        spinWeight.setEditable(true);

        Label lblHeight = new Label("Estatura:");
        Label lblCm = new Label("cm");
        spinHeight = new Spinner<>();
        spinHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 250, 160, 0.1));
        spinHeight.setEditable(true);

        Label lblMinHeartRate = new Label("Frecuencia cardiaca máxima:");
        sldMinHeartRate = new Slider(50, 220, 80);
        Label lblHeartRateMin = new Label("80");


        Label lblMaxHeartRate = new Label("Frecuencia cardiaca mínima:");
        sldMaxHeartRate = new Slider(50, 220, 80);
        Label lblHeartRateMax = new Label("80");

       // Button btnBack = new Button("Atrás");
        Button btnOK = new Button("Aceptar");

        gp.add(lblBirthDate, 0, 0); gp.add(dpBirth, 1, 0);
        gp.add(lblWeight, 0, 1); gp.add(spinWeight, 1, 1); gp.add(lblKg, 2,1);
        gp.add(lblHeight, 0, 2); gp.add(spinHeight, 1, 2); gp.add(lblCm, 2,2);
        gp.add(lblMinHeartRate, 0, 3); gp.add(sldMinHeartRate, 1, 3); gp.add(lblHeartRateMin, 2, 3);
        gp.add(lblMaxHeartRate, 0, 4); gp.add(sldMaxHeartRate, 1, 4); gp.add(lblHeartRateMax, 2, 4);
        /* gp.add(btnBack, 0, 5); */ gp.add(btnOK, 2, 5);

        sldMaxHeartRate.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                lblHeartRateMax.setText(String.valueOf(newValue.intValue()));
            }
        });

        sldMinHeartRate.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                lblHeartRateMin.setText(String.valueOf(newValue.intValue()));
            }
        });

        root.setAlignment(Pos.CENTER);
        root.getChildren().add(gp);

        // Events
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {

                    ProfileCreationDTO userDTO = new ProfileCreationDTO();
                    userDTO.setBirthDate(dpBirth.getValue());
                    userDTO.setHeight(spinHeight.getValue().floatValue());
                    userDTO.setWeight(spinWeight.getValue().floatValue());
                    userDTO.setMinHeartRate(sldMinHeartRate.valueProperty().getValue().shortValue());
                    userDTO.setMaxHeartRate(sldMaxHeartRate.valueProperty().getValue().shortValue());

                    userController.createProfile(userDTO);
                    ScreenController.getInstance().setScreen(ScreenController.State.HOME);

                }

                catch (RemoteException e) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error en la creación del perfil de usuario");
                    alert.setContentText(e.getCause().getMessage());
                    alert.showAndWait();
                }

            }
        });

        //btnBack.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.REGISTER));

        return root;
    }

    @Override
    public void initialize() {

        dpBirth.setValue(null); // Elimina el texto y el valor anterior
        spinWeight.getValueFactory().setValue(45.0d);
        spinHeight.getValueFactory().setValue(160.0d);
        sldMinHeartRate.setValue(80);
        sldMaxHeartRate.setValue(80);
    }

    @Override
    public Parent getView() {
        return view;
    }
}
