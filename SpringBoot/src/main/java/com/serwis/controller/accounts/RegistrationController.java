package com.serwis.controller.accounts;

import com.serwis.entity.Users;
import com.serwis.repository.UsersRepository;
import com.serwis.services.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class RegistrationController implements Initializable {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UsersService usersService;

	@FXML
	private ComboBox<String> chooseRole;
	@FXML
	private TextField textFieldPassword;
	@FXML
	private TextField textFieldUsername;

	private ObservableList<String> listRole = FXCollections.observableArrayList();
	private HashMap<String, String> map = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseRole.setItems(doListRole());
		chooseRole.getSelectionModel().select(0);
	}

	private ObservableList<String> doListRole() {
		listRole.clear();
		listRole.add("Serwisant");
		listRole.add("Magazynier");
		listRole.add("Administrator");
		doMapRole();
		return listRole;
	}

	private void doMapRole() {
		map.clear();
		map.put("Serwisant", "SERVICEMAN");
		map.put("Magazynier", "WAREHOUSEMAN");
		map.put("Administrator", "ADMINISTRATOR");
	}

	@FXML
	public void addNewAccountAction(ActionEvent event) {
		if (textFieldUsername.getText().length() < 3) {
			incorrectUsername();
		} else if (textFieldPassword.getText().length() < 5) {
			incorrectPassword();
		} else {
			Users newUser = new Users();
//			newUser.setUsername(textFieldUsername.getText());
//			newUser.setPassword(textFieldPassword.getText());
//			newUser.setId_role(map.get(chooseRole.getSelectionModel().getSelectedItem()));
//			usersService.addUser(newUser);
//			alertAccountWasCreated();
		}
	}

	private void alertAccountWasCreated() {
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Założono nowe konto");
	alert.setHeaderText("Pomyślnie założono nowe konto");
	alert.getButtonTypes().setAll(ButtonType.OK);
	Optional<ButtonType> result = alert.showAndWait();
	if(result.get()==ButtonType.OK){
		Stage stage = (Stage) textFieldPassword.getScene().getWindow();
		stage.close();
		}
	}

	private void incorrectUsername() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Bład zakładania konta");
		alert.setHeaderText("Nazwa użytkownika musi mieć minimum 3 znaki");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			textFieldUsername.clear();
			textFieldPassword.clear();
		}
	}

	private void incorrectPassword() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd zakładania konta");
		alert.setHeaderText("Hasło musi mieć minimum 5 znaków");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			textFieldPassword.clear();
		}
	}

}
