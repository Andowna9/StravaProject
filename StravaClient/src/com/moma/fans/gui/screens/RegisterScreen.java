package com.moma.fans.gui.screens;

import java.rmi.RemoteException;

import com.moma.fans.controllers.UserController;

import com.moma.fans.gui.Screen;
import com.moma.fans.gui.ScreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * Vista para registrar una nueva cuenta.
 * @author JonanC
 */
public class RegisterScreen implements Screen {

    private UserController controller;

    TextField tfEmail;
    TextField tfNickname;
    PasswordField passField;

    Parent view;

    public RegisterScreen(UserController controller) {

        this.controller = controller;

        view = createView();
        
    }

    private Parent createView() {

        VBox root = new VBox();

        VBox eRegVbox = new VBox();
        eRegVbox.setSpacing(15.0d);

        VBox vboxEmail = new VBox();
        vboxEmail.setSpacing(2.0d);
        Label lblEmail = new Label("Correo electrónico:");
        tfEmail = new TextField();
        vboxEmail.getChildren().addAll(lblEmail, tfEmail);

        VBox vboxNickname = new VBox();
        vboxNickname.setSpacing(2.0d);
        Label lblNickname = new Label("Nombre de usuario:");
        tfNickname = new TextField();
        vboxNickname.getChildren().addAll(lblNickname, tfNickname);

        VBox vboxPass = new VBox();
        vboxPass.setSpacing(2.0d);
        Label lblPassword = new Label("Contraseña:");
        passField = new PasswordField();
        vboxPass.getChildren().addAll(lblPassword, passField);

        // Creación botones
        Button btnNormalRegister;
        Button btnGoogleRegister;
        Button btnFacebookRegister;

        btnNormalRegister = new Button();
        btnGoogleRegister = new Button();
        btnFacebookRegister = new Button();

        eRegVbox.getChildren().addAll(
                createCenteredBoldLabel("Registro habitual"),
                vboxEmail,
                vboxNickname,
                vboxPass,
                createCenteredButton("Registrarse", btnNormalRegister)
        );

        Hyperlink hlLogin = new Hyperlink("Volver a inicio de sesión");
        hlLogin.setBorder(Border.EMPTY);
        hlLogin.setAlignment(Pos.CENTER_LEFT);

        hlLogin.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN));

        root.setPadding(new Insets(20, 80, 20, 80));
        root.setSpacing(5.0d);
        root.getChildren().addAll(
                eRegVbox,
                createCenteredBoldLabel("O"),
                createCenteredButton("Registrarse con Google", btnGoogleRegister),
                createCenteredBoldLabel("O"),
                createCenteredButton("Registrarse con Facebook", btnFacebookRegister),
                hlLogin
        );

        // Events
        btnNormalRegister.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                try {
                    controller.register(tfEmail.getText(), tfNickname.getText(), passField.getText());
                    ScreenController.getInstance().setScreen(ScreenController.State.PROFILE_CREATION);
                } catch (RemoteException e) {

                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error en el registro");
                    alert.setContentText(e.getCause().getMessage());
                    alert.showAndWait();
                }

            }
        });

        return root;

    }

    private HBox createCenteredBoldLabel(String text) {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label lblBold = new Label(text);
        lblBold.setStyle("-fx-font-weight: bold");
        hBox.getChildren().add(lblBold);

        return hBox;
    }

    private HBox createCenteredButton(String text, Button btn) {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        btn.setText(text);
        hBox.getChildren().add(btn);
        

        return hBox;
    }


    @Override
    public void initialize() {

        tfEmail.clear();
        passField.clear();
        tfNickname.clear();
    }

    @Override
    public Parent getView() {

        return view;
    }
}
