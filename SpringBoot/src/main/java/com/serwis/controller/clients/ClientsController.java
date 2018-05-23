package com.serwis.controller.clients;

import com.serwis.config.StageManager;
import com.serwis.entity.Clients;
import com.serwis.services.ClientsService;
import com.serwis.util.imageSettings.EditAndHistoryButton;
import com.serwis.view.FxmlView;
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
 * Created by jakub on 19.04.2018.
 */
@Controller
public class ClientsController implements Initializable {
	private static Clients client;
	List<Clients> clientsList = new ArrayList<>();
	ObservableList<Clients> observableList = FXCollections.observableArrayList(clientsList);
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
	private Callback<TableColumn<Clients, Boolean>, TableCell<Clients, Boolean>> cellEditFactory =
			new Callback<TableColumn<Clients, Boolean>, TableCell<Clients, Boolean>>() {
				@Override
				public TableCell<Clients, Boolean> call(final TableColumn<Clients, Boolean> param) {
					final TableCell<Clients, Boolean> cell = new TableCell<Clients, Boolean>() {
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
									Clients client = getTableView().getItems().get(getIndex());
									try {
										updateClient(client);
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

						private void updateClient(Clients client) throws IOException {
							setClient(client);
							stageManager.switchSceneAndWait(FxmlView.UPDATECLIENT);
						}
					};
					return cell;
				}
			};
	private Callback<TableColumn<Clients, Boolean>, TableCell<Clients, Boolean>> cellHistoryFactory =
			new Callback<TableColumn<Clients, Boolean>, TableCell<Clients, Boolean>>() {
				@Override
				public TableCell<Clients, Boolean> call(final TableColumn<Clients, Boolean> param) {
					final TableCell<Clients, Boolean> cell = new TableCell<Clients, Boolean>() {
						final Button btnHistory = new Button();
						Image imgEdit = new Image(getClass().getResourceAsStream("/images/history.png"));

						@Override
						public void updateItem(Boolean check, boolean empty) {
							super.updateItem(check, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								btnHistory.setOnAction(e -> {
									Clients client = getTableView().getItems().get(getIndex());
									try {
										historyClient(client);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								});

								btnHistory.setStyle("-fx-background-color: transparent;");
								ImageView iv = EditAndHistoryButton.getImageView(imgEdit);
								btnHistory.setGraphic(iv);

								setGraphic(btnHistory);
								setAlignment(Pos.CENTER);
								setText(null);
							}
						}

						private void historyClient(Clients client) throws IOException {
							setClient(client);
							stageManager.switchSceneAndWait(FxmlView.CLIENTHISTORY);
						}
					};
					return cell;
				}
			};
	public static Clients getClient() {
		return client;
	}

	public static void setClient(Clients client) {
		ClientsController.client = client;
	}

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
		editColumn.setCellFactory(cellEditFactory);
		historyColumn.setCellFactory(cellHistoryFactory);
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

	@FXML
	public void deleteCars(ActionEvent event) {
		List<Clients> clients = clientsTable.getSelectionModel().getSelectedItems();
		alertDeleteClients(clients);
		loadClientsDetails();
	}

	private void alertDeleteClients(List<Clients> clients) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuwania");
		alert.setHeaderText(null);
		alert.setContentText("Czy napewno chcesz usunąć wybranych klientów?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) clientsService.deleteInBatch(clients);
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
