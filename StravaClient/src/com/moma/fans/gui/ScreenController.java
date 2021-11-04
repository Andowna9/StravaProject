package com.moma.fans.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;

// La clase implementa el patrón de diseño Singleton
public class ScreenController {

    private Stage stage;
    private Scene main;
    private HashMap<String, Parent> screenMap = new HashMap<>();

    private final static ScreenController instance = new ScreenController();

    private ScreenController() {}

    public void setStage(Stage stage, double width, double height) {

        this.stage = stage;
        stage.setScene(new Scene(new Pane(), width, height));
    }

    public void addScreen(String name, Parent screen) {

        screenMap.put(name, screen);
    }

    public void setScreen(String name) {

        stage.setTitle(name);
        stage.getScene().setRoot(screenMap.get(name));

    }

    public static ScreenController getInstance() {

        return instance;
    }
}
