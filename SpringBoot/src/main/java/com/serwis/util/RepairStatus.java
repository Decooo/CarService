package com.serwis.util;

/**
 * Created by jakub on 30.05.2018.
 */
public enum RepairStatus {

	NOWE {
		@Override
		public String getStatus() {
			return "Nowe";
		}
	},
	WTRAKCIE {
		@Override
		public String getStatus() {
			return "W trakcie";
		}
	},
	ZAKONCZONE {
		@Override
		public String getStatus() {
			return "Zakonczone";
		}
	},
	OCZEKIWANIENACZESCI {
		@Override
		public String getStatus() {
			return "Ozcekiwanie na czesci";
		}
	};

	public abstract String getStatus();

}
