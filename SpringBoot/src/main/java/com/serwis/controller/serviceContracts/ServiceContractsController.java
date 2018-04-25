package com.serwis.controller.serviceContracts;

import com.serwis.config.StageManager;
import com.serwis.entity.Clients;
import com.serwis.entity.ServiceContracts;
import com.serwis.services.ClientsService;
import com.serwis.services.ServiceContractsService;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.ServiceContractsWrapper;
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
 * Created by jakub on 23.04.2018.
 */
@Controller
public class ServiceContractsController implements Initializable {

	@Autowired
	private ClientsService clientsService;
	@Autowired
	private ServiceContractsService serviceContractsService;

	@FXML
	public TableColumn<ServiceContractsWrapper, Integer> idColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Double> nameColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Double> surnameColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Double> workingTimeColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Double> amountForPartsColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Double> remainingWorkingTimeColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Double> remainingAmountForPartsColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Boolean> editColumn;
	@FXML
	public TableColumn<ServiceContractsWrapper, Boolean> historyColumn;
	@FXML
	private TextField searchTextField;
	@FXML
	private TableView<ServiceContractsWrapper> contractsTable;
	@FXML
	private Button backButton;
	@Lazy
	@Autowired
	private StageManager stageManager;

	private List<Clients> clientsList = new ArrayList<>();
	private List<ServiceContracts> contractsList = new ArrayList<>();
	private ObservableList<ServiceContractsWrapper> serviceContractsList = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadContractsDetails();
		filtrationTable();
	}

	private void filtrationTable() {
		ObservableList data = contractsTable.getItems();
		searchTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (oldValue != null && (newValue.length() < oldValue.length())) {
				contractsTable.setItems(data);
			}
			String value = newValue.toLowerCase();
			ObservableList<ServiceContractsWrapper> subentries = FXCollections.observableArrayList();

			long count = contractsTable.getColumns().stream().count();
			for (int i = 0; i < contractsTable.getItems().size(); i++) {
				for (int j = 0; j < count; j++) {
					String entry = "" + contractsTable.getColumns().get(j).getCellData(i);
					if (entry.toLowerCase().contains(value)) {
						subentries.add(contractsTable.getItems().get(i));
						break;
					}
				}
			}
			contractsTable.setItems(subentries);
		});
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(contractsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);

	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		workingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("workingTime"));
		amountForPartsColumn.setCellValueFactory(new PropertyValueFactory<>("amountForParts"));
		remainingWorkingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("remainingWorkingTime"));
		remainingAmountForPartsColumn.setCellValueFactory(new PropertyValueFactory<>("remainingAmountForParts"));
		//editColumn.setCellFactory(cellEditFactory);
		//historyColumn.setCellFactory(cellHistoryFactory);
	}

	public void loadContractsDetails() {
		clientsList.clear();
		contractsList.clear();
		contractsList.addAll(serviceContractsService.findAll());
		for (ServiceContracts list : contractsList) {
			Clients clients = clientsService.findByIdClients(list.getIdClient());
			clientsList.add(clients);
		}
		ServiceContractsWrapper wrapper = new ServiceContractsWrapper();
		ObservableList<ServiceContractsWrapper> contractsWrappers = wrapper.serviceContractsWrappers(clientsList, contractsList);
		contractsTable.setItems(contractsWrappers);
		contractsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		serviceContractsList = contractsWrappers;
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void newContractAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDSERVICECONTRACT);
	}

	@FXML
	public void deleteContract(ActionEvent event) {
	}


}
