package com.moma.fans.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProfileCreationView extends VBox {

    public ProfileCreationView() {

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10.5);

        // Campos obligatorios

        Label lblName = new Label("Nombre:");
        TextField tfName = new TextField();

        Label lblSurname = new Label("Apellido:");
        TextField tfSurname = new TextField();

        Label lblBirthDate = new Label("Fecha de nacimiento:");
        DatePicker dpBirth = new DatePicker();

        // Campos opcionales

        Label lblWeight = new Label("Peso:");
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,200, 45, 0.1);
        Spinner<Double> spinWeight = new Spinner<>();
        spinWeight.setValueFactory(valueFactory);
        spinWeight.setEditable(true);

        Label lblMinHeartRate = new Label("Frecuencia cardiaca máxima:");
        Slider sldMinHeartRate = new Slider(50, 220, 80);

        Label lblMaxHeartRate = new Label("Frecuencia cardiaca mínima:");
        Slider sldMaxHeartRate = new Slider(50, 220, 100);

        gp.add(lblName, 0, 0); gp.add(tfName, 1, 0);
        gp.add(lblSurname, 0, 1); gp.add(tfSurname, 1, 1);
        gp.add(lblBirthDate, 0, 2); gp.add(dpBirth, 1, 2);
        gp.add(lblWeight, 0, 3); gp.add(spinWeight, 1, 3);
        gp.add(lblMinHeartRate, 0, 4); gp.add(sldMinHeartRate, 1, 4);
        gp.add(lblMaxHeartRate, 0, 5); gp.add(sldMaxHeartRate, 1, 5);

        final ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth( 50 );
        // gp.getColumnConstraints().addAll(col, col);

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(gp);

    }
}
