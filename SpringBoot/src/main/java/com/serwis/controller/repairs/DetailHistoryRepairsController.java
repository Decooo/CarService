package com.serwis.controller.repairs;

import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import com.serwis.services.IssuedPartsService;
import com.serwis.services.PartsService;
import com.serwis.wrappers.IssuedPartsWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 02.06.2018.
 */
@Controller
public class DetailHistoryRepairsController implements Initializable {

	@Autowired
	private IssuedPartsService issuedPartsService;
	@Autowired
	private PartsService partsService;
	@FXML
	private Label carLabel;
	@FXML
	private Label clientLabel;
	@FXML
	private TableView<IssuedPartsWrapper> partsTable;
	@FXML
	private TableColumn<IssuedPartsWrapper,Integer> idColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,String> nameColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,Integer> quantityColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,Double> priceColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,Double> valueColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper,String> statusColumn;
	@FXML
	private Label priceLabel;
	@FXML
	private Label dedicatedTimeLabel;
	@FXML
	private Label commentsLabel;
	@FXML
	private Button backBtn;

	private List<IssuedParts> issuedPartsList = new ArrayList<>();
	private List<Parts> partsList = new ArrayList<>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadIssuedParts();
		settLabel();
	}

	private void settLabel() {
		priceLabel.setText(String.valueOf(HistoryRepairsController.getRepairs().getPrice()));
		dedicatedTimeLabel.setText(String.valueOf(HistoryRepairsController.getRepairs().getDedicatedTime()));
		commentsLabel.setText(HistoryRepairsController.getRepairs().getComments());
		carLabel.setText(HistoryRepairsController.getRepairs().getCar());
		clientLabel.setText(HistoryRepairsController.getRepairs().getClient());
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("namePart"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
	}

	public void loadIssuedParts() {
		issuedPartsList.clear();
		partsList.clear();
		issuedPartsList = issuedPartsService.findAllByIdRepairs(HistoryRepairsController.getRepairs().getIdRepairs());

		for (IssuedParts list : issuedPartsList) {
			Parts part = partsService.findByIdParts(list.getIdParts());
			partsList.add(part);
		}
		IssuedPartsWrapper wrapper = new IssuedPartsWrapper();
		ObservableList<IssuedPartsWrapper> issuedPartsWrappers = wrapper.issuedPartsWrappers(partsList, issuedPartsList);
		partsTable.setItems(issuedPartsWrappers);
		partsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(partsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}
}
