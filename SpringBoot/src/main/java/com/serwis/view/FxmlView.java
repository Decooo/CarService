package com.serwis.view;

import java.util.ResourceBundle;

/**
 * Created by jakub on 08.03.2018.
 */
public enum FxmlView {
	SERVICEMAN{
		@Override
		public String getTitle(){
			return getStringFromResourceBundle("serviceman.title");
		}
		@Override
		public String getFxmlFile(){
			return "/fxml/Serviceman.fxml";
		}
	},
	WAREHOUSEMAN{
		@Override
		public String getTitle(){
			return getStringFromResourceBundle("warehouseman.title");
		}
		@Override
		public String getFxmlFile(){
			return "/fxml/Warehouseman.fxml";
		}
	},
	MANAGER{
		@Override
		public String getTitle(){
			return getStringFromResourceBundle("manager.title");
		}
		@Override
		public String getFxmlFile(){
			return "/fxml/Manager.fxml";
		}
	},
	LOGIN{
		@Override
		public String getTitle(){
			return getStringFromResourceBundle("login.title");
		}
		@Override
		public String getFxmlFile(){
			return "/fxml/Login.fxml";
		}
	},
	REGISTRATION{
		@Override
		public String getTitle(){
			return getStringFromResourceBundle("registration.title");
		}
		@Override
		public String getFxmlFile(){
			return "/fxml/Registration.fxml";
		}
	},
	LISTACCOUNTS{
		@Override
		public String getTitle(){
			return getStringFromResourceBundle("listAccounts.title");
		}
		@Override
		public String getFxmlFile(){
			return "/fxml/ListAccounts.fxml";
		}
	};


	public abstract String getTitle();
	public abstract String getFxmlFile();

	String getStringFromResourceBundle(String key){
		return ResourceBundle.getBundle("Bundle").getString(key);
	}
}
