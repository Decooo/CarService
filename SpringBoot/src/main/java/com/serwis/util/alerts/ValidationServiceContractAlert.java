package com.serwis.util.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by jakub on 24.04.2018.
 */
public class ValidationServiceContractAlert {

	public static void notIntroducedData() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono czasu lub kwoty");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void IntroducedBadChar() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Można wprowadzać tylko cyfry oddzielone kropka");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}
}
