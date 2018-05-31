package com.serwis.controller.repairs;

import com.serwis.config.StageManager;
import com.serwis.controller.ServicemanController;
import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import com.serwis.entity.Repairs;
import com.serwis.services.IssuedPartsService;
import com.serwis.services.PartsService;
import com.serwis.services.RepairsService;
import com.serwis.util.status.RepairStatus;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.IssuedPartsWrapper;
import com.serwis.wrappers.RepairsWrapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 31.05.2018.
 */
@Controller
public class DetailsRepairController implements Initializable {
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
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private RepairsService repairsService;
	@Autowired
	private ServicemanController servicemanController;
	@Autowired
	private IssuedPartsService issuedPartsService;
	@Autowired
	private PartsService partsService;
	@FXML
	private Label priceLabel;
	@FXML
	private ComboBox<String> statusCombo;
	@FXML
	private Label carLabel;
	@FXML
	private Label clientLabel;
	@FXML
	private TextArea commentsText;
	@FXML
	private TextField dedicatedTimeText;
	@FXML
	private Button backBtn;
	private ObservableList<String> statusList = FXCollections.observableArrayList();
	private int idStatus = 0;
	private List<IssuedParts> issuedPartsList = new ArrayList<>();
	private List<Parts> partsList = new ArrayList<>();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RepairsWrapper repairsWrapper = ServicemanController.getRepairs();
		setRepairsProperties(repairsWrapper);
		statusCombo.setItems(doListStatus());
		statusCombo.getSelectionModel().select(idStatus);
		ordinalNumber();
		setColumnProperties();
		loadIssuedParts();

	}

	private void setRepairsProperties(RepairsWrapper repair) {
		priceLabel.setText(String.valueOf(repair.getPrice()));
		carLabel.setText(repair.getCar());
		clientLabel.setText(repair.getClient());
		dedicatedTimeText.setText(String.valueOf(repair.getDedicatedTime()));
		commentsText.setText(repair.getComments());
	}
	private ObservableList<String> doListStatus() {
		statusList.clear();
		for(RepairStatus status : RepairStatus.values()){
			if(!status.getStatus().equals(RepairStatus.ZAKONCZONE.getStatus())){
				statusList.add(status.getStatus());
				if(status.getStatus().equalsIgnoreCase(ServicemanController.getRepairs().getStatus())){
					idStatus = statusList.size()-1;
				}
			}
		}
		return statusList;
	}

	@FXML
	public void orderPartAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ORDERPARTS);
	}

	@FXML
	public void endRepairAction(ActionEvent event) {
	}

	@FXML
	public void updateAction(ActionEvent event) throws IOException {
		Repairs repairs = new Repairs();
		if(!textIsDouble(dedicatedTimeText.getText()) || dedicatedTimeText.getText().length()==0){
			errorIntroducedDedicatedTime();
		}else{
			repairs.setIdRepairs(ServicemanController.getRepairs().getIdRepairs());
			repairs.setComments(commentsText.getText());
			repairs.setStatus(statusCombo.getValue());
			repairs.setDedicatedTime(Double.parseDouble(dedicatedTimeText.getText()));
			repairs.setStartDate(ServicemanController.getRepairs().getDate());
			repairs.setIdTypeRepairs(ServicemanController.getRepairs().getIdTypeRepair());
			repairs.setIdCars(ServicemanController.getRepairs().getIdCar());
			repairs.setIdClient(ServicemanController.getRepairs().getIdClient());
			repairs.setPrice(ServicemanController.getRepairs().getPrice());
			repairsService.save(repairs);
			alertUpdatedRepair();
		}
	}

	private void alertUpdatedRepair() throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Zaaktualizowano dane");
		alert.setHeaderText("Zaaktualizowano dane zlecenia");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			servicemanController.loadRepairsDetails();
		}
	}

	private void errorIntroducedDedicatedTime() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText("Wprowadzono czas w niewłaściwy sposób");
		alert.getButtonTypes().setAll(ButtonType.OK);
		alert.showAndWait();
	}

	private boolean textIsDouble(String text) {
		return text.matches("[0-9]+(\\.){0,1}[0-9]*");
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
		issuedPartsList = issuedPartsService.findAllByIdRepairs(ServicemanController.getRepairs().getIdRepairs());

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
