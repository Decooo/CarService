package com.serwis.controller.cars;

import com.serwis.Util.Alerts.ValidationCarAlert;
import com.serwis.entity.Cars;
import com.serwis.services.CarsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 05.04.2018.
 */
@Controller
public class UpdateCarController implements Initializable {

	@Autowired
	CarsService carsService;
	@Autowired
	CarsController carsController;

	@FXML
	private TextField textFieldBrand;
	@FXML
	private TextField textFieldModel;
	@FXML
	private TextField textFieldVIN;
	@FXML
	private TextField textFieldRegistrationNumber;
	@FXML
	private ComboBox yearsProductionComboBox;
	@FXML
	private Button backButton;

	private ObservableList<Integer> yearsProduction = FXCollections.observableArrayList();

	private int indexYearProduction;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Cars car = CarsController.getCar();
		yearsProductionComboBox.setItems(doListYearsProduction());
		setCarProperties(car);
	}

	private void setCarProperties(Cars car) {
		yearsProductionComboBox.getSelectionModel().select(indexYearProduction);
		textFieldBrand.setText(car.getBrand());
		textFieldModel.setText(car.getModel());
		textFieldVIN.setText(car.getVIN());
		textFieldRegistrationNumber.setText(car.getRegistration_number());
	}

	private ObservableList<Integer> doListYearsProduction() {
		yearsProduction.clear();
		for (int i = 2018; i >= 1970; i--) {
			yearsProduction.add(i);
		}
		setIndexYearProduction();
		return yearsProduction;
	}

	private void setIndexYearProduction() {
		Cars car = CarsController.getCar();
		indexYearProduction = 2018 - car.getYear_production();
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void updateCarAction(ActionEvent event) throws IOException {
		if (textFieldBrand.getText().length() == 0) {
			ValidationCarAlert.notIntroducedBrand();
		} else if (textFieldModel.getText().length() == 0) {
			ValidationCarAlert.notIntroducedModel();
		} else if (textFieldVIN.getText().length() != 17) {
			ValidationCarAlert.badLenghtVINnumber();
		} else if (textFieldRegistrationNumber.getText().length() == 0) {
			ValidationCarAlert.notIntroducedRegistrationNumber();
		} else {
			Cars car = CarsController.getCar();
			car.setBrand(textFieldBrand.getText());
			car.setModel(textFieldModel.getText());
			car.setVIN(textFieldVIN.getText());
			car.setRegistration_number(textFieldRegistrationNumber.getText());
			car.setYear_production(yearsProduction.get(yearsProductionComboBox.getSelectionModel().getSelectedIndex()));
			carsService.addCar(car);
			alertUpdatedCar();
		}
	}



	private void alertUpdatedCar() throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Zaaktualizowano dane");
		alert.setHeaderText("Zaaktualizowano dane pojazu");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.close();
			carsController.loadCarsDetails();
		}
	}
}
