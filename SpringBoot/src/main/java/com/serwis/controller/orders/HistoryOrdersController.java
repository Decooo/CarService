package com.serwis.controller.orders;

import com.serwis.config.StageManager;
import com.serwis.entity.Orders;
import com.serwis.services.OrdersService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.05.2018.
 */
@Controller
public class HistoryOrdersController implements Initializable {

	private static Orders order;
	@Autowired
	private OrdersService ordersService;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@FXML
	private TableView<Orders> historyOrdersTable;
	@FXML
	private TableColumn<Orders, Integer> idColumn;
	@FXML
	private TableColumn<Orders, Date> dateColumn;
	@FXML
	private TableColumn<Orders, String> statusColumn;
	@FXML
	private TableColumn<Orders, Double> valueColumn;
	@FXML
	private TableColumn<Orders, Boolean> detailColumn;
	@FXML
	private TextField searchField;
	@FXML
	private Button backBtn;
	private ObservableList<Orders> ordersList = FXCollections.observableArrayList();
	private Callback<TableColumn<Orders, Boolean>, TableCell<Orders, Boolean>> cellHistoryFactory =
			new Callback<TableColumn<Orders, Boolean>, TableCell<Orders, Boolean>>() {
				@Override
				public TableCell<Orders, Boolean> call(final TableColumn<Orders, Boolean> param) {
					final TableCell<Orders, Boolean> cell = new TableCell<Orders, Boolean>() {
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
									Orders orders = getTableView().getItems().get(getIndex());
									try {
										historyOrders(orders);
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

						private void historyOrders(Orders orders) throws IOException {
							setOrder(orders);
							stageManager.switchSceneAndWait(FxmlView.DETAILSORDER);
						}
					};
					return cell;
				}
			};

	public static Orders getOrder() {
		return order;
	}

	public static void setOrder(Orders order) {
		HistoryOrdersController.order = order;
	}

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
		detailColumn.setCellFactory(cellHistoryFactory);

		dateColumn.setCellFactory(column -> {
			TableCell<Orders, Date> celll = new TableCell<Orders, Date>() {
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

			return celll;
		});

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

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

}
