package org.siqisource.stone.exceptions;

public class ProgramException extends RuntimeException {

	private static final long serialVersionUID = 2708738976919630722L;

	public ProgramException() {
		super();
	}

	public ProgramException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProgramException(String message) {
		super(message);
	}

	public ProgramException(Throwable cause) {
		super(cause);
	}

}
