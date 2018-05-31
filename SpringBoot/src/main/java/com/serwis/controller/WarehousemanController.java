package com.serwis.controller;

import com.serwis.authentication.UserOnline;
import com.serwis.config.StageManager;
import com.serwis.entity.IssuedParts;
import com.serwis.entity.Parts;
import com.serwis.services.IssuedPartsService;
import com.serwis.services.PartsService;
import com.serwis.util.imageSettings.EditAndHistoryButton;
import com.serwis.util.status.IssuedPartsStatus;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.IssuedPartsWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
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
 * Created by jakub on 12.03.2018.
 */
@Controller
public class WarehousemanController implements Initializable {
	@Autowired
	private IssuedPartsService issuedPartsService;
	@Autowired
	private PartsService partsService;
	@FXML
	private TableView<IssuedPartsWrapper> orderedTable;
	@FXML
	private TableColumn<IssuedPartsWrapper, Integer> idColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper, String> nameColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper, Integer> quantityColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper, String> statusColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper, Boolean> inOrderColumn;
	@FXML
	private TableColumn<IssuedPartsWrapper, Boolean> issueColumn;
	private List<IssuedParts> issuedPartsList = new ArrayList<>();
	private List<Parts> partsList = new ArrayList<>();

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private Button logoutButton;
	private Callback<TableColumn<IssuedPartsWrapper, Boolean>, TableCell<IssuedPartsWrapper, Boolean>> cellInOrderedFactory =
			new Callback<TableColumn<IssuedPartsWrapper, Boolean>, TableCell<IssuedPartsWrapper, Boolean>>() {
				@Override
				public TableCell<IssuedPartsWrapper, Boolean> call(final TableColumn<IssuedPartsWrapper, Boolean> param) {
					final TableCell<IssuedPartsWrapper, Boolean> cell = new TableCell<IssuedPartsWrapper, Boolean>() {
						final Button btnInOrdered = new Button();
						Image imgEdit = new Image(getClass().getResourceAsStream("/images/inOrdered.png"));

						@Override
						public void updateItem(Boolean check, boolean empty) {
							super.updateItem(check, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								btnInOrdered.setOnAction(e -> {
									IssuedPartsWrapper issuedPart = getTableView().getItems().get(getIndex());
									try {
										inOrdered(issuedPart);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								});

								btnInOrdered.setStyle("-fx-background-color: transparent;");
								ImageView iv = EditAndHistoryButton.getImageView(imgEdit);
								btnInOrdered.setGraphic(iv);

								setGraphic(btnInOrdered);
								setAlignment(Pos.CENTER);
								setText(null);
							}
						}

						private void inOrdered(IssuedPartsWrapper issuedPart) throws IOException {
							IssuedParts part = new IssuedParts();
							part.setIdIssuedParts(issuedPart.getIdIssued());
							part.setIdParts(issuedPart.getIdPart());
							part.setQuantity(issuedPart.getQuantity());
							part.setIdRepairs(issuedPart.getIdRepair());
							part.setStatus(IssuedPartsStatus.WZAMOWIENIU.getStatus());
							issuedPartsService.save(part);
							alertUpdateStatus();
						}

						private void alertUpdateStatus() {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Zaaktualizowano status");
							alert.setHeaderText("Zmieniono status na: W zamowieniu ");
							alert.getButtonTypes().setAll(ButtonType.OK);
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK) {
								loadIssuedPartsDetails();
							}
						}
					};
					return cell;
				}
			};
	private Callback<TableColumn<IssuedPartsWrapper, Boolean>, TableCell<IssuedPartsWrapper, Boolean>> cellIssue =
			new Callback<TableColumn<IssuedPartsWrapper, Boolean>, TableCell<IssuedPartsWrapper, Boolean>>() {
				@Override
				public TableCell<IssuedPartsWrapper, Boolean> call(final TableColumn<IssuedPartsWrapper, Boolean> param) {
					final TableCell<IssuedPartsWrapper, Boolean> cell = new TableCell<IssuedPartsWrapper, Boolean>() {
						final Button btnIssued = new Button();
						Image imgEdit = new Image(getClass().getResourceAsStream("/images/issued.png"));

						@Override
						public void updateItem(Boolean check, boolean empty) {
							super.updateItem(check, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								btnIssued.setOnAction(e -> {
									IssuedPartsWrapper issuedPart = getTableView().getItems().get(getIndex());
									try {
										issued(issuedPart);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								});

								btnIssued.setStyle("-fx-background-color: transparent;");
								ImageView iv = EditAndHistoryButton.getImageView(imgEdit);
								btnIssued.setGraphic(iv);

								setGraphic(btnIssued);
								setAlignment(Pos.CENTER);
								setText(null);
							}
						}

						private void issued(IssuedPartsWrapper issuedPart) throws IOException {
							Parts partInMagazine = partsService.findByIdParts(issuedPart.getIdPart());
							if (partInMagazine.getQuantity() < issuedPart.getQuantity()) {
								alertlackOfParts();
							} else {
								IssuedParts part = new IssuedParts();
								part.setIdIssuedParts(issuedPart.getIdIssued());
								part.setIdParts(issuedPart.getIdPart());
								part.setQuantity(issuedPart.getQuantity());
								part.setIdRepairs(issuedPart.getIdRepair());
								part.setStatus(IssuedPartsStatus.ZAKONCZONE.getStatus());
								alertIssuedPart(part);
							}
						}

						private void alertlackOfParts() {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Brak części");
							alert.setHeaderText("Brak części na magazynie");
							alert.getButtonTypes().setAll(ButtonType.OK);
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK) {
							}
						}

						private void alertIssuedPart(IssuedParts part) {
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Potwierdzenie wydawania");
							alert.setHeaderText(null);
							alert.setContentText("Czy napewno część zostala wydana?");
							Optional<ButtonType> result = alert.showAndWait();

							if (result.get() == ButtonType.OK) {
								issuedPartsService.save(part);
								Parts partInMagazine = partsService.findByIdParts(part.getIdParts());
								partInMagazine.setQuantity(partInMagazine.getQuantity()- part.getQuantity());
								partsService.save(partInMagazine);
								loadIssuedPartsDetails();
							}
						}

					};
					return cell;
				}
			};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (UserOnline.getIdRole() == 1) {
			logoutButton.setText("Powrót");
		}
		ordinalNumber();
		setColumnProperties();
		loadIssuedPartsDetails();
	}

	private void setColumnProperties() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("namePart"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		inOrderColumn.setCellFactory(cellInOrderedFactory);
		issueColumn.setCellFactory(cellIssue);
	}

	public void loadIssuedPartsDetails() {
		issuedPartsList.clear();
		partsList.clear();
		issuedPartsList = issuedPartsService.findByStatusIsNot(IssuedPartsStatus.ZAKONCZONE.getStatus());

		for (IssuedParts list : issuedPartsList) {
			Parts part = partsService.findByIdParts(list.getIdParts());
			partsList.add(part);
		}
		IssuedPartsWrapper wrapper = new IssuedPartsWrapper();
		ObservableList<IssuedPartsWrapper> issuedPartsWrappers = wrapper.issuedPartsWrappers(partsList, issuedPartsList);
		orderedTable.setItems(issuedPartsWrappers);
		orderedTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(orderedTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}

	@FXML
	public void logoutAction(ActionEvent event) {
		if (UserOnline.getIdRole() != 1) {
			stageManager.switchScene(FxmlView.LOGIN);
		} else {
			stageManager.switchScene(FxmlView.MANAGER);
		}
	}

	public void addNewPartsAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ADDPARTS);
	}

	@FXML
	public void listPartsAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.LISTPARTS);
	}

	@FXML
	public void ordersAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.CURRENTORDER);
	}

	@FXML
	public void historyOrdersAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.HISTORYORDERS);
	}

	@FXML
	public void issuePartsAction(ActionEvent event) throws IOException {
		stageManager.switchSceneAndWait(FxmlView.ISSUEDPARTS);
	}
}
