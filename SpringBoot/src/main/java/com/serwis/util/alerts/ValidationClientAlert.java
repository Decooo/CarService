package com.serwis.util.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by jakub on 19.04.2018.
 */
public class ValidationClientAlert {
	public static void notIntroducedName() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono imienia");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void notIntroducedSurname() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono nazwiska");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void badLenghtPesel() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Numer PESEL musi mieć 11 znaków");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void IntroducedBadChar() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Numer PESEL musi zawierać tylko liczby");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}
}
