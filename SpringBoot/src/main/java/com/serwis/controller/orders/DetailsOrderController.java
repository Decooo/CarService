package com.serwis.controller.orders;

import com.serwis.entity.Parts;
import com.serwis.entity.PartsOrders;
import com.serwis.services.PartsOrdersService;
import com.serwis.services.PartsService;
import com.serwis.wrappers.CurrentOrderWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.05.2018.
 */
@Controller
public class DetailsOrderController implements Initializable{

	@FXML
	private TableView<CurrentOrderWrapper> detailsOrderTable;
	@FXML
	private TableColumn<CurrentOrderWrapper,Integer> idColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper,String> nameColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper,Integer> quantityColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper,Double> priceColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper,Double> valueColumn;

	private List<Parts> partsList = new ArrayList<>();
	private List<PartsOrders> partsOrdersList = new ArrayList<>();

	@Autowired
	private PartsOrdersService partsOrdersService;
	@Autowired
	private PartsService partsService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadOrdersDetails();
	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	private void loadOrdersDetails() {
		partsList.clear();
		partsOrdersList.clear();
		partsOrdersList=partsOrdersService.findAllById_Orders(HistoryOrdersController.getOrder().getIdOrders());

		for (PartsOrders list : partsOrdersList) {
			Parts part = partsService.findByIdParts(list.getId_parts());
			partsList.add(part);
		}
		CurrentOrderWrapper wrapper = new CurrentOrderWrapper();
		ObservableList<CurrentOrderWrapper> currentOrderWrappers = wrapper.currentOrderWrappers(partsList, partsOrdersList);
		detailsOrderTable.setItems(currentOrderWrappers);
		detailsOrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}


	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(detailsOrderTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

}
