package com.serwis.controller.accounts;

import com.serwis.entity.UserRole;
import com.serwis.entity.Users;
import com.serwis.repository.UsersRepository;
import com.serwis.services.UserRoleService;
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
import java.util.*;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class RegistrationController implements Initializable {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UsersService usersService;
	@Autowired
	private UserRoleService userRoleService;

	@FXML
	private ComboBox<String> chooseRole;
	@FXML
	private TextField textFieldPassword;
	@FXML
	private TextField textFieldUsername;
	private ObservableList<String> listRole = FXCollections.observableArrayList();
	private HashMap<String, Integer> map = new HashMap<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseRole.setItems(doListRole());
		chooseRole.getSelectionModel().select(0);
	}

	private ObservableList<String> doListRole() {
		List<UserRole> listUserRole = userRoleService.findAll();
		listRole.clear();
		doMapRole(listUserRole);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			listRole.add(entry.getKey());
		}
		return listRole;
	}

	private void doMapRole(List<UserRole> list) {
		map.clear();
		for (UserRole aList : list) {
			map.put(aList.getRole(), aList.getIdUserRole());
		}
	}

	@FXML
	public void addNewAccountAction(ActionEvent event) {
		if (textFieldUsername.getText().length() < 3) {
			incorrectUsername("Nazwa użytkownika musi mieć minimum 3 znaki");
		} else if (userExists(textFieldUsername.getText())) {
			incorrectUsername("Taki użytkownik już istnieje");
		} else if (textFieldPassword.getText().length() < 5) {
			incorrectPassword();
		} else {
			Users newUser = new Users();
			newUser.setUsername(textFieldUsername.getText());
			newUser.setPassword(textFieldPassword.getText());
			newUser.setId_role(map.get(chooseRole.getSelectionModel().getSelectedItem()));
			usersService.addUser(newUser);
			alertAccountWasCreated();
		}
	}



	private boolean userExists(String username) {
		Users user = usersService.findByUsername(username);
		if(user != null) {
			return true;
		}else return false;
	}

	private void incorrectUsername(String text) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Bład zakładania konta");
		alert.setHeaderText(text);
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

	private void alertAccountWasCreated() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Założono nowe konto");
		alert.setHeaderText("Pomyślnie założono nowe konto");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) textFieldPassword.getScene().getWindow();
			stage.close();
		}
	}

}
