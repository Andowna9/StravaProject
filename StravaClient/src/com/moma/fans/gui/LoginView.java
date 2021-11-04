package com.moma.fans.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class LoginView extends BorderPane {

    public  LoginView() {

        VBox mainVbox = new VBox();
        mainVbox.setSpacing(15.0d);
        mainVbox.setPadding(new Insets(80.0d));

        VBox vboxEmail = new VBox();
        vboxEmail.setSpacing(2.0d);
        Label lblEmail = new Label("Correo electrónico:");
        TextField tfEmail = new TextField();
        vboxEmail.getChildren().addAll(lblEmail, tfEmail);

        VBox vboxPass = new VBox();
        vboxPass.setSpacing(2.0d);
        Label lblPassword = new Label("Contraseña:");
        PasswordField passField = new PasswordField();
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
        hlRegister.setOnAction(event -> ScreenController.getInstance().setScreen("Registro"));

    }



}
