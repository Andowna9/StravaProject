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

    public enum State {

        // Valores posibles

        LOG_IN("Inicio de sesión"), REGISTER("Registro"),
        PROFILE_CREATION("Creación de perfil"), HOME("Home"),
        TRAINING_SESSION_CREATION("Creación de sesión de entrenamiento"),
        CHALLENGE_CREATION("Creación de reto");

        // Variable final para que no se pueda modificar
        private final String name;

        State(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    private Stage stage;
    private HashMap<State, Parent> screenMap = new HashMap<>();

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
     * @param state estado en la navegación de pantallas
     * @param screen contenedor padre
     */
    public void addScreen(State state, Parent screen) {

        screenMap.put(state, screen);
    }

    /**
     * Establece la pantalla indicada como la actual.
     * @param state estado de navegación
     */
    public void setScreen(State state) {

        stage.setTitle(state.toString());
        stage.getScene().setRoot(screenMap.get(state));

    }

    /**
     * Reinicia el estado proporcionado.
     * @param state estado que representa una pantalla
     */
    public void resetLayout(State state) {

        Parent screen = screenMap.get(state);

        if (screen instanceof IReset) {
            ((IReset) screen).resetLayout();
        }
    }

    /**
     * @return única instancia de la clase
     */
    public static ScreenController getInstance() {

        return instance;
    }
}
