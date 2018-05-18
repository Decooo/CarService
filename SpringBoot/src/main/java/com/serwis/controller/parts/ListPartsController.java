package com.serwis.controller.parts;

import com.serwis.entity.Parts;
import com.serwis.entity.TypeParts;
import com.serwis.services.PartsService;
import com.serwis.services.TypePartsService;
import com.serwis.wrappers.PartsWrapper;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 18.05.2018.
 */
@Controller
public class ListPartsController implements Initializable {
	@FXML
	private TextField searchField;
	@FXML
	private Button backButton;
	@FXML
	private TableView<PartsWrapper> partsTable;
	@FXML
	private TableColumn<PartsWrapper, Integer> idColumn;
	@FXML
	private TableColumn<PartsWrapper, String> nameColumn;
	@FXML
	private TableColumn<PartsWrapper, String> typeColumn;
	@FXML
	private TableColumn<PartsWrapper, Integer> quantityColumn;
	@FXML
	private TableColumn<PartsWrapper, Double> priceColumn;
	@FXML
	private TableColumn<PartsWrapper, Boolean> orderColumn;

	@Autowired
	private PartsService partsService;
	@Autowired
	private TypePartsService typePartsService;

	private List<Parts> partsList = new ArrayList<>();
	private List<TypeParts> typePartsList = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadPartsDetails();
		filtrationTable();
	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


	}

	private void loadPartsDetails() {
		partsList.clear();
		typePartsList.clear();
		partsList.addAll(partsService.findAll());
		for (Parts list : partsList) {
			TypeParts type = typePartsService.getType(list.getId_type_parts());
			typePartsList.add(type);
		}
		PartsWrapper wrapper = new PartsWrapper();
		ObservableList<PartsWrapper> partsWrappers = wrapper.PartsWrapper(partsList, typePartsList);
		partsTable.setItems(partsWrappers);
		partsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}


	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(partsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	private void filtrationTable() {
		ObservableList data = partsTable.getItems();
		searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (oldValue != null && (newValue.length() < oldValue.length())) {
				partsTable.setItems(data);
			}
			String value = newValue.toLowerCase();
			ObservableList<PartsWrapper> subentries = FXCollections.observableArrayList();

			long count = partsTable.getColumns().stream().count();
			for (int i = 0; i < partsTable.getItems().size(); i++) {
				for (int j = 0; j < count; j++) {
					String entry = "" + partsTable.getColumns().get(j).getCellData(i);
					if (entry.toLowerCase().contains(value)) {
						subentries.add(partsTable.getItems().get(i));
						break;
					}
				}
			}
			partsTable.setItems(subentries);
		});
	}

	@FXML
	public void deleteParts(ActionEvent event) {
		List<PartsWrapper> parts = partsTable.getSelectionModel().getSelectedItems();
		List<Parts> deleteParts = new ArrayList<>();
		for (PartsWrapper sc : parts) {
			deleteParts.add(partsService.findByIdParts(sc.getIdParts()));
		}
		alertDeleteParts(deleteParts);
		loadPartsDetails();
	}

	private void alertDeleteParts(List<Parts> parts) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuwania");
		alert.setHeaderText(null);
		alert.setContentText("Czy napewno chcesz usunąć wybrane części?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) partsService.deleteInBatch(parts);
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
}
