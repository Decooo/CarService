package com.serwis.controller;

import com.serwis.entity.Users;
import com.serwis.repository.UsersRepository;
import com.serwis.services.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.HashMap;
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
		listRole.add("Serwisant");
		listRole.add("Magazynier");
		listRole.add("Administrator");
		doMapRole();
		return listRole;
	}

	private void doMapRole() {
		map.put("Serwisant", "SERVICEMAN");
		map.put("Magazynier", "WAREHOUSEMAN");
		map.put("Administrator", "ADMINISTRATOR");
	}

	@FXML
	public void addNewAccountAction(ActionEvent event) {
		Users newUser = new Users();
		newUser.setUsername(textFieldUsername.getText());
		newUser.setPassword(textFieldPassword.getText());
		newUser.setRole(map.get(chooseRole.getSelectionModel().getSelectedItem()));
		usersService.addUser(newUser);
	}
}
