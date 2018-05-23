package com.serwis.controller.orders;

import com.serwis.entity.Orders;
import com.serwis.services.OrdersService;
import com.serwis.util.date.CustomDate;
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
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.05.2018.
 */
@Controller
public class HistoryOrdersController implements Initializable {
	@Autowired
	private OrdersService ordersService;

	@FXML
	private TableView<Orders> historyOrdersTable;
	@FXML
	private TableColumn<Orders,Integer> idColumn;
	@FXML
	private TableColumn<Orders,CustomDate> dateColumn;
	@FXML
	private TableColumn<Orders,String> statusColumn;
	@FXML
	private TableColumn<Orders,Double> valueColumn;
	@FXML
	private TableColumn<Orders,Boolean> detailColumn;
	@FXML
	private TextField searchField;
	@FXML
	private Button backBtn;

	private ObservableList<Orders> ordersList = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadOrdersDetails();
		filtrationTable();
	}

	private void setColumnProperties() {
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		//detailColumn
	}

	public void loadOrdersDetails() {
		ordersList.clear();
		ordersList.addAll(ordersService.findAll());
		historyOrdersTable.setItems(ordersList);
		historyOrdersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}


	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(historyOrdersTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

	private void filtrationTable() {
		ObservableList data = historyOrdersTable.getItems();
		searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (oldValue != null && (newValue.length() < oldValue.length())) {
				historyOrdersTable.setItems(data);
			}
			String value = newValue.toLowerCase();
			ObservableList<Orders> subentries = FXCollections.observableArrayList();

			long count = historyOrdersTable.getColumns().stream().count();
			for (int i = 0; i < historyOrdersTable.getItems().size(); i++) {
				for (int j = 0; j < count; j++) {
					String entry = "" + historyOrdersTable.getColumns().get(j).getCellData(i);
					if (entry.toLowerCase().contains(value)) {
						subentries.add(historyOrdersTable.getItems().get(i));
						break;
					}
				}
			}
			historyOrdersTable.setItems(subentries);
		});
	}
}
