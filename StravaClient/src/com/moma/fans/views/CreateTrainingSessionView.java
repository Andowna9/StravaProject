package com.moma.fans.views;

import java.time.LocalDate;

import com.moma.fans.controllers.TrainingSessionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista que permite crear una sesion de entrenamiento
 * @author Julen396
 */
public class CreateTrainingSessionView extends VBox {

    private TrainingSessionController controller;
	public CreateTrainingSessionView(TrainingSessionController controller) {

        this.controller = controller;

        // Vertical
        this.setSpacing(25);
        
        //Creación de labels
        Label lblTitle = new Label("Crear nueva sesión de entrenamiento");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Label lblName = new Label("Titulo");       
        Label lblSport = new Label("Deporte");
        Label lblStartDate = new Label("Fecha inicio");
        Label lblStartime = new Label("Hora de inicio");
        Label lblDuration = new Label("Duracion (Minutos)");
        Label lblDistance = new Label("Distancia (Kilometros)");        
  	  
        //Creación de Textfields      
        TextField tfName = new TextField();
        tfName.setPromptText("Titulo de la sesion");
        TextField tfStartime = new TextField();
        tfStartime.setPromptText("hh:mm");
        TextField tfDuration = new TextField();
        tfDuration.setPromptText("min");
        TextField tfDistance = new TextField();
        tfDistance.setPromptText("km");        
        
        // Creación ComboBox
        ObservableList<String> sports = FXCollections.observableArrayList();
        sports.addAll("Running", "Ciclismo");
        ComboBox<String> cbxSports = new ComboBox<>(sports);
        cbxSports.setMinWidth(181);
         
        //Creación de Buttons 
        Button btnCreate = new Button("Crear sesion"); 
        btnCreate.setStyle("-fx-background-color: #99ff99;");
        btnCreate.setMinWidth(80);
        
        Button btnCancel = new Button("Cancelar");
        btnCancel.setStyle("-fx-background-color: #ff6666;");
        btnCancel.setMinWidth(80);
        
        // Creación calendarios
        DatePicker dpStartDate = new DatePicker();
        
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
        this.getChildren().addAll(lblTitle, gridPane, buttonsBox);
        
        this.setAlignment(Pos.CENTER);
        
        // Eventos |----------------------------------|
        btnCreate.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.HOME));
        btnCancel.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.HOME));
        
	}
	
}
