package com.moma.fans.gui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista que permite crear un reto.
 * @author UnaiCL
 */
public class CreateChallengeView extends Parent {

	public CreateChallengeView() {
		
		
        // Vertical
        VBox mainVbox = new VBox();
        mainVbox.setSpacing(15.0d);
	    mainVbox.setPadding(new Insets(10.0d, 0, 0, 50.0d));
        
		// Horizontal superior
        HBox topControls = new HBox();
        topControls.setAlignment(Pos.CENTER_RIGHT);
        Hyperlink hlLogout = new Hyperlink("Cerrar sesión");
        hlLogout.setBorder(Border.EMPTY);
        hlLogout.setAlignment(Pos.CENTER_RIGHT);
        topControls.getChildren().add(hlLogout);
        
        //Creación de labels
        Label lblTitle = new Label("Crear nuevo reto");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Label lblName = new Label("Nombre");       
        Label lblSport = new Label("Deporte");
        Label lblStartDate = new Label("Fecha inicio");
        Label lblEndDate = new Label("Fecha fin");
  	  
        //Creación de Textfields      
        TextField tfName = new TextField();
        tfName.setPromptText("Nombre del reto");
        TextField tfOptionNums = new TextField();
        tfOptionNums.setPromptText("kms/mins");
        
        // Creación ComboBox
        ObservableList<String> sports = FXCollections.observableArrayList();
        sports.addAll("Running", "Ciclismo");
        ComboBox<String> cbxSports = new ComboBox<>(sports);
        cbxSports.setMinWidth(181);
         
        //Creación de Buttons 
        Button btnCreate = new Button("Crear"); 
        btnCreate.setStyle("-fx-background-color: #99ff99;");
        btnCreate.setMinWidth(80);
        
        Button btnCancel = new Button("Cancelar");
        btnCancel.setStyle("-fx-background-color: #ff6666;");
        btnCancel.setMinWidth(80);
        
        // Creación calendarios
        DatePicker dpStartDate = new DatePicker();
        DatePicker dpEndDate = new DatePicker();
        
        dpStartDate.setValue(LocalDate.now());
        dpEndDate.setValue(dpStartDate.getValue().plusDays(1));
        
        // Creacion radioButtons
        final ToggleGroup group = new ToggleGroup();
        VBox radioVB = new VBox();

        RadioButton rbDistance = new RadioButton("km (Distancia)");
        rbDistance.setToggleGroup(group);
        rbDistance.setSelected(true);

        RadioButton rbTime = new RadioButton("min (Tiempo)");
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
        
        HBox raddioBox = new HBox();
        raddioBox.setAlignment(Pos.CENTER);
        raddioBox.setSpacing(50);
        raddioBox.getChildren().addAll(tfOptionNums, radioVB);
        
        //Añadir secciones al vertical
        mainVbox.getChildren().addAll(topControls, lblTitle, gridPane, raddioBox, buttonsBox);
        
        mainVbox.setAlignment(Pos.CENTER);
        
        // Añadir secciones a la vista
        this.getChildren().addAll(mainVbox);
        
        // Eventos |----------------------------------|
        btnCreate.setOnAction(event -> ScreenController.getInstance().setScreen("Home"));
        btnCancel.setOnAction(event -> ScreenController.getInstance().setScreen("Home"));
        
	}
	
}
