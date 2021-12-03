package com.moma.fans.gui.components;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StravaAlert extends Alert{

	public StravaAlert(AlertType type) {
		super(type);
		
		Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
		
		if (type == AlertType.ERROR) {
			stage.getIcons().add(
					new Image("file:res/icons/strava-error.png"));
		}
	}

	
}
