package scalf.project1.util;

public class Token {
	public final String value;
	public final int number;
	public final Keyword keyword;
	public final int line;
	
	public Token(String value, Keyword keyword, int line) {
		this.value = value;
		this.number = 0;
		this.keyword = keyword;
		this.line = line;
	}
	
	public Token(int number, Keyword keyword, int line) {
		this.value = null;
		this.number = number;
		this.keyword = keyword;
		this.line = line;
	}
	
	public String toString() {
		if (value == null)
			return String.format("%s: %d", keyword.name(), number);
		else
			return String.format("%s: %s", keyword.name(), value);
	}
	
}
