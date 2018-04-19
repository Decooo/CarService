package com.serwis.util.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by jakub on 06.04.2018.
 */
public class ValidationCarAlert {

	public static void badLenghtVINnumber() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Numer VIN musi mieć 17 znaków");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void notIntroducedRegistrationNumber() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono numeru rejestracyjnego pojazdu");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void notIntroducedModel() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono modelu pojazdu");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void notIntroducedBrand() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono marki pojazdu");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}
}
