package com.moma.fans;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.rmi.Naming;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();
        root.getChildren().add(new Button("Press me!"));

        stage.setScene(new Scene(root, 300, 200));
        stage.setResizable(false);
        stage.show();

    }
/*Prueba */
    public static void main(String [] args) {

        // RMI Test

        /*

        try {

            ServerAPI serviceStub = (ServerAPI) Naming.lookup("//localhost:1099/StravaServer");
            System.out.println("Server says: " + serviceStub.test());

        } catch (Exception e) {

            System.err.println("Exception running client: " + e.getMessage());
            e.printStackTrace();
        } */

        // JAVAFX Test

        launch();
    }
}
