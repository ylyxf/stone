package org.siqisource.stone.runtime.exceptions;

public class ProgramException extends RuntimeException {

	private static final long serialVersionUID = 2277641090703683770L;

	public ProgramException() {
		super();
	}

	public ProgramException(String message) {
		super(message);
	}

	public ProgramException(String message, Exception ex) {
		super(message, ex);
	}

}
