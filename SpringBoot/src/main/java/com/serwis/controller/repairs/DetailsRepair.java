package com.serwis.controller.repairs;

import com.serwis.controller.ServicemanController;
import com.serwis.util.RepairStatus;
import com.serwis.wrappers.RepairsWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 31.05.2018.
 */
@Controller
public class DetailsRepair implements Initializable {
	@FXML
	private Label priceLabel;
	@FXML
	private ComboBox<String> statusCombo;
	@FXML
	private Label carLabel;
	@FXML
	private Label clientLabel;
	@FXML
	private TextArea commentsLabel;
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
		commentsLabel.setText(repair.getComments());
	}
	private ObservableList<String> doListStatus() {
		statusList.clear();
		for(RepairStatus status : RepairStatus.values()){
			statusList.add(status.getStatus());
			if(status.getStatus().equalsIgnoreCase(ServicemanController.getRepairs().getStatus())){
				idStatus = statusList.size()-1;
			}
		}
		return statusList;
	}

	@FXML
	public void orderPartAction(ActionEvent event) {
	}

	@FXML
	public void endRepairAction(ActionEvent event) {
	}

	@FXML
	public void updateAction(ActionEvent event) {
	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}
}
