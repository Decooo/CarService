package com.serwis.controller.repairs;

import com.serwis.controller.ServicemanController;
import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import com.serwis.services.IssuedPartsService;
import com.serwis.services.PartsService;
import com.serwis.util.status.IssuedPartsStatus;
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
 * Created by jakub on 31.05.2018.
 */
@Controller
public class OrderPartsController implements Initializable {
	@Autowired
	private PartsService partsService;
	@Autowired
	private IssuedPartsService issuedPartsService;
	@Autowired
	private DetailsRepairController detailsRepairController;
	@FXML
	private ComboBox partCombo;
	@FXML
	private TextField quantityText;
	@FXML
	private Button backBtn;

	private ObservableList<String> listParts = FXCollections.observableArrayList();
	private HashMap<String, Integer> mapPart = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		partCombo.setItems(doListPart());
		partCombo.getSelectionModel().select(0);
	}

	private ObservableList<String> doListPart() {
		List<Parts> allParts = partsService.findAll();
		listParts.clear();
		doMapParts(allParts);
		for (Map.Entry<String, Integer> entry : mapPart.entrySet()) {
			listParts.add(entry.getKey());
		}
		return listParts;
	}

	private void doMapParts(List<Parts> list) {
		mapPart.clear();
		for (Parts aList : list) {
			mapPart.put(aList.getName(), aList.getIdparts());
		}
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void orderAction(ActionEvent event) {
		if(!textIsInt(quantityText.getText()) || quantityText.getText().length() == 0){
			errorIntroducedQuantity();
		}else{
			IssuedParts issuedParts = new IssuedParts();
			issuedParts.setIdRepairs(ServicemanController.getRepairs().getIdRepairs());
			issuedParts.setIdParts(mapPart.get(partCombo.getSelectionModel().getSelectedItem()));
			issuedParts.setStatus(IssuedPartsStatus.NOWE.getStatus());
			issuedParts.setQuantity(Integer.parseInt(quantityText.getText()));
			issuedPartsService.save(issuedParts);
			alertAddNewIssuedPart();
		}
	}

	private void alertAddNewIssuedPart() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Zamowiono nowa czesc");
		alert.setHeaderText("Pomyślnie Zamowiono nowa czesc");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.close();
			detailsRepairController.loadIssuedParts();
		}

	}

	private void errorIntroducedQuantity() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Błędnie wprowadzono ilość sztuk");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	private boolean textIsInt(String text) {
		return text.matches("\\d+");
	}

}
