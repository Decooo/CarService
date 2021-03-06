package com.serwis.controller.serviceContracts;

import com.serwis.config.StageManager;
import com.serwis.entity.Clients;
import com.serwis.entity.ServiceContracts;
import com.serwis.services.ClientsService;
import com.serwis.services.ServiceContractsService;
import com.serwis.util.imageSettings.EditAndHistoryButton;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.ServiceContractsWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.04.2018.
 */
@Controller
public class ServiceContractsController implements Initializable {
	private static ServiceContracts serviceContracts;

	public static ServiceContracts getServiceContracts() {
		return serviceContracts;
	}

	public static void setServiceContracts(ServiceContracts serviceContracts) {
		ServiceContractsController.serviceContracts = serviceContracts;
	}

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
	@Autowired
	private ClientsService clientsService;
	@Autowired
	private ServiceContractsService serviceContractsService;
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
	private Callback<TableColumn<ServiceContractsWrapper, Boolean>, TableCell<ServiceContractsWrapper, Boolean>> cellEditFactory =
			new Callback<TableColumn<ServiceContractsWrapper, Boolean>, TableCell<ServiceContractsWrapper, Boolean>>() {
				@Override
				public TableCell<ServiceContractsWrapper, Boolean> call(final TableColumn<ServiceContractsWrapper, Boolean> param) {
					final TableCell<ServiceContractsWrapper, Boolean> cell = new TableCell<ServiceContractsWrapper, Boolean>() {
						final Button btnEdit = new Button();
						Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));

						@Override
						public void updateItem(Boolean check, boolean empty) {
							super.updateItem(check, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								btnEdit.setOnAction(e -> {
									ServiceContractsWrapper serviceContractsWrapper = getTableView().getItems().get(getIndex());
									ServiceContracts serviceContracts = serviceContractsService.findByIdServiceContracts(serviceContractsWrapper.getIdServiceContracts());
									try {
										updateContract(serviceContracts);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								});

								btnEdit.setStyle("-fx-background-color: transparent;");
								ImageView iv = EditAndHistoryButton.getImageView(imgEdit);
								btnEdit.setGraphic(iv);

								setGraphic(btnEdit);
								setAlignment(Pos.CENTER);
								setText(null);
							}
						}

						private void updateContract(ServiceContracts serviceContracts) throws IOException {
							setServiceContracts(serviceContracts);
							stageManager.switchSceneAndWait(FxmlView.UPDATESERVICECONTRACT);
						}
					};
					return cell;
				}
			};

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
		editColumn.setCellFactory(cellEditFactory);
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
		List<ServiceContractsWrapper> contracts = contractsTable.getSelectionModel().getSelectedItems();
		List<ServiceContracts> deleteContracts = new ArrayList<>();
		for (ServiceContractsWrapper sc : contracts) {
			deleteContracts.add(serviceContractsService.findByIdServiceContracts(sc.getIdServiceContracts()));
		}
		alertDeleteContracts(deleteContracts);
		loadContractsDetails();
	}

	private void alertDeleteContracts(List<ServiceContracts> contracts) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuwania");
		alert.setHeaderText(null);
		alert.setContentText("Czy napewno chcesz usunąć wybrane umowy?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) serviceContractsService.deleteInBatch(contracts);
	}
}
