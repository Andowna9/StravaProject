package com.moma.fans.views;

import java.rmi.RemoteException;

import com.moma.fans.controllers.UserController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista para registrar una nueva cuenta.
 * @author JonanC
 */
public class RegisterView extends VBox {

    private UserController controller;
    
    public RegisterView(UserController controller) {

        this.controller = controller;

        VBox eRegVbox = new VBox();
        eRegVbox.setSpacing(15.0d);

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

        VBox vboxConfirmPass = new VBox();
        vboxPass.setSpacing(2.0d);
        Label lblConfirmPassword = new Label("Confirmar contraseña:");
        PasswordField passFieldConfirm = new PasswordField();
        vboxConfirmPass.getChildren().addAll(lblConfirmPassword, passFieldConfirm);
        
        // Creación botones
        Button btnNormalRegister;
        Button btnGoogleRegister;
        Button btnFacebookRegister;
        
        btnNormalRegister = new Button();
        btnGoogleRegister = new Button();
        btnFacebookRegister = new Button();
        
        eRegVbox.getChildren().addAll(
                createCenteredBoldLabel("Registro simple"),
                vboxEmail,
                vboxPass,
                vboxConfirmPass,
                createCenteredButton("Registrarse", btnNormalRegister)
        );

        this.setPadding(new Insets(20, 80, 20, 80));
        this.setSpacing(10.0d);
        this.getChildren().addAll(
                eRegVbox,
                createCenteredBoldLabel("O"),
                createCenteredButton("Registrarse con Google", btnGoogleRegister),
                createCenteredBoldLabel("O"),
                createCenteredButton("Registrarse con Facebook", btnFacebookRegister)
        );

        // Events
        btnNormalRegister.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				
				try {
					controller.register(tfEmail.getText(), passField.getText());
					ScreenController.getInstance().setScreen(ScreenController.State.PROFILE_CREATION);
				} catch (RemoteException e) {
					alert.setHeaderText("Error en el registro");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
				
			}
		});
        
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
}
