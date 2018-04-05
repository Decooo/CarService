package com.serwis.controller;

import com.serwis.entity.Users;
import com.serwis.services.UsersService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
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
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableAccounts.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}
}
