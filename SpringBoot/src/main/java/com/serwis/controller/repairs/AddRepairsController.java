package com.serwis.controller.repairs;

import com.serwis.entity.Cars;
import com.serwis.entity.Clients;
import com.serwis.entity.Repairs;
import com.serwis.entity.TypeRepairs;
import com.serwis.services.CarsService;
import com.serwis.services.ClientsService;
import com.serwis.services.RepairsService;
import com.serwis.services.TypeRepairsService;
import com.serwis.util.RepairStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jakub on 29.05.2018.
 */
@Controller
public class AddRepairsController implements Initializable{
	@Autowired
	private RepairsService repairsService;
	@Autowired
	private TypeRepairsService typeRepairsService;
	@Autowired
	private CarsService carsService;
	@Autowired
	private ClientsService clientsService;
	@FXML
	private ComboBox<String> typeRepairCombo;
	@FXML
	private TextArea commentsText;
	@FXML
	private Button addRepairBtn;
	@FXML
	private ComboBox<String> clientCombo;
	@FXML
	private ComboBox<String> carCombo;

	@FXML
	private Button backButton;

	private ObservableList<String> listType = FXCollections.observableArrayList();
	private HashMap<String, Integer> mapType = new HashMap<>();
	private ObservableList<String> listCar = FXCollections.observableArrayList();
	private HashMap<String, Integer> mapCar = new HashMap<>();
	private ObservableList<String> listClient = FXCollections.observableArrayList();
	private HashMap<String, Integer> mapClient = new HashMap<>();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeRepairCombo.setItems(doListType());
		typeRepairCombo.getSelectionModel().select(0);
		carCombo.setItems(doListCar());
		carCombo.getSelectionModel().select(0);
		clientCombo.setItems(doListClient());
		clientCombo.getSelectionModel().select(0);
	}

	private ObservableList<String> doListType() {
		List<TypeRepairs> listTypeParts = typeRepairsService.findAll();
		listType.clear();
		doMapRole(listTypeParts);
		for (Map.Entry<String, Integer> entry : mapType.entrySet()) {
			listType.add(entry.getKey());
		}
		return listType;
	}

	private void doMapRole(List<TypeRepairs> list) {
		mapType.clear();
		for (TypeRepairs aList : list) {
			mapType.put(aList.getType(), aList.getIdTypeRepairs());
		}
	}
	private ObservableList<String> doListCar() {
		List<Cars> listCars = carsService.findAll();
		listCar.clear();
		doMapCar(listCars);
		for (Map.Entry<String, Integer> entry : mapCar.entrySet()) {
			listCar.add(entry.getKey());
		}
		return listCar;
	}

	private void doMapCar(List<Cars> list) {
		mapCar.clear();
		for (Cars aList : list) {
			mapCar.put(aList.getBrand() + " " + aList.getModel() + " - " + aList.getVIN(), aList.getId_cars());
		}
	}

	private ObservableList<String> doListClient() {
		List<Clients> listClients = clientsService.findAll();
		listClient.clear();
		doMapClient(listClients);
		for (Map.Entry<String, Integer> entry : mapClient.entrySet()) {
			listClient.add(entry.getKey());
		}
		return listClient;
	}

	private void doMapClient(List<Clients> list) {
		mapClient.clear();
		for (Clients aList : list) {
			mapClient.put(aList.getName() + " " + aList.getSurname() + " - " + aList.getPesel(), aList.getIdClients());
		}
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void addRepairsAction(ActionEvent event) throws ParseException {
		Repairs repairs = new Repairs();
		repairs.setComments(commentsText.getText());
		repairs.setDedicatedTime(0.0);
		repairs.setPrice(0.0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(sdf.format(new Date()));
		repairs.setStartDate(date);
		repairs.setStatus(RepairStatus.NOWE.getStatus());
		repairs.setIdCars(mapCar.get(carCombo.getSelectionModel().getSelectedItem()));
		repairs.setIdClient(mapClient.get(clientCombo.getSelectionModel().getSelectedItem()));
		repairs.setIdTypeRepairs(mapType.get(typeRepairCombo.getSelectionModel().getSelectedItem()));
		repairsService.save(repairs);
		alertAddedNewReapir();
	}


	private void alertAddedNewReapir() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Dodano nowe zlecenie");
		alert.setHeaderText("Pomyślnie dodano nowe zlecenie");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.close();
		}
	}
}
