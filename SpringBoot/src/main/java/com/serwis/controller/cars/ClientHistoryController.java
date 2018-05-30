package com.serwis.controller.cars;

import com.serwis.controller.clients.ClientsController;
import com.serwis.entity.Repairs;
import com.serwis.services.RepairsService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by jakub on 19.04.2018.
 */
@Controller
public class ClientHistoryController implements Initializable{
	@Autowired
	private RepairsService repairsService;

	@FXML
	private TableView<Repairs> carsTable;
	@FXML
	private TableColumn<Repairs, Date> dateColumn;
	@FXML
	private TableColumn<Repairs,String> typeColumn;
	@FXML
	private TableColumn<Repairs, Double> priceColumn;
	@FXML
	private TableColumn<Repairs, Integer> idColumn;

	private ObservableList<Repairs> historyList = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadCarsDetails();
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(carsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	private void setColumnProperties() {
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		dateColumn.setCellFactory(column -> {
			TableCell<Repairs, Date> cell = new TableCell<Repairs, Date>() {
				private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(format.format(item));
					}
				}
			};

			return cell;
		});

	}

	private void loadCarsDetails() {
		historyList.clear();
		historyList.addAll(repairsService.findByIdClient(ClientsController.getClient().getIdClients()));
		carsTable.setItems(historyList);
		carsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
