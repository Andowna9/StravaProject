package com.moma.fans.gui;

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

    public ProfileCreationView() {

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(15);

        // Campos obligatorios

        Label lblName = new Label("Nombre:");
        TextField tfName = new TextField();

        Label lblSurname = new Label("Apellido:");
        TextField tfSurname = new TextField();

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
        gp.add(lblSurname, 0, 1); gp.add(tfSurname, 1, 1);
        gp.add(lblBirthDate, 0, 2); gp.add(dpBirth, 1, 2);
        gp.add(lblWeight, 0, 3); gp.add(spinWeight, 1, 3); gp.add(lblKg, 2,3);
        gp.add(lblMinHeartRate, 0, 4); gp.add(sldMinHeartRate, 1, 4); gp.add(lblHeartRateMin, 2, 4);
        gp.add(lblMaxHeartRate, 0, 5); gp.add(sldMaxHeartRate, 1, 5); gp.add(lblHeartRateMax, 2, 5);
        gp.add(btnBack, 0, 7); gp.add(btnOK, 2, 7);

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

    }
}
