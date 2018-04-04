package com.serwis.controller;

import com.serwis.config.StageManager;
import com.serwis.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 12.03.2018.
 */
@Controller
public class ManagerController implements Initializable {

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void logoutAction(ActionEvent event) {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	public void servicemanAction(ActionEvent event) {
		stageManager.switchScene(FxmlView.SERVICEMAN);
	}

	@FXML
	public void warehousemanAction(ActionEvent event) {
		stageManager.switchScene(FxmlView.WAREHOUSEMAN);
	}

	public void newAccountAction(ActionEvent event) {
		stageManager.switchSceneAndWait(FxmlView.LOGIN);
	}
}
