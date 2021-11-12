package com.moma.fans.gui.views;

import java.rmi.RemoteException;

import com.moma.fans.controllers.UserController;

import com.moma.fans.gui.IReset;
import com.moma.fans.gui.ScreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * Vista para hacer login en el servicio remoto.
 * @author JonanC
 */

public class LoginView extends BorderPane implements IReset {

    private UserController controller;

    private TextField tfEmail;
    private PasswordField passField;

    public  LoginView(UserController controller) {

        this.controller = controller;

        VBox mainVbox = new VBox();
        mainVbox.setSpacing(15.0d);
        mainVbox.setPadding(new Insets(80.0d));

        VBox vboxEmail = new VBox();
        vboxEmail.setSpacing(2.0d);
        Label lblEmail = new Label("Correo electrónico:");
        tfEmail = new TextField();
        vboxEmail.getChildren().addAll(lblEmail, tfEmail);

        VBox vboxPass = new VBox();
        vboxPass.setSpacing(2.0d);
        Label lblPassword = new Label("Contraseña:");
        passField = new PasswordField();
        vboxPass.getChildren().addAll(lblPassword, passField);

        HBox noteBox = new HBox();
        noteBox.setAlignment(Pos.CENTER);
        Label lblQuestion = new Label("No tienes una cuenta todavía?");
        Hyperlink hlRegister = new Hyperlink("Regístrate");
        hlRegister.setBorder(Border.EMPTY);
        noteBox.getChildren().addAll(lblQuestion, hlRegister);

        HBox errorBox = new HBox();
        errorBox.setAlignment(Pos.CENTER);
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Paint.valueOf("red"));
        errorBox.getChildren().add(errorLabel);

        HBox btnContainer = new HBox();
        btnContainer.setAlignment(Pos.CENTER);
        Button btnLogin = new Button("Iniciar Sesión");
        btnContainer.getChildren().add(btnLogin);

        mainVbox.getChildren().addAll(vboxEmail, vboxPass, errorBox, btnContainer, noteBox);

        this.setCenter(mainVbox);
        mainVbox.setAlignment(Pos.CENTER);

        // Events
        hlRegister.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.REGISTER));
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				
				// Controlar errores al hacer login
				try {
					controller.login(tfEmail.getText(), passField.getText());
					ScreenController.getInstance().setScreen(ScreenController.State.HOME);
                    resetLayout();
				} catch (RemoteException e) {
					alert.setHeaderText("Error al iniciar sesión");
					alert.setContentText(e.getCause().getMessage());
					alert.showAndWait();
				}
				
			}
		});

    }

    @Override
    public void resetLayout() {

        tfEmail.clear();
        passField.clear();
    }



}
