package com.serwis.controller.parts;

import com.serwis.entity.Parts;
import com.serwis.entity.TypeParts;
import com.serwis.services.PartsService;
import com.serwis.services.TypePartsService;
import com.serwis.util.alerts.ValidationPartsAlert;
import com.serwis.util.alerts.ValidationServiceContractAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

/**
 * Created by jakub on 18.05.2018.
 */
@Controller
public class AddPartsController implements Initializable {

	@Autowired
	private TypePartsService typePartsService;
	@Autowired
	private PartsService partsService;

	@FXML
	private ComboBox<String> typeComboBox;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField quantityTextField;
	@FXML
	private TextField priceTextField;
	@FXML
	private Button backButton;

	private ObservableList<String> listType = FXCollections.observableArrayList();
	private HashMap<String, Integer> map = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeComboBox.setItems(doListType());
		typeComboBox.getSelectionModel().select(0);
	}

	private ObservableList<String> doListType() {
		List<TypeParts> listTypeParts = typePartsService.findAll();
		listType.clear();
		doMapRole(listTypeParts);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			listType.add(entry.getKey());
		}
		return listType;
	}

	private void doMapRole(List<TypeParts> list) {
		map.clear();
		for (TypeParts aList : list) {
			map.put(aList.getType(), aList.getIdTypeParts());
		}
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void addPartsAction(ActionEvent event) {
		if (nameTextField.getText().length() == 0) {
			ValidationPartsAlert.notIntroducedName();
		} else if (quantityTextField.getText().length() == 0) {
			ValidationPartsAlert.notIntroducedQuantity();
		} else if (priceTextField.getText().length() == 0) {
			ValidationPartsAlert.notIntroducedPrice();
		} else if (!textIsInt(quantityTextField.getText())) {
			ValidationPartsAlert.notIntroducedOnlyNumbers();
		} else if (!textIsDouble(priceTextField.getText())) {
			ValidationServiceContractAlert.IntroducedBadChar();
		} else {
			Parts part = new Parts();
			part.setName(nameTextField.getText());
			part.setQuantity(Integer.parseInt(quantityTextField.getText()));
			part.setPrice(Double.parseDouble(priceTextField.getText()));
			part.setId_type_parts(map.get(typeComboBox.getSelectionModel().getSelectedItem()));
			System.out.println("ID: " +map.get(typeComboBox.getSelectionModel().getSelectedItem()));
			partsService.save(part);
			alertAddedNewPart();
		}
	}

	private void alertAddedNewPart() {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Dodano nową część");
			alert.setHeaderText("Pomyślnie dodano nową część");
			alert.getButtonTypes().setAll(ButtonType.OK);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Stage stage = (Stage) backButton.getScene().getWindow();
				stage.close();
			}
		}

	private boolean textIsInt(String text) {
		return text.matches("\\d+");
	}
	private boolean textIsDouble(String text) {
		return text.matches("[0-9]+(\\.){0,1}[0-9]*");
	}
}
