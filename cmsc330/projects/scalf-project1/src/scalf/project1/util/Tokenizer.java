package scalf.project1.util;

import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.List;

import scalf.project1.exception.*;
import scalf.project1.structure.Queue;

class Tokenizer {
	private static final int RESERVED = 11;
	private static final int PUNCTUATION = 17;
	private StreamTokenizer streamTokenizer;
	private Queue<Token> tokens;
	private List<Character> punctuation =
			Arrays.asList('(',',',')','.',':',';');
		
	Tokenizer(Reader reader) throws Exception {
		streamTokenizer = new StreamTokenizer(reader);
		streamTokenizer.ordinaryChar('.');
		getTokens();
	}
	
	private void getTokens() throws Exception {
		tokens = new Queue<Token>();
		int current;
		
		current = streamTokenizer.nextToken();
		while (current != StreamTokenizer.TT_EOF) {
			switch (streamTokenizer.ttype) {
				case StreamTokenizer.TT_NUMBER:
					tokens.add(new Token((int)streamTokenizer.nval,
							Keyword.NUMBER,
							streamTokenizer.lineno()));
					break;
				case StreamTokenizer.TT_WORD:
					for (Keyword aKeyword : Keyword.values()) {
						if (aKeyword.ordinal() == RESERVED) {
							throw new LexicalError(streamTokenizer.lineno(),
									streamTokenizer.sval);
						} else {
							if (aKeyword.name().equals(streamTokenizer.sval.toUpperCase())) {
								tokens.add(new Token(streamTokenizer.sval,
										aKeyword,
										streamTokenizer.lineno()));
								break;
							}
						}
					}
					break;
				case '"':
					tokens.add(new Token(streamTokenizer.sval,
							Keyword.STRING,
							streamTokenizer.lineno()));
					break;
				default:
					if (punctuation.contains((char)current)) {
						int index = RESERVED + punctuation.indexOf((char)current);
						if (index < PUNCTUATION) {
							Keyword keyword = Keyword.values()[index];
							tokens.add(new Token(Character.toString((char)current),
									keyword,
									streamTokenizer.lineno()));
						} else {
							throw new LexicalError(streamTokenizer.lineno(),
								Character.toString((char)current));
						}
					} else {
						throw new LexicalError(streamTokenizer.lineno(),
							Character.toString((char)current));
					}
					
			}
			
			current = streamTokenizer.nextToken();
		} // end while
		
	}
	
	boolean hasNext() {
		return !(this.tokens.isEmpty());
	}
	
	Token getNext() {
		return tokens.getNext();
	}
	
	int getLine() {
		return streamTokenizer.lineno();
	}
	
	public String toString() {
		return tokens.toString();
	}
}
