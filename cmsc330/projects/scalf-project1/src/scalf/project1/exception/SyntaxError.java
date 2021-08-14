package scalf.project1.exception;

public class SyntaxError extends Exception {
	private static final long serialVersionUID = -358767305290397333L;

	public SyntaxError(int line, String message) {
		super("SYNTAX ERROR ON LINE " + line + "\n" + message);
	}
}
