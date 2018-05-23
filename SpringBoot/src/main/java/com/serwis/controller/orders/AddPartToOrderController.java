package com.serwis.controller.orders;

import com.serwis.controller.parts.ListPartsController;
import com.serwis.entity.PartsOrders;
import com.serwis.services.PartsOrdersService;
import com.serwis.util.alerts.ValidationPartsAlert;
import com.serwis.wrappers.PartsWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.05.2018.
 */
@Controller
public class AddPartToOrderController implements Initializable {

	@Autowired
	private PartsOrdersService partsOrdersService;
	@FXML
	private Label namePartLabel;
	@FXML
	private TextField quantityField;
	@FXML
	private Button backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PartsWrapper parts = ListPartsController.getPartsWrapper();
		setPartProperties(parts);
	}

	private void setPartProperties(PartsWrapper parts) {
		namePartLabel.setText(parts.getName());
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

	public void addToOrderAction(ActionEvent event) {
		if (quantityField.getText().length() == 0) {
			ValidationPartsAlert.notIntroducedQuantity();
		} else if (!textIsInt(quantityField.getText())) {
			ValidationPartsAlert.notIntroducedOnlyNumbers();
		} else {
			PartsOrders part = new PartsOrders();
			List<PartsOrders> alreadyOrderedList = partsOrdersService.findAllById_OrdersIsNullIsZero();
			Boolean alreadyOrdered = false;
			int alreadyOrderedQuantity = 0;
			for (PartsOrders p : alreadyOrderedList) {
				if (p.getId_parts() == ListPartsController.getPartsWrapper().getIdParts()) {
					alreadyOrdered = true;
					part.setIdPartsOrders(p.getIdPartsOrders());
					alreadyOrderedQuantity = p.getQuantity();
					break;
				}
			}
			if (alreadyOrdered) {
				part.setQuantity(alreadyOrderedQuantity + Integer.parseInt(quantityField.getText()));
			} else {
				part.setQuantity(Integer.parseInt(quantityField.getText()));
			}
			part.setId_parts(ListPartsController.getPartsWrapper().getIdParts());
			partsOrdersService.addpart(part);
			alertAddedPartToOrder();
		}
	}

	private boolean textIsInt(String text) {
		return text.matches("\\d+");
	}


	private void alertAddedPartToOrder() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Dodano część do zamowienia");
		alert.setHeaderText("Pomyślnie dodano część do zamówienia");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.close();
		}
	}
}
