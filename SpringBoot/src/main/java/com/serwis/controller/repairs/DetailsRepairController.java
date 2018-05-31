package com.serwis.controller.repairs;

import com.serwis.config.StageManager;
import com.serwis.controller.ServicemanController;
import com.serwis.entity.Repairs;
import com.serwis.services.RepairsService;
import com.serwis.util.status.RepairStatus;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.RepairsWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 31.05.2018.
 */
@Controller
public class DetailsRepairController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private RepairsService repairsService;
	@Autowired
	private ServicemanController servicemanController;
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RepairsWrapper repairsWrapper = ServicemanController.getRepairs();
		setRepairsProperties(repairsWrapper);
		statusCombo.setItems(doListStatus());
		statusCombo.getSelectionModel().select(idStatus);

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

	public void loadIssuedParts() {
	}
}
