package com.serwis.controller;

import com.serwis.authentication.UserOnline;
import com.serwis.config.StageManager;
import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import com.serwis.services.IssuedPartsService;
import com.serwis.services.PartsService;
import com.serwis.util.status.IssuedPartsStatus;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.IssuedPartsWrapper;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 12.03.2018.
 */
@Controller
public class WarehousemanController implements Initializable {
	@Autowired
	private IssuedPartsService issuedPartsService;
	@Autowired
	private PartsService partsService;
	@FXML
	private TableView<IssuedPartsWrapper> orderedTable;
	@FXML
	private TableColumn<IssuedPartsWrapper,Integer> idColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,String> nameColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,Integer> quantityColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,String> statusColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,Boolean> inOrderColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,Boolean> issueColumn;
	private List<IssuedParts> issuedPartsList = new ArrayList<>();
	private List<Parts> partsList = new ArrayList<>();

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private Button logoutButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (UserOnline.getIdRole() == 1) {
			logoutButton.setText("Powrót");
		}
		ordinalNumber();
		setColumnProperties();
		loadIssuedPartsDetails();
	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("namePart"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		//inOrderColumn.setCellFactory(cellDetailsFactory);
		//issueColumn.setCellFactory(cell);
	}

	public void loadIssuedPartsDetails() {
		issuedPartsList.clear();
		partsList.clear();
		issuedPartsList = issuedPartsService.findByStatusIsNot(IssuedPartsStatus.ZAKONCZONE.getStatus());

		for (IssuedParts list : issuedPartsList) {
			Parts part = partsService.findByIdParts(list.getIdParts());
			partsList.add(part);
		}
		IssuedPartsWrapper wrapper = new IssuedPartsWrapper();
		ObservableList<IssuedPartsWrapper> issuedPartsWrappers = wrapper.issuedPartsWrappers(partsList, issuedPartsList);
		orderedTable.setItems(issuedPartsWrappers);
		orderedTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(orderedTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	@FXML
	public void logoutAction(ActionEvent event) {
		if (UserOnline.getIdRole() != 1) {
			stageManager.switchScene(FxmlView.LOGIN);
		} else {
			stageManager.switchScene(FxmlView.MANAGER);
		}
	}

	public void addNewPartsAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDPARTS);
	}

	@FXML
	public void listPartsAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.LISTPARTS);
	}

	@FXML
	public void ordersAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.CURRENTORDER);
	}

	@FXML
	public void historyOrdersAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.HISTORYORDERS);
	}
}
