package com.moma.fans;

import com.moma.fans.gui.CreateChallengeView;
import com.moma.fans.gui.LoginView;
import com.moma.fans.gui.ProfileCreationView;
import com.moma.fans.gui.RegisterView;
import com.moma.fans.gui.ScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Añadimos todas las pantallas con sus nombres
        ScreenController.getInstance().addScreen("Inicio de sesión", new LoginView());
        ScreenController.getInstance().addScreen("Registro", new RegisterView());
        ScreenController.getInstance().addScreen("Creación de perfil", new ProfileCreationView());
        ScreenController.getInstance().addScreen("Creación de reto", new CreateChallengeView());


        ScreenController.getInstance().setStage(stage, 500, 400);
        ScreenController.getInstance().setScreen("Creación de reto");
        stage.setResizable(true);
        stage.show();

    }

    public static void main(String [] args) {

        launch();
    }
}
