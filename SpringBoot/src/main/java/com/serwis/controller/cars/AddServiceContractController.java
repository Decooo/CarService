package com.serwis.controller.cars;

import com.serwis.controller.serviceContracts.ServiceContractsController;
import com.serwis.entity.Clients;
import com.serwis.entity.ServiceContracts;
import com.serwis.services.ClientsService;
import com.serwis.services.ServiceContractsService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 24.04.2018.
 */
@Controller
public class AddServiceContractController implements Initializable {

	@Autowired
	private ServiceContractsController serviceContractsController;
	@Autowired
	private ClientsService clientsService;
	@Autowired
	private ServiceContractsService serviceContractsService;

	@FXML
	private TextField textWorkingTime;
	@FXML
	private TextField textAmountForParts;
	@FXML
	private ComboBox clientComboBox;
	@FXML
	private Button backButton;

	private List<Clients> clientsList = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientComboBox.setItems(doListClient());
		clientComboBox.getSelectionModel().select(0);
	}

	private ObservableList<Clients> doListClient() {
		clientsList = clientsService.findAll();
		ObservableList<Clients> list = FXCollections.observableArrayList(clientsList);
		return list;
	}

	@FXML
	public void addContractAction(ActionEvent event) {
		if (textWorkingTime.getText().length() == 0 || textAmountForParts.getText().length() == 0) {
			ValidationServiceContractAlert.notIntroducedData();
		} else if (!introduceBadChar(textWorkingTime.getText())) {
			ValidationServiceContractAlert.IntroducedBadChar();
		} else if (!introduceBadChar(textAmountForParts.getText())) {
			ValidationServiceContractAlert.IntroducedBadChar();
		} else {
			ServiceContracts serviceContracts = new ServiceContracts();
			serviceContracts.setAmountForParts(Double.parseDouble(textAmountForParts.getText()));
			serviceContracts.setRemainingAmountForParts(Double.parseDouble(textAmountForParts.getText()));
			serviceContracts.setWorkingTime(Double.parseDouble(textWorkingTime.getText()));
			serviceContracts.setRemainingWorkingTime(Double.parseDouble(textWorkingTime.getText()));
			int clientID = clientComboBox.getSelectionModel().getSelectedIndex();
			serviceContracts.setIdClient(clientsList.get(clientID).getIdClients());
			serviceContractsService.add(serviceContracts);
			alertAddedNewContract();
		}
	}

	private void alertAddedNewContract() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Dodano nowa umowe");
		alert.setHeaderText("Pomyślnie dodano nową umowę");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.close();
			serviceContractsController.loadClientsDetails();
		}
	}

	private boolean introduceBadChar(String text) {
		return text.matches("[0-9]+(\\.){0,1}[0-9]*");
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
}
