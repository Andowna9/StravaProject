package com.moma.fans.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Clase que controla la transición entre distintas pantallas, cada
 * una de las cuales corresponde a una vista de la aplicación.
 * Implementa el patrón de diseño Singleton.
 * @author JonanC
 */
public class ScreenController {

    private Stage stage;
    private HashMap<String, Parent> screenMap = new HashMap<>();

    private final static ScreenController instance = new ScreenController();

    private ScreenController() {}

    /**
     * Establece lo relativo a la ventana gráfica creada por el S.O. y su tamaño.
     * @param stage contenedor principal que ejerce de ventana
     * @param width anchura de la ventana
     * @param height altura de la ventana
     */
    public void setStage(Stage stage, double width, double height) {

        this.stage = stage;
        stage.setScene(new Scene(new Pane(), width, height));
    }

    /**
     * Añade y registra una pantalla, asociándola a un nombre.
     * @param name nombre de la pantalla
     * @param screen contenedor padre
     */
    public void addScreen(String name, Parent screen) {

        screenMap.put(name, screen);
    }

    /**
     * Establece la pantalla indicada como la actual.
     * @param name nombre de la pantalla
     */
    public void setScreen(String name) {

        stage.setTitle(name);
        stage.getScene().setRoot(screenMap.get(name));

    }

    /**
     * @return única instancia de la clase
     */
    public static ScreenController getInstance() {

        return instance;
    }
}
