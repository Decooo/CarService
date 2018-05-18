package com.serwis.util.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by jakub on 18.05.2018.
 */
public class ValidationPartsAlert {

	public static void notIntroducedName() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono nazwy");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}


	public static void notIntroducedOnlyNumbers() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Podano niepoprawną ilość");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void notIntroducedPrice() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono ceny");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	public static void notIntroducedQuantity() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Nie wprowadzono ilości");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}
}
