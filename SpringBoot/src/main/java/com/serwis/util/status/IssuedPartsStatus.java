package com.serwis.util.status;

/**
 * Created by jakub on 31.05.2018.
 */
public enum IssuedPartsStatus {
	NOWE {
		@Override
		public String getStatus() {
			return "Nowe";
		}
	},
	WZAMOWIENIU {
		@Override
		public String getStatus() {
			return "W zamówieniu";
		}
	},
	ZAKONCZONE {
		@Override
		public String getStatus() {
			return "Zakonczone";
		}
	};

	public abstract String getStatus();

}
