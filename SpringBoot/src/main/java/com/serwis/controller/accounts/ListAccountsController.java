package com.serwis.controller.accounts;

import com.serwis.entity.Users;
import com.serwis.services.UsersService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class ListAccountsController implements Initializable {

	@Autowired
	UsersService usersService;

	@FXML
	private TableView<Users> tableAccounts;
	@FXML
	private TableColumn<Users, String> idColumn;
	@FXML
	private TableColumn<Users, String> usernameColumn;
	@FXML
	private TableColumn<Users, String> passwordColumn;
	@FXML
	private TableColumn<Users, String> roleColumn;

	private ObservableList<Users> userList = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadUserDetails();
	}

	private void setColumnProperties() {
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
	}

	private void loadUserDetails() {
		userList.clear();
		userList.addAll(usersService.findAll());
		tableAccounts.setItems(userList);
		tableAccounts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableAccounts.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	@FXML
	public void deleteUsers(ActionEvent event) {
		List<Users> users = tableAccounts.getSelectionModel().getSelectedItems();
		alertDeleteUsers(users);
		loadUserDetails();
	}

	private void alertDeleteUsers(List<Users> users) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuwania");
		alert.setHeaderText(null);
		alert.setContentText("Czy napewno chcesz usunąć wybrane?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) usersService.deleteInBatch(users);
	}
}
