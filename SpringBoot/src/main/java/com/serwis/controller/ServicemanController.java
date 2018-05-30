package com.serwis.controller;

import com.serwis.authentication.UserOnline;
import com.serwis.config.StageManager;
import com.serwis.entity.Cars;
import com.serwis.entity.Clients;
import com.serwis.entity.Repairs;
import com.serwis.services.CarsService;
import com.serwis.services.ClientsService;
import com.serwis.services.RepairsService;
import com.serwis.util.RepairStatus;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.RepairsWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 09.03.2018.
 */
@Controller
public class ServicemanController implements Initializable {
	@Autowired
	private RepairsService repairsService;
	@Autowired
	private CarsService carsService;
	@Autowired
	private ClientsService clientsService;
	@FXML
	private TableView<RepairsWrapper> repairsTable;
	@FXML
	private TableColumn<RepairsWrapper,Integer> idColumn;
	@FXML
	private TableColumn<RepairsWrapper,String> carColumn;
	@FXML
	private TableColumn<RepairsWrapper,String> clientColumn;
	@FXML
	private TableColumn<RepairsWrapper,Date> dateColumn;
	@FXML
	private TableColumn<RepairsWrapper,String> statusColumn;
	@FXML
	private TableColumn<RepairsWrapper,Boolean> detailsColumn;
	@Lazy
	@Autowired
	private StageManager stageManager;

	private List<Cars> carsList = new ArrayList<>();
	private List<Clients> clientsList = new ArrayList<>();
	private List<Repairs> repairsList = new ArrayList<>();

	@FXML
	private Button logoutButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (UserOnline.getIdRole() == 1) {
			logoutButton.setText("Powrót");
		}
		ordinalNumber();
		setColumnProperties();
		loadRepairsDetails();
	}

	private void setColumnProperties() {
		carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		//detailsColumn.setCellFactory(cellEditFactory);
	}

	public void loadRepairsDetails() {
		clientsList.clear();
		carsList.clear();
		repairsList.clear();
		repairsList = repairsService.findByStatusIsNot(RepairStatus.ZAKONCZONE.getStatus());

		for (Repairs list : repairsList) {
			Cars car = carsService.findByIdCars(list.getIdCars());
			carsList.add(car);
			Clients client = clientsService.findByIdClients(list.getIdClient());
			clientsList.add(client);
		}
		RepairsWrapper wrapper = new RepairsWrapper();
		ObservableList<RepairsWrapper> repairsWrappers = wrapper.repairsWrappers(carsList, clientsList,repairsList);
		repairsTable.setItems(repairsWrappers);
		repairsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}



	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(repairsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	@FXML
	public void logoutAction(ActionEvent event) {
		if(UserOnline.getIdRole() != 1){
			stageManager.switchScene(FxmlView.LOGIN);
		}else{
			stageManager.switchScene(FxmlView.MANAGER);
		}
	}

	@FXML
	public void openCarsListAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.CARS);
	}

	@FXML
	public void openCardsClientsAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.CLIENTS);
	}

	@FXML
	public void newRepair(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDREPAIR);
	}
}
