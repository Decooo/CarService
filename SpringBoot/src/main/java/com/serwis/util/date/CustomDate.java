package com.serwis.util.date;

import java.text.SimpleDateFormat;

/**
 * Created by jakub on 23.05.2018.
 */
public class CustomDate extends java.sql.Date {
	public CustomDate(long date) {
		super(date);
	}

	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(this);
	}

}
