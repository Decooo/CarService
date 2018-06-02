package com.serwis.controller.repairs;

import com.serwis.config.StageManager;
import com.serwis.entity.Cars;
import com.serwis.entity.Clients;
import com.serwis.entity.Repairs;
import com.serwis.entity.TypeRepairs;
import com.serwis.services.CarsService;
import com.serwis.services.ClientsService;
import com.serwis.services.RepairsService;
import com.serwis.services.TypeRepairsService;
import com.serwis.util.imageSettings.EditAndHistoryButton;
import com.serwis.util.status.RepairStatus;
import com.serwis.view.FxmlView;
import com.serwis.wrappers.RepairsWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jakub on 31.05.2018.
 */
@Controller
public class HistoryRepairsController implements Initializable{
	private static RepairsWrapper repairs;
	@Autowired
	private RepairsService repairsService;
	@Autowired
	private CarsService carsService;
	@Autowired
	private ClientsService clientsService;
	@Autowired
	private TypeRepairsService typeRepairsService;
	@FXML
	private TableView<RepairsWrapper> repairsTable;
	@FXML
	private TableColumn<RepairsWrapper, Integer> idColumn;
	@FXML
	private TableColumn<RepairsWrapper, String> carColumn;
	@FXML
	private TableColumn<RepairsWrapper, String> clientColumn;
	@FXML
	private TableColumn<RepairsWrapper, String> typeRepairsColumn;
	@FXML
	private TableColumn<RepairsWrapper, Date> dateColumn;
	@FXML
	private TableColumn<RepairsWrapper, String> statusColumn;
	@FXML
	private TableColumn<RepairsWrapper, Boolean> detailsColumn;
	private List<Cars> carsList = new ArrayList<>();
	private List<Clients> clientsList = new ArrayList<>();
	private List<Repairs> repairsList = new ArrayList<>();
	private List<TypeRepairs> typeRepairsList = new ArrayList<>();
	public static RepairsWrapper getRepairs() {
		return repairs;
	}

	public static void setRepairs(RepairsWrapper repairs) {
		HistoryRepairsController.repairs = repairs;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ordinalNumber();
		setColumnProperties();
		loadRepairsDetails();
	}

	@Lazy
	@Autowired
	private StageManager stageManager;

	private Callback<TableColumn<RepairsWrapper, Boolean>, TableCell<RepairsWrapper, Boolean>> cellDetails =
			new Callback<TableColumn<RepairsWrapper, Boolean>, TableCell<RepairsWrapper, Boolean>>() {
				@Override
				public TableCell<RepairsWrapper, Boolean> call(final TableColumn<RepairsWrapper, Boolean> param) {
					final TableCell<RepairsWrapper, Boolean> cell = new TableCell<RepairsWrapper, Boolean>() {
						final Button btnDetail = new Button();
						Image imgEdit = new Image(getClass().getResourceAsStream("/images/detail.png"));

						@Override
						public void updateItem(Boolean check, boolean empty) {
							super.updateItem(check, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								btnDetail.setOnAction(e -> {
									RepairsWrapper repair = getTableView().getItems().get(getIndex());
									try {
										detailRepair(repair);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								});

								btnDetail.setStyle("-fx-background-color: transparent;");
								ImageView iv = EditAndHistoryButton.getImageView(imgEdit);
								btnDetail.setGraphic(iv);

								setGraphic(btnDetail);
								setAlignment(Pos.CENTER);
								setText(null);
							}
						}

						private void detailRepair(RepairsWrapper repair) throws IOException {
							setRepairs(repair);
							stageManager.switchSceneAndWait(FxmlView.DETAILHISTORYREPAIRS);
						}
					};
					return cell;
				}
			};
	private void setColumnProperties() {
		carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		typeRepairsColumn.setCellValueFactory(new PropertyValueFactory<>("typeRepairs"));
		detailsColumn.setCellFactory(cellDetails);
		dateColumn.setCellFactory(column -> {
			TableCell<RepairsWrapper, Date> cell = new TableCell<RepairsWrapper, Date>() {
				private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(format.format(item));
					}
				}
			};

			return cell;
		});
	}

	public void loadRepairsDetails() {
		clientsList.clear();
		carsList.clear();
		repairsList.clear();
		typeRepairsList.clear();
		repairsList = repairsService.findByStatusIs(RepairStatus.ZAKONCZONE.getStatus());

		for (Repairs list : repairsList) {
			Cars car = carsService.findByIdCars(list.getIdCars());
			carsList.add(car);
			Clients client = clientsService.findByIdClients(list.getIdClient());
			clientsList.add(client);
			TypeRepairs typeRepairs = typeRepairsService.findByIdTypeRepairs(list.getIdTypeRepairs());
			typeRepairsList.add(typeRepairs);
		}
		RepairsWrapper wrapper = new RepairsWrapper();
		ObservableList<RepairsWrapper> repairsWrappers = wrapper.repairsWrappers(carsList, clientsList, repairsList,typeRepairsList);
		repairsTable.setItems(repairsWrappers);
		repairsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private void ordinalNumber() {
		idColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(repairsTable.getItems().indexOf(p.getValue()) + 1 + ""));
		idColumn.setSortable(false);
	}
}
