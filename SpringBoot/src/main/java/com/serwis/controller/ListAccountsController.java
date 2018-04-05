package com.serwis.controller;

import com.serwis.entity.Users;
import com.serwis.repository.UsersRepository;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class ListAccountsController implements Initializable {

	@Autowired
	UsersRepository usersRepository;

	@FXML
	private TableView<Users> tableAccounts;
//	@FXML
//	private TableColumn<Users, String> idColumn;
	@FXML
	private TableColumn<Users, String> usernameColumn;
	@FXML
	private TableColumn<Users, String> passwordColumn;
	@FXML
	private TableColumn<Users, String> roleColumn;

	private ObservableList<Users> data;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Users> users = usersRepository.findAll();
		data = FXCollections.observableArrayList(users);

		//ordinalNumber();

		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		tableAccounts.setItems(data);
	}

//	private void ordinalNumber() {
//		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableAccounts.getItems().indexOf(p.getValue()) + 1 + ""));
//		idColumn.setSortable(false);
//	}
}
