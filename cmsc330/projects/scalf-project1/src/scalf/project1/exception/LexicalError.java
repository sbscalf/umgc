package scalf.project1.exception;

public class LexicalError extends Exception {

	private static final long serialVersionUID = 7674151227424338044L;
	
	public LexicalError(int line, String token) {
		super("Invalid token found on line " + line +
				".\nTOKEN:  " + token);
	}
}
