package com.moma.fans.views;

import com.moma.fans.controllers.UserController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Vista de creación de perfil de usuario.
 * @author AlexNitu
 * @author JonanC
 */
public class ProfileCreationView extends VBox {

    private UserController controller;

    public ProfileCreationView(UserController controller) {

        this.controller = controller;

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(15);

        // Campos obligatorios

        Label lblName = new Label("Nombre de usuario:");
        TextField tfName = new TextField();

        Label lblBirthDate = new Label("Fecha de nacimiento:");
        DatePicker dpBirth = new DatePicker();

        // Campos opcionales

        Label lblWeight = new Label("Peso:");
        Label lblKg = new Label("kg");
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,200, 45, 0.1);
        Spinner<Double> spinWeight = new Spinner<>();
        spinWeight.setValueFactory(valueFactory);
        spinWeight.setEditable(true);

        Label lblMinHeartRate = new Label("Frecuencia cardiaca máxima:");
        Slider sldMinHeartRate = new Slider(50, 220, 80);
        Label lblHeartRateMin = new Label("80");


        Label lblMaxHeartRate = new Label("Frecuencia cardiaca mínima:");
        Slider sldMaxHeartRate = new Slider(50, 220, 80);
        Label lblHeartRateMax = new Label("80");

        Button btnBack = new Button("Atrás");
        Button btnOK = new Button("OK");

        gp.add(lblName, 0, 0); gp.add(tfName, 1, 0);
        gp.add(lblBirthDate, 0, 1); gp.add(dpBirth, 1, 1);
        gp.add(lblWeight, 0, 2); gp.add(spinWeight, 1, 2); gp.add(lblKg, 2,2);
        gp.add(lblMinHeartRate, 0, 3); gp.add(sldMinHeartRate, 1, 3); gp.add(lblHeartRateMin, 2, 3);
        gp.add(lblMaxHeartRate, 0, 4); gp.add(sldMaxHeartRate, 1, 4); gp.add(lblHeartRateMax, 2, 4);
        gp.add(btnBack, 0, 5); gp.add(btnOK, 2, 5);

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
        btnOK.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.HOME));
        btnBack.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.REGISTER));

    }
}
