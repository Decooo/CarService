package com.serwis.controller.orders;

import com.serwis.config.StageManager;
import com.serwis.entity.Orders;
import com.serwis.entity.Parts;
import com.serwis.entity.PartsOrders;
import com.serwis.services.OrdersService;
import com.serwis.services.PartsOrdersService;
import com.serwis.services.PartsService;
import com.serwis.util.imageSettings.EditAndHistoryButton;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.CurrentOrderWrapper;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jakub on 23.05.2018.
 */
@Controller
public class CurrentOrderController implements Initializable {

	private static CurrentOrderWrapper currentOrderWrapper;
	@Autowired
	private OrdersService ordersService;
	private double totalValue = 0.0;
	@FXML
	private Label valueLabel;
	@FXML
	private TableView<CurrentOrderWrapper> currentOrderTable;
	@FXML
	private TableColumn<CurrentOrderWrapper, Integer> idColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper, String> nameColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper, Integer> quantityColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper, Double> priceColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper, Double> valueColumn;
	@FXML
	private TableColumn<CurrentOrderWrapper, Boolean> changeQuantityColumn;
	private List<Parts> partsList = new ArrayList<>();
	private List<PartsOrders> partsOrdersList = new ArrayList<>();
	@Autowired
	private PartsOrdersService partsOrdersService;
	@Lazy
	@Autowired
	private StageManager stageManager;
	private Callback<TableColumn<CurrentOrderWrapper, Boolean>, TableCell<CurrentOrderWrapper, Boolean>> cellEditFactory =
			new Callback<TableColumn<CurrentOrderWrapper, Boolean>, TableCell<CurrentOrderWrapper, Boolean>>() {
				@Override
				public TableCell<CurrentOrderWrapper, Boolean> call(final TableColumn<CurrentOrderWrapper, Boolean> param) {
					final TableCell<CurrentOrderWrapper, Boolean> cell = new TableCell<CurrentOrderWrapper, Boolean>() {
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
									CurrentOrderWrapper parts = getTableView().getItems().get(getIndex());
									try {
										editQuantity(parts);
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

						private void editQuantity(CurrentOrderWrapper currentOrderWrapper) throws IOException {
							setCurrentOrderWrapper(currentOrderWrapper);
							stageManager.switchSceneAndWait(FxmlView.UPDATEQUANTITYPARTTOORDER);
						}
					};
					return cell;
				}
			};
	@Autowired
	private PartsService partsService;
	@FXML
	private TextField searchField;
	@FXML
	private Button backButton;

	public static CurrentOrderWrapper getCurrentOrderWrapper() {
		return currentOrderWrapper;
	}

	public static void setCurrentOrderWrapper(CurrentOrderWrapper currentOrderWrapper) {
		CurrentOrderController.currentOrderWrapper = currentOrderWrapper;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadOrdersDetails();
		filtrationTable();
	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		changeQuantityColumn.setCellFactory(cellEditFactory);
	}

	public void loadOrdersDetails() {
		partsList.clear();
		partsOrdersList.clear();
		partsOrdersList = partsOrdersService.findAllById_OrdersIsNullIsZero();

		for (PartsOrders list : partsOrdersList) {
			Parts part = partsService.findByIdParts(list.getId_parts());
			partsList.add(part);
		}
		CurrentOrderWrapper wrapper = new CurrentOrderWrapper();
		ObservableList<CurrentOrderWrapper> currentOrderWrappers = wrapper.currentOrderWrappers(partsList, partsOrdersList);
		currentOrderTable.setItems(currentOrderWrappers);
		currentOrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		loadTotalValue();
	}

	private void loadTotalValue() {
		totalValue = 0.0;
		CurrentOrderWrapper wrapper = new CurrentOrderWrapper();
		ObservableList<CurrentOrderWrapper> currentOrderWrappers = wrapper.currentOrderWrappers(partsList, partsOrdersList);
		for (CurrentOrderWrapper w : currentOrderWrappers) {
			totalValue += w.getValue();
		}
		totalValue = Math.round(totalValue * 100.0) / 100.0;
		valueLabel.setText("Wartość zamówienia: " + totalValue);
	}


	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(currentOrderTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	private void filtrationTable() {
		ObservableList data = currentOrderTable.getItems();
		searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (oldValue != null && (newValue.length() < oldValue.length())) {
				currentOrderTable.setItems(data);
			}
			String value = newValue.toLowerCase();
			ObservableList<CurrentOrderWrapper> subentries = FXCollections.observableArrayList();

			long count = currentOrderTable.getColumns().stream().count();
			for (int i = 0; i < currentOrderTable.getItems().size(); i++) {
				for (int j = 0; j < count; j++) {
					String entry = "" + currentOrderTable.getColumns().get(j).getCellData(i);
					if (entry.toLowerCase().contains(value)) {
						subentries.add(currentOrderTable.getItems().get(i));
						break;
					}
				}
			}
			currentOrderTable.setItems(subentries);
		});
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void deletePart(ActionEvent event) {
		List<CurrentOrderWrapper> parts = currentOrderTable.getSelectionModel().getSelectedItems();
		List<PartsOrders> deleteParts = new ArrayList<>();
		for (CurrentOrderWrapper sc : parts) {
			deleteParts.add(partsOrdersService.findByIdParts(sc.getId_parts_orders()));
		}
		alertDeleteParts(deleteParts);
		loadOrdersDetails();


	}

	private void alertDeleteParts(List<PartsOrders> parts) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuwania");
		alert.setHeaderText(null);
		alert.setContentText("Czy napewno chcesz usunąć wybrane częsci z zamowienia?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) partsOrdersService.deleteInBatch(parts);
	}

	@FXML
	public void submitOrderAction(ActionEvent event) throws ParseException {
		//stworzenie nowego zamówienia
		if (totalValue > 0) {
			Orders order = new Orders();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(sdf.format(new Date()));
			order.setDate(date);
			order.setStatus("Zakonczono");
			order.setValue(totalValue);
			ordersService.saveAndFlush(order);

			//aktualizacja bieżacych zamówień z id 0 na id nowego zamówienia
			//Zwiększenie stanu magazynu o zamówione części
			List<PartsOrders> orderedParts = partsOrdersService.findAllById_OrdersIsNullIsZero();
			for (PartsOrders po : orderedParts) {
				po.setIdOrders(order.getIdOrders());
				partsOrdersService.save(po);

				Parts part = partsService.findByIdParts(po.getId_parts());
				part.setQuantity(part.getQuantity() + po.getQuantity());
				partsService.save(part);
			}
			loadOrdersDetails();
		}
	}
}
