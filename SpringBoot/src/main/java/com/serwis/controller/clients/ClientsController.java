package com.serwis.controller.clients;

import com.serwis.config.StageManager;
import com.serwis.entity.Clients;
import com.serwis.services.ClientsService;
import com.serwis.view.FxmlView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 19.04.2018.
 */
@Controller
public class ClientsController implements Initializable {
	List<Clients> clientsList = new ArrayList<>();
	@Autowired
	private ClientsService clientsService;
	@Autowired
	@Lazy
	private StageManager stageManager;
	@FXML
	private TextField searchTextField;
	@FXML
	private Button backButton;
	@FXML
	private TableView<Clients> clientsTable;
	@FXML
	private TableColumn<Clients, Long> peselColumn;
	@FXML
	private TableColumn<Clients, String> surnameColumn;
	@FXML
	private TableColumn<Clients, String> nameColumn;
	@FXML
	private TableColumn<Clients, Integer> idColumn;
	@FXML
	private TableColumn<Clients, Boolean> historyColumn;
	@FXML
	private TableColumn<Clients, Boolean> editColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadClientsDetails();
		filtrationTable();
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(clientsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);

	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
		//editColumn.setCellFactory(cellEditFactory);
		//historyColumn.setCellFactory(cellHistoryFactory);
	}

	public void loadClientsDetails() {
		clientsList.clear();
		clientsList.addAll(clientsService.findAll());
		ObservableList<Clients> observableList = FXCollections.observableArrayList(clientsList);
		clientsTable.setItems(observableList);
		clientsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void filtrationTable() {
		ObservableList data = clientsTable.getItems();
		searchTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (oldValue != null && (newValue.length() < oldValue.length())) {
				clientsTable.setItems(data);
			}
			String value = newValue.toLowerCase();
			ObservableList<Clients> subentries = FXCollections.observableArrayList();

			long count = clientsTable.getColumns().stream().count();
			for (int i = 0; i < clientsTable.getItems().size(); i++) {
				for (int j = 0; j < count; j++) {
					String entry = "" + clientsTable.getColumns().get(j).getCellData(i);
					if (entry.toLowerCase().contains(value)) {
						subentries.add(clientsTable.getItems().get(i));
						break;
					}
				}
			}
			clientsTable.setItems(subentries);
		});
	}

	public void deleteCars(ActionEvent event) {
	}

	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void newClientAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDCLIENT);
	}

}
