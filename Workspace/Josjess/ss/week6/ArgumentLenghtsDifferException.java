package ss.week6;

public class ArgumentLenghtsDifferException extends TooFewArgumentsException {
	public ArgumentLenghtsDifferException() {
	}

	public ArgumentLenghtsDifferException(String message) {
		super(message);
	}

	public ArgumentLenghtsDifferException(Throwable cause) {
		super(cause);
	}

	public ArgumentLenghtsDifferException(String message, Throwable cause) {
		super(message, cause);
	}
}
