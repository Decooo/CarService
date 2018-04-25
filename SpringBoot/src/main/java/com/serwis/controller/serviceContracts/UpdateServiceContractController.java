package com.serwis.controller.serviceContracts;

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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 25.04.2018.
 */
@Controller
public class UpdateServiceContractController implements Initializable {
	@Autowired
	private ServiceContractsController serviceContractsController;
	@Autowired
	private ClientsService clientsService;
	@Autowired
	private ServiceContractsService serviceContractsService;
	@FXML
	private Button backButton;
	@FXML
	private TextField textWorkingTime;
	@FXML
	private TextField textAmountForParts;
	@FXML
	private ComboBox clientComboBox;
	private ObservableList<String> clientList = FXCollections.observableArrayList();
	private int indexClient = 0;

	public void initialize(URL location, ResourceBundle resources) {
		ServiceContracts serviceContracts = ServiceContractsController.getServiceContracts();
		clientComboBox.setItems(doListClients());
		setCarProperties(serviceContracts);
	}

	private void setCarProperties(ServiceContracts serviceContracts) {
		clientComboBox.getSelectionModel().select(indexClient);
		textWorkingTime.setText(String.valueOf(serviceContracts.getWorkingTime()));
		textAmountForParts.setText(String.valueOf(serviceContracts.getAmountForParts()));
	}

	private ObservableList<String> doListClients() {
		List<Clients> clientsList = clientsService.findAll();
		Clients clients = clientsService.findByIdClients(ServiceContractsController.getServiceContracts().getIdClient());
		for (int i = 0; i < clientsList.size(); i++) {
			clientList.add(clientsList.get(i).getName() + " " + clientsList.get(i).getSurname());
			if (clientsList.get(i).getIdClients() == clients.getIdClients()) {
				indexClient = i;
			}
		}
		return clientList;
	}


	@FXML
	public void updateContractAction(ActionEvent event) {
		if (textWorkingTime.getText().length() == 0 || textAmountForParts.getText().length() == 0) {
			ValidationServiceContractAlert.notIntroducedData();
		} else if (!introduceBadChar(textWorkingTime.getText())) {
			ValidationServiceContractAlert.IntroducedBadChar();
		} else if (!introduceBadChar(textAmountForParts.getText())) {
			ValidationServiceContractAlert.IntroducedBadChar();
		} else {
			List<Clients> clientsList = clientsService.findAll();
			ServiceContracts serviceContracts = ServiceContractsController.getServiceContracts();
			serviceContracts.setAmountForParts(Double.parseDouble(textAmountForParts.getText()));
			serviceContracts.setWorkingTime(Double.parseDouble(textWorkingTime.getText()));
			int clientID = clientComboBox.getSelectionModel().getSelectedIndex();
			serviceContracts.setIdClient(clientsList.get(clientID).getIdClients());
			serviceContractsService.add(serviceContracts);
			alertUpdatedContract();
		}
	}

	private void alertUpdatedContract() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Aktualizacja");
		alert.setHeaderText("Pomyślnie zaaktualizowano umowę");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.close();
			serviceContractsController.loadContractsDetails();
		}
	}

	private boolean introduceBadChar(String text) {
		return text.matches("[0-9]+(\\.){0,1}[0-9]*");
	}

	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

}
