package com.serwis.controller.accounts;

import com.serwis.entity.UserRole;
import com.serwis.entity.Users;
import com.serwis.services.UserRoleService;
import com.serwis.services.UsersService;
import com.serwis.wrappers.UsersWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class ListAccountsController implements Initializable {

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private
	UsersService usersService;
	@FXML
	private TableView<UsersWrapper> tableAccounts;
	@FXML
	private TableColumn<UsersWrapper, String> idColumn;
	@FXML
	private TableColumn<UsersWrapper, String> usernameColumn;
	@FXML
	private TableColumn<UsersWrapper, String> passwordColumn;
	@FXML
	private TableColumn<UsersWrapper, String> roleColumn;

	private List<Users> userList = new ArrayList<>();
	private List<UserRole> userRoleList = new ArrayList<>();

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
		for (Users list : userList) {
			UserRole role = userRoleService.getRole(list.getId_role());
			userRoleList.add(role);
		}
		UsersWrapper wrapper = new UsersWrapper();
		ObservableList<UsersWrapper> usersWrapper = wrapper.UsersWrapper(userList, userRoleList);
		tableAccounts.setItems(usersWrapper);
		tableAccounts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableAccounts.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	@FXML
	public void deleteUsers(ActionEvent event) {
		List<UsersWrapper> users = tableAccounts.getSelectionModel().getSelectedItems();
		List<Users> deleteUsers = new ArrayList<>();
		for (UsersWrapper us : users) {
			deleteUsers.add(usersService.findByIdUsers(us.getIdUser()));
		}
		alertDeleteUsers(deleteUsers);
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
