package com.serwis.view;

import java.util.ResourceBundle;

/**
 * Created by jakub on 08.03.2018.
 */
public enum FxmlView {
	SERVICEMAN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("serviceman.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Serviceman.fxml";
		}
	},
	WAREHOUSEMAN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("warehouseman.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Warehouseman.fxml";
		}
	},
	MANAGER {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("manager.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Manager.fxml";
		}
	},
	LOGIN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("login.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/accounts/Login.fxml";
		}
	},
	REGISTRATION {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("registration.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/accounts/Registration.fxml";
		}
	},
	LISTACCOUNTS {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("listAccounts.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/accounts/ListAccounts.fxml";
		}
	},
	CARS {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("cars.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/cars/Cars.fxml";
		}
	},
	ADDCAR {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("addCar.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/cars/AddCar.fxml";
		}
	},
	UPDATECAR {
		@Override
		public String getTitle() {return getStringFromResourceBundle("updateCar.title");}

		@Override
		public String getFxmlFile() {return "/fxml/cars/UpdateCar.fxml";}
	},
	CARREPAIRHISTORY {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("carRepairHistory.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/cars/CarRepairHistory.fxml";
		}
	},
	CLIENTS {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("clients.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/clients/clients.fxml";
		}
	},
	ADDCLIENT {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("addclient.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/clients/addClient.fxml";
		}
	},
	UPDATECLIENT {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("updateclient.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/clients/updateClient.fxml";
		}
	},
	CLIENTHISTORY {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("clientHistory.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/clients/clientHistory.fxml";
		}
	},
	SERVICECONTRACTS {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("serviceContracts.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/serviceContracts/ServiceContracts.fxml";
		}
	},
	ADDSERVICECONTRACT {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("addServiceContracts.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/serviceContracts/AddServiceContract.fxml";
		}
	},
	UPDATESERVICECONTRACT {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("updateServiceContracts.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/serviceContracts/UpdateServiceContract.fxml";
		}
	},
	ADDPARTS {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("addParts.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/parts/addParts.fxml";
		}
	},
	LISTPARTS {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("listParts.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/parts/listParts.fxml";
		}
	},
	CURRENTORDER {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("currentOrder.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/orders/currentOrder.fxml";
		}
	},
	ADDPARTTOORDER {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("addPartToOrder.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/orders/addPartToOrder.fxml";
		}
	},
	UPDATEQUANTITYPARTTOORDER {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("updateQuantityPartToOrder.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/orders/updateQuantityPartToOrder.fxml";
		}
	};;


	public abstract String getTitle();

	public abstract String getFxmlFile();

	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}
}
