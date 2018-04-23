package com.serwis.controller.serviceContracts;

import com.serwis.config.StageManager;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.ServiceContractsWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 23.04.2018.
 */
@Controller
public class ServiceContractsController implements Initializable {

	public TableColumn<ServiceContractsWrapper, Integer> idColumn;
	public TableColumn<ServiceContractsWrapper, Double> nameColumn;
	public TableColumn<ServiceContractsWrapper, Double> surnameColumn;
	public TableColumn<ServiceContractsWrapper, Double> workingTimeColumn;
	public TableColumn<ServiceContractsWrapper, Double> amountForPartsColumn;
	public TableColumn<ServiceContractsWrapper, Double> remainingWorkingTimeColumn;
	public TableColumn<ServiceContractsWrapper, Double> remainingAmountForPartsColumn;
	public TableColumn<ServiceContractsWrapper, Boolean> editColumn;
	public TableColumn<ServiceContractsWrapper, Boolean> historyColumn;
	@FXML
	private TextField searchTextField;
	@FXML
	private TableView<ServiceContractsWrapper> contractsTable;
	@FXML
	private Button backButton;
	@Lazy
	@Autowired
	private StageManager stageManager;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void backAction(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void newContractAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDSERVICECONTRACT);
	}

	@FXML
	public void deleteContract(ActionEvent event) {
	}

	public void loadClientsDetails() {
	}
}
