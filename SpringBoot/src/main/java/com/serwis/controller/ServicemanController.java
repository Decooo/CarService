package com.serwis.controller;

import com.serwis.authentication.UserOnline;
import com.serwis.config.StageManager;
import com.serwis.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 09.03.2018.
 */
@Controller
public class ServicemanController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private Button logoutButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (UserOnline.getRole().equalsIgnoreCase("administrator")) {
			logoutButton.setText("Powrót");
		}
	}

	@FXML
	public void logoutAction(ActionEvent event) {
		if(!UserOnline.getRole().equalsIgnoreCase("administrator")){
			stageManager.switchScene(FxmlView.LOGIN);
		}else{
			stageManager.switchScene(FxmlView.MANAGER);
		}
	}

	@FXML
	public void openCarsListAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.CARS);
	}
}
