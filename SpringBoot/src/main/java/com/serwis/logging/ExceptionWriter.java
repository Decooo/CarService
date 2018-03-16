package com.serwis.logging;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by jakub on 08.03.2018.
 */
public class ExceptionWriter extends PrintWriter {
	public ExceptionWriter(Writer writer) {
		super(writer);
	}

	private String wrapAroundWithNewlines(String stringWithoutNewlines) {
		return ("\n" + stringWithoutNewlines + "\n");
	}

	/*
	 * Convert a stacktrace into a string
	 */
	public String getExceptionAsString(Throwable throwable) {
		throwable.printStackTrace(this);

		String exception = super.out.toString();

		return (wrapAroundWithNewlines(exception));
	}
}
