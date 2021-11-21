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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    
    ToggleGroup group;
    RadioButton rbFacebook;
    RadioButton rbGoogle;
    RadioButton rbNormalRegister;

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

        btnNormalRegister = new Button();

        eRegVbox.getChildren().addAll(
                createCenteredBoldLabel("Introducir datos de registro:"),
                vboxEmail,
                vboxNickname,
                vboxPass
        );

        Hyperlink hlLogin = new Hyperlink("Volver a inicio de sesión");
        hlLogin.setBorder(Border.EMPTY);
        hlLogin.setAlignment(Pos.CENTER_LEFT);

        hlLogin.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN));

        // Creacion radioButtons
        group = new ToggleGroup();
        HBox radioHB = new HBox();

        VBox radioVB = new VBox();

        radioVB.setSpacing(5);
        
        rbNormalRegister = new RadioButton("Local");
        rbNormalRegister.setToggleGroup(group);
        rbNormalRegister.setSelected(true);

        rbGoogle = new RadioButton("Google");
        rbGoogle.setToggleGroup(group);
        
        rbFacebook = new RadioButton("Facebook");
        rbFacebook.setToggleGroup(group);

        radioVB.getChildren().addAll(rbNormalRegister, rbGoogle, rbFacebook);

        radioHB.setAlignment(Pos.CENTER);
        radioHB.getChildren().add(radioVB);
        
        root.setPadding(new Insets(20, 80, 20, 80));
        root.setSpacing(20.0d);
        root.getChildren().addAll(
                eRegVbox,
                radioHB,
                createCenteredButton("Registrarse", btnNormalRegister),
                hlLogin
        );

        // Events
        btnNormalRegister.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                try {
                    String registerType = ((RadioButton) group.getSelectedToggle()).getText();
                    controller.register(tfEmail.getText(), tfNickname.getText(), passField.getText(), registerType);
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
        rbNormalRegister.setSelected(true);
    }

    @Override
    public Parent getView() {

        return view;
    }
}
