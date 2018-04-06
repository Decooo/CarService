package com.serwis.controller;

import com.serwis.config.StageManager;
import com.serwis.entity.Cars;
import com.serwis.services.CarsService;
import com.serwis.view.FxmlView;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class CarsController implements Initializable {

	public static Cars car;
	@FXML
	private Button backButton;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private CarsService carsService;
	@FXML
	private TableView<Cars> carsTable;
	@FXML
	private TableColumn<Cars, Integer> idColumn;
	@FXML
	private TableColumn<Cars, String> brandColumn;
	@FXML
	private TableColumn<Cars, String> modelColumn;
	@FXML
	private TableColumn<Cars, Integer> yearProductionColumn;
	@FXML
	private TableColumn<Cars, String> VINColumn;
	@FXML
	private TableColumn<Cars, String> nrRegistrationColumn;
	@FXML
	private TableColumn<Cars, Boolean> editColumn;
	private ObservableList<Cars> carsList = FXCollections.observableArrayList();
	private Callback<TableColumn<Cars, Boolean>, TableCell<Cars, Boolean>> cellFactory =
			new Callback<TableColumn<Cars, Boolean>, TableCell<Cars, Boolean>>() {
				@Override
				public TableCell<Cars, Boolean> call(final TableColumn<Cars, Boolean> param) {
					final TableCell<Cars, Boolean> cell = new TableCell<Cars, Boolean>() {
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
									Cars cars = getTableView().getItems().get(getIndex());
									try {
										updateCars(cars);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								});

								btnEdit.setStyle("-fx-background-color: transparent;");
								ImageView iv = new ImageView();
								iv.setImage(imgEdit);
								iv.setPreserveRatio(true);
								iv.setSmooth(true);
								iv.setCache(true);
								btnEdit.setGraphic(iv);

								setGraphic(btnEdit);
								setAlignment(Pos.CENTER);
								setText(null);
							}
						}

						private void updateCars(Cars cars) throws IOException {
							setCar(cars);
							stageManager.switchSceneAndWait(FxmlView.UPDATECAR);
						}
					};
					return cell;
				}
			};

	public static Cars getCar() {
		return car;
	}

	public static void setCar(Cars car) {
		CarsController.car = car;
	}

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
		brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
		yearProductionColumn.setCellValueFactory(new PropertyValueFactory<>("year_production"));
		VINColumn.setCellValueFactory(new PropertyValueFactory<>("VIN"));
		nrRegistrationColumn.setCellValueFactory(new PropertyValueFactory<>("registration_number"));
		editColumn.setCellFactory(cellFactory);
	}

	public void loadCarsDetails() {
		carsList.clear();
		carsList.addAll(carsService.findAll());
		carsTable.setItems(carsList);
		carsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	@FXML
	public void newCarAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDCAR);
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void deleteCars(ActionEvent event) {
		List<Cars> cars = carsTable.getSelectionModel().getSelectedItems();
		alertDeleteCars(cars);
		loadCarsDetails();
	}

	private void alertDeleteCars(List<Cars> cars) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuwania");
		alert.setHeaderText(null);
		alert.setContentText("Czy napewno chcesz usunąć wybrane pojazdy?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) carsService.deleteInBatch(cars);
	}
}