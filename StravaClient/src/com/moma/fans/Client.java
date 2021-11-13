package com.moma.fans;

import com.moma.fans.controllers.UserController;
import com.moma.fans.controllers.ChallengeController;
import com.moma.fans.controllers.TrainingSessionController;
import com.moma.fans.gui.views.*;
import com.moma.fans.remote.ServiceLocator;
import com.moma.fans.gui.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Client extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Obtenemos los argumentos de programa pasados a la aplicación
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();

        // Service Locator
        ServiceLocator serviceLocator = new ServiceLocator();
        serviceLocator.setService(args.get(0), args.get(1), args.get(2));

        // Controladores
        UserController userController = new UserController(serviceLocator);
        ChallengeController challengeController = new ChallengeController(serviceLocator);
        TrainingSessionController trainingSessionController = new TrainingSessionController(serviceLocator);


        // Añadimos todas las pantallas con sus nombres
        ScreenController.getInstance().addScreen(ScreenController.State.LOG_IN, new LoginView(userController));
        ScreenController.getInstance().addScreen(ScreenController.State.REGISTER, new RegisterView(userController));
        ScreenController.getInstance().addScreen(ScreenController.State.PROFILE_CREATION, new ProfileCreationView(userController));
        ScreenController.getInstance().addScreen(ScreenController.State.CHALLENGE_CREATION, new CreateChallengeView(userController, challengeController));
        ScreenController.getInstance().addScreen(ScreenController.State.TRAINING_SESSION_CREATION, new CreateTrainingSessionView(userController, trainingSessionController));
        ScreenController.getInstance().addScreen(ScreenController.State.HOME, new HomeView(userController));

        ScreenController.getInstance().setStage(stage, 600, 400);
        ScreenController.getInstance().setScreen(ScreenController.State.HOME);

        stage.setResizable(false);
        stage.show();

    }

    public static void main(String [] args) {

       launch(args);
    }
}
