package com.moma.fans.gui.views;

import com.moma.fans.controllers.UserController;
import com.moma.fans.gui.IReset;
import com.moma.fans.gui.ScreenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Vista de creación de perfil de usuario.
 * @author AlexNitu
 * @author JonanC
 */
public class ProfileCreationView extends VBox implements IReset {

    private UserController controller;

    TextField tfName;
    DatePicker dpBirth;

    Spinner<Double> spinWeight;
    Spinner<Double> spinHeight;

    Slider sldMinHeartRate;
    Slider sldMaxHeartRate;

    public ProfileCreationView(UserController controller) {

        this.controller = controller;

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(15);

        // Campos obligatorios

        Label lblName = new Label("Nombre de usuario:");
        tfName = new TextField();

        Label lblBirthDate = new Label("Fecha de nacimiento:");
        dpBirth = new DatePicker();

        // Campos opcionales

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

        Button btnBack = new Button("Atrás");
        Button btnOK = new Button("Aceptar");

        gp.add(lblName, 0, 0); gp.add(tfName, 1, 0);
        gp.add(lblBirthDate, 0, 1); gp.add(dpBirth, 1, 1);
        gp.add(lblWeight, 0, 2); gp.add(spinWeight, 1, 2); gp.add(lblKg, 2,2);
        gp.add(lblHeight, 0, 3); gp.add(spinHeight, 1, 3); gp.add(lblCm, 2,3);
        gp.add(lblMinHeartRate, 0, 4); gp.add(sldMinHeartRate, 1, 4); gp.add(lblHeartRateMin, 2, 4);
        gp.add(lblMaxHeartRate, 0, 5); gp.add(sldMaxHeartRate, 1, 5); gp.add(lblHeartRateMax, 2, 5);
        gp.add(btnBack, 0, 6); gp.add(btnOK, 2, 6);

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

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(gp);
        
        // Events
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ScreenController.getInstance().setScreen(ScreenController.State.HOME);
                ScreenController.getInstance().resetLayout(ScreenController.State.REGISTER);
                resetLayout();
            }
        });
        btnBack.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.REGISTER));

    }

    @Override
    public void resetLayout() {

        tfName.clear();
        dpBirth.setValue(null); // Elimina el texto y el valor anterior
        spinWeight.getValueFactory().setValue(45.0d);
        spinHeight.getValueFactory().setValue(160.0d);
        sldMinHeartRate.setValue(80);
        sldMaxHeartRate.setValue(80);

    }
}
