package com.serwis.controller.cars;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 06.04.2018.
 */
@Controller
public class CarRepairHistoryController implements Initializable {
	@FXML
	private TableColumn dateColumn;
	@FXML
	private TableColumn typeColumn;
	@FXML
	private TableColumn priceColumn;
	@FXML
	private TableColumn idColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
