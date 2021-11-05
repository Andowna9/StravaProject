package com.moma.fans.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Vista que permite crear un reto.
 * @author UnaiCL
 */
public class CreateChallengeView extends Parent {

	public CreateChallengeView() {
        VBox mainVbox = new VBox();
        mainVbox.setSpacing(15.0d);
        mainVbox.setPadding(new Insets(80.0d));
        
        //Creación de labels
        Label lblTitle = new Label("Crear nuevo reto");
        lblTitle.setStyle("-fx-font-size: 16px;");
        
        Label lblName = new Label("Nombre");       
        Label lblSport = new Label("Deporte"); 
  	  
        //Creación de Textfields      
        TextField tfName = new TextField();           
        TextField tfSport = new TextField();  
         
        //Creación de Buttons 
        Button btnCreate = new Button("Crear"); 
        btnCreate.setStyle("-fx-background-color: #99ff99;");
        
        Button btnCancel = new Button("Cancelar");
        btnCancel.setStyle("-fx-background-color: #ff6666;");
        
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        
         
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(10); 
        gridPane.setHgap(20);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
         
        //Arranging all the nodes in the grid 
        gridPane.add(lblName, 0, 0); 
        gridPane.add(tfName, 0, 1); 
        gridPane.add(lblSport, 1, 0);       
        gridPane.add(tfSport, 1, 1); 
        gridPane.add(btnCreate, 0, 2); 
        gridPane.add(btnCancel, 1, 2);  
        
        mainVbox.getChildren().addAll(lblTitle, gridPane);
        
        mainVbox.setAlignment(Pos.CENTER);
        
        this.getChildren().add(mainVbox);
	}
	
}
