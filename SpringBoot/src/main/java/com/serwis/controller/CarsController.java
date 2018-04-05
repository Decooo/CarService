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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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

	@FXML
	public Button backButton;
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

	private ObservableList<Cars> carsList = FXCollections.observableArrayList();


	@Lazy
	@Autowired
	private StageManager stageManager;

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
