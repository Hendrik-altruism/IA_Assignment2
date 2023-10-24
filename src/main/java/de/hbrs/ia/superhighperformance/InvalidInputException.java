package de.hbrs.ia.superhighperformance;

public class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = 3837878499303110266L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String message) {
		super(message);
	}
}
