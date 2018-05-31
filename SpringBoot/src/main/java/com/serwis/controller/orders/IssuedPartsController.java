package com.serwis.controller.orders;

import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import com.serwis.services.IssuedPartsService;
import com.serwis.services.PartsService;
import com.serwis.util.status.IssuedPartsStatus;
import com.serwis.wrappers.IssuedPartsWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
 * Created by jakub on 31.05.2018.
 */
@Controller
public class IssuedPartsController implements Initializable{
	@Autowired
	private IssuedPartsService issuedPartsService;
	@Autowired
	private PartsService partsService;
	@FXML
	private TextField searchText;
	@FXML
	private Button backBtn;
	@FXML
	private TableView<IssuedPartsWrapper> issuedPartsTable;
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
	private List<IssuedParts> issuedPartsList = new ArrayList<>();
	private List<Parts> partsList = new ArrayList<>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadIssuedParts();
		filtrationTable();
	}

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
		issuedPartsList = issuedPartsService.findByStatusIs(IssuedPartsStatus.ZAKONCZONE.getStatus());

		for (IssuedParts list : issuedPartsList) {
			Parts part = partsService.findByIdParts(list.getIdParts());
			partsList.add(part);
		}
		IssuedPartsWrapper wrapper = new IssuedPartsWrapper();
		ObservableList<IssuedPartsWrapper> issuedPartsWrappers = wrapper.issuedPartsWrappers(partsList, issuedPartsList);
		issuedPartsTable.setItems(issuedPartsWrappers);
		issuedPartsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(issuedPartsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	private void filtrationTable() {
		ObservableList data = issuedPartsTable.getItems();
		searchText.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (oldValue != null && (newValue.length() < oldValue.length())) {
				issuedPartsTable.setItems(data);
			}
			String value = newValue.toLowerCase();
			ObservableList<IssuedPartsWrapper> subentries = FXCollections.observableArrayList();

			long count = issuedPartsTable.getColumns().stream().count();
			for (int i = 0; i < issuedPartsTable.getItems().size(); i++) {
				for (int j = 0; j < count; j++) {
					String entry = "" + issuedPartsTable.getColumns().get(j).getCellData(i);
					if (entry.toLowerCase().contains(value)) {
						subentries.add(issuedPartsTable.getItems().get(i));
						break;
					}
				}
			}
			issuedPartsTable.setItems(subentries);
		});
	}

}
