package com.serwis.config;

import com.serwis.view.FxmlView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Created by jakub on 08.03.2018.
 */
public class StageManager {

	//private static final Logger LOG = getLogger(StageManager.class);
	private final Stage primaryStage;
	private final SpringFXMLLoader springFXMLLoader;

	public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
		this.springFXMLLoader = springFXMLLoader;
		this.primaryStage = stage;
	}

	public void switchScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		show(viewRootNodeHierarchy, view.getTitle());
	}

	private void show(final Parent rootnode, String title) {
		setOptionsScene(rootnode, title);

		try {
			primaryStage.show();
		} catch (Exception exception) {
			logAndExit("Nie można wyświetlić sceny: " + title, exception);
		}
	}

	private void setOptionsScene(Parent rootnode, String title) {
		Scene scene = prepareScene(rootnode);
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
	}

	private Scene prepareScene(Parent rootnode) {
		Scene scene = primaryStage.getScene();

		if (scene == null) {
			scene = new Scene(rootnode);
		}
		scene.setRoot(rootnode);
		return scene;
	}

	private Parent loadViewNodeHierarchy(String fxmlFilePath) {
		Parent rootNode = null;
		try {
			rootNode = springFXMLLoader.load(fxmlFilePath);
			Objects.requireNonNull(rootNode, "Root fxml nie może być nullem");
		} catch (Exception exception) {
			logAndExit("Nie można załadować widoku fxml: " + fxmlFilePath, exception);
		}
		return rootNode;
	}

	private void logAndExit(String errorMsg, Exception exception) {
		//LOG.error(errorMsg, exception, exception.getCause());
		Platform.exit();
	}

	public void switchSceneAndWait(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		showAndWait(viewRootNodeHierarchy, view.getTitle());
	}

	private void showAndWait(Parent rootnode, String title) {
		setOptionsScene(rootnode, title);

		try {
			primaryStage.showAndWait();
		} catch (Exception exception) {
			logAndExit("Nie można wyświetlić sceny: " + title, exception);
		}
	}

}

