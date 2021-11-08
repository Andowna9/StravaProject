package com.moma.fans.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista para registrar una nueva cuenta.
 * @author JonanC
 */
public class RegisterView extends VBox {

    public RegisterView() {

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

        eRegVbox.getChildren().addAll(
                createCenteredBoldLabel("Registro simple"),
                vboxEmail,
                vboxPass,
                vboxConfirmPass,
                createCenteredButton("Registrarse")
        );

        this.setPadding(new Insets(20, 80, 20, 80));
        this.setSpacing(10.0d);
        this.getChildren().addAll(
                eRegVbox,
                createCenteredBoldLabel("O"),
                createCenteredButton("Registrarse con Google"),
                createCenteredBoldLabel("O"),
                createCenteredButton("Registrarse con Facebook")
        );


    }

    private HBox createCenteredBoldLabel(String text) {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label lblBold = new Label(text);
        lblBold.setStyle("-fx-font-weight: bold");
        hBox.getChildren().add(lblBold);

        return hBox;
    }

    private HBox createCenteredButton(String text) {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button btn = new Button(text);
        hBox.getChildren().add(btn);

        return hBox;
    }
}
