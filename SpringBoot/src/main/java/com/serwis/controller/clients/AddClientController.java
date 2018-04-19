package com.serwis.controller.clients;

import com.serwis.util.alerts.ValidationClientAlert;
import com.serwis.entity.Clients;
import com.serwis.services.ClientsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 19.04.2018.
 */
@Controller
public class AddClientController implements Initializable{
	@Autowired
	private ClientsService clientsService;
	@Autowired
	private ClientsController clientsController;
	@FXML
	private TextField textFieldName;
	@FXML
	private TextField textFieldSurname;
	@FXML
	private TextField textFieldPesel;
	@FXML
	private Button backButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void addClientAction(ActionEvent event) {
		if (textFieldName.getText().length() == 0) {
			ValidationClientAlert.notIntroducedName();
		} else if (textFieldSurname.getText().length() == 0) {
			ValidationClientAlert.notIntroducedSurname();
		} else if (textFieldPesel.getText().length() != 11) {
			ValidationClientAlert.badLenghtPesel();
		} else if (!introduceBadChar(textFieldPesel.getText())) {
			ValidationClientAlert.IntroducedBadChar();
		} else {
			Clients clients = new Clients();
			clients.setName(textFieldName.getText());
			clients.setSurname(textFieldSurname.getText());
			long pesel = Long.parseLong(textFieldPesel.getText());
			clients.setPesel(pesel);
			clientsService.addClient(clients);
			alertAddedNewClient();
		}
	}

	private boolean introduceBadChar(String text) {
		return text.matches("\\d+");
	}

	private void alertAddedNewClient() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Dodano nowego klienta");
		alert.setHeaderText("Pomyślnie dodano nowego klienta");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.close();
			clientsController.loadClientsDetails();
		}
	}
}
