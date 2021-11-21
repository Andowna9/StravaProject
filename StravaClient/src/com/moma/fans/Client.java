package com.moma.fans;

import com.moma.fans.controllers.UserController;
import com.moma.fans.controllers.ChallengeController;
import com.moma.fans.controllers.TrainingSessionController;
import com.moma.fans.gui.screens.*;
import com.moma.fans.remote.ServiceLocator;
import com.moma.fans.gui.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class Client extends Application {

    private UserController userController;

    @Override
    public void start(Stage stage) throws Exception {

        // Obtenemos los argumentos de programa pasados a la aplicación
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();

        // Service Locator
        ServiceLocator serviceLocator = new ServiceLocator();
        serviceLocator.setService(args.get(0), args.get(1), args.get(2));

        // Controladores
        userController = new UserController(serviceLocator);
        ChallengeController challengeController = new ChallengeController(serviceLocator);
        TrainingSessionController trainingSessionController = new TrainingSessionController(serviceLocator);


        // Añadimos todas las pantallas con sus nombres
        ScreenController.getInstance().addScreen(ScreenController.State.LOG_IN, new LoginScreen(userController));
        ScreenController.getInstance().addScreen(ScreenController.State.REGISTER, new RegisterScreen(userController));
        ScreenController.getInstance().addScreen(ScreenController.State.PROFILE_CREATION, new ProfileCreationScreen(userController));
        ScreenController.getInstance().addScreen(ScreenController.State.CHALLENGE_CREATION, new CreateChallengeScreen(userController, challengeController));
        ScreenController.getInstance().addScreen(ScreenController.State.TRAINING_SESSION_CREATION, new CreateTrainingSessionScreen(userController, trainingSessionController));
        ScreenController.getInstance().addScreen(ScreenController.State.HOME, new HomeScreen(userController, trainingSessionController, challengeController));

        ScreenController.getInstance().setStage(stage, 600, 400);
        ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN);

        // Cargar icono
        stage.getIcons().add(new Image("file:res/icons/strava-icon.png"));
        
        stage.setResizable(false);
        stage.show();

    }

    @Override
    public void stop() throws Exception {

        // Solución temporal
        // TODO Mejorar este sistema con un timeout

        if (userController.getToken() != -1) {

            userController.logout();
        }


    }

    public static void main(String [] args) {

       launch(args);
    }
}
