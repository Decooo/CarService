package com.serwis.controller.orders;

import com.serwis.config.StageManager;
import com.serwis.entity.PartsOrders;
import com.serwis.services.PartsOrdersService;
import com.serwis.util.alerts.ValidationPartsAlert;
import com.serwis.wrappers.CurrentOrderWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.05.2018.
 */
@Controller
public class UpdateQuantityPartToOrderController implements Initializable {
	@Autowired
	private CurrentOrderController currentOrderController;
	@Autowired
	private PartsOrdersService partsOrdersService;
	@FXML
	private Label namePartLabel;
	@FXML
	private TextField quantityField;
	@FXML
	private Button backBtn;
	@Lazy
	@Autowired
	private StageManager stageManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CurrentOrderWrapper parts = CurrentOrderController.getCurrentOrderWrapper();
		setPartProperties(parts);
	}

	private void setPartProperties(CurrentOrderWrapper parts) {
		namePartLabel.setText(parts.getName());
		quantityField.setText(String.valueOf(parts.getQuantity()));
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void updateQuantityToPartAction(ActionEvent event) {
		if (quantityField.getText().length() == 0) {
			ValidationPartsAlert.notIntroducedQuantity();
		} else if (!textIsInt(quantityField.getText())) {
			ValidationPartsAlert.notIntroducedOnlyNumbers();
		} else {
			PartsOrders part = new PartsOrders();
			part.setId_parts_orders(CurrentOrderController.getCurrentOrderWrapper().getId_parts_orders());
			part.setId_parts(CurrentOrderController.getCurrentOrderWrapper().getId_parts());
			part.setQuantity(Integer.parseInt(quantityField.getText()));
			partsOrdersService.addpart(part);
			alertUpdatedQuantity();
		}
	}

	private boolean textIsInt(String text) {
		return text.matches("\\d+");
	}


	private void alertUpdatedQuantity() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Zaaktualizowano ilosc");
		alert.setHeaderText("Pomyślnie Zaaktualizowano ilosc");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.close();
			currentOrderController.loadOrdersDetails();
		}
	}
}
