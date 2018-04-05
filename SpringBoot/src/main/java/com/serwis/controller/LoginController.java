package com.serwis.controller;

import com.serwis.authentication.UserOnline;
import com.serwis.config.StageManager;
import com.serwis.entity.Users;
import com.serwis.repository.UsersRepository;
import com.serwis.view.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by jakub on 28.02.2018.
 */
@Controller
public class LoginController implements Initializable {

	@Autowired
	UsersRepository usersRepository;

	@Lazy
	@Autowired
	private StageManager stageManager;
	@FXML
	private TextField textfieldUsername;
	@FXML
	private TextField textfieldPassword;
	@FXML
	private Button btnLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setKeyPressedEnterToLogin();
	}

	private void setKeyPressedEnterToLogin() {
		btnLogin.setOnKeyPressed(event -> {
			try {
				if (event.getCode() == KeyCode.ENTER) {
					loginAction();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	public void loginAction() throws Exception {
		if (usersRepository.findByUsername(textfieldUsername.getText()) == null) {
			incorrectNameAlert();
		} else if (passwordIsCorrect()) {
			incorrectPasswordAlert();
		} else {
			Users user = usersRepository.findByUsername(textfieldUsername.getText());
			UserOnline.setUsername(user.getUsername());
			UserOnline.setRole(user.getRole());
			switchScene(user.getRole());
		}
	}

	private boolean passwordIsCorrect() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return !bCryptPasswordEncoder.matches(textfieldPassword.getText(),usersRepository.findByUsername(textfieldUsername.getText()).getPassword());
		}

	private void switchScene(String role) throws Exception {
		if (role.equalsIgnoreCase("administrator")) {
			stageManager.switchScene(FxmlView.MANAGER);
		} else if (role.equalsIgnoreCase("warehouseman")) {
			stageManager.switchScene(FxmlView.WAREHOUSEMAN);
		} else if (role.equalsIgnoreCase("serviceman")) {
			stageManager.switchScene(FxmlView.SERVICEMAN);
		} else {
			throw new Exception("Rola użytkownika jest niepoprawna");
		}
	}

	private void incorrectPasswordAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Logowanie nieprawidłowe");
		alert.setHeaderText("Podane hasło jest nieprawidłowe");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			textfieldPassword.clear();
		}
	}

	private void incorrectNameAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Logowanie nieprawidłowe");
		alert.setHeaderText("Nie znaleziono takiego użytkownika");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			textfieldUsername.clear();
			textfieldPassword.clear();
		}
	}

}

