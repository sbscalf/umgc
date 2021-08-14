package scalf.project1.util;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import scalf.project1.exception.SyntaxError;
import static scalf.project1.util.Keyword.*;

public class Parser {
	private Tokenizer tokenizer;
	private Token currentToken;
	private Keyword expectedKeyword;
	private JFrame window;
	private String message, string;
	private int number;
	private boolean needWidget, needRadio;
	private final List<Keyword> VALIDWIDGETS =
			Arrays.asList(BUTTON,GROUP,LABEL,PANEL,TEXTFIELD);
	
	public Parser(Reader reader) throws Exception {
		tokenizer = new Tokenizer(reader);
	}
	
	public void showTokens() {
		System.out.println(tokenizer.toString());
	}
	
	public void parse() throws Exception {
		currentToken = tokenizer.getNext();
		if (parseGui()) {
			window.setLocationRelativeTo(null);
			window.setResizable(false);
			window.setVisible(true);
		} else {
			message = "Expected " + expectedKeyword.name() +
					" but found " + currentToken.keyword.name();
			throw new SyntaxError(currentToken.line, message);
		}
	}
	
	private void evaluateToken(Keyword keyword) throws Exception {
		expectedKeyword = keyword;
		if (currentToken.keyword == keyword) {
			string = currentToken.value;
			number = currentToken.number;
			currentToken = tokenizer.getNext();
		} else {
			message = "Expected " + expectedKeyword.name() +
					" but found " + currentToken.keyword.name();
			throw new SyntaxError(currentToken.line, message);
		}
	}
	
	private boolean parseGui() throws Exception {
		evaluateToken(WINDOW);
		window = new JFrame();
		
		evaluateToken(STRING);
		window.setTitle(string);
		
		evaluateToken(OPEN_PARENTHESIS);
		evaluateToken(NUMBER);
		int width = number;
		evaluateToken(COMMA);
		evaluateToken(NUMBER);
		int height = number;
		evaluateToken(CLOSE_PARENTHESIS);
		window.setSize(width, height);
		
		parseLayout(window);
		
		needWidget = true;
		parseWidgets(window);
		
		evaluateToken(Keyword.END);
		evaluateToken(Keyword.PERIOD);
		return true;
	}
	
	private void parseLayout(Container container) throws Exception {
		evaluateToken(LAYOUT);
		parseLayoutType(container);
		evaluateToken(COLON);
	}
	
	private void parseLayoutType(Container container) throws Exception {
		if (currentToken.keyword == FLOW) {
			evaluateToken(FLOW);
			container.setLayout(new FlowLayout());
		} else {
			parseGridLayout(container);
		}
	}
	
	private void parseGridLayout(Container container) throws Exception {
		evaluateToken(GRID);
		evaluateToken(OPEN_PARENTHESIS);
		evaluateToken(NUMBER);
		int rows = number;
		evaluateToken(COMMA);
		evaluateToken(NUMBER);
		int columns = number;
		GridLayout grid = new GridLayout(rows, columns);
		if (currentToken.keyword == COMMA) {
			parseGridLayoutGaps(grid);
		}
		evaluateToken(CLOSE_PARENTHESIS);
		container.setLayout(grid);
	}
	
	private void parseGridLayoutGaps(GridLayout grid) throws Exception {
		evaluateToken(COMMA);
		evaluateToken(NUMBER);
		grid.setHgap(number);
		evaluateToken(COMMA);
		evaluateToken(NUMBER);
		grid.setVgap(number);
	}
	
	private boolean parseWidgets(Container container) throws Exception {
		if (parseWidget(container)) {
			needWidget = false;
			if (parseWidgets(container)) {
				return true;
			}
			return true;
		} else {
			if (needWidget) {
				throw new SyntaxError(currentToken.line,
					"Expected Widget Type. Found " + currentToken.keyword);
			}
		}
		return false;
	}
	
	private boolean parseWidget(Container container) throws Exception {
		if (VALIDWIDGETS.contains(currentToken.keyword)) { 
			switch (currentToken.keyword) {
				case BUTTON:
					parseButton(container);
					break;
				case GROUP:
					parseGroup(container);
					break;
				case LABEL:
					parseLabel(container);
					break;
				case PANEL:
					parsePanel(container);
					break;
				case TEXTFIELD:
					parseTextField(container);
					break;
				default:
					return false;
			}
			return true;
		} // Current Token is not a Widget
		return false;
	}
	
	private void parseButton(Container container) throws Exception {
		evaluateToken(BUTTON);
		evaluateToken(STRING);
		JButton button = new JButton(string);
		evaluateToken(SEMICOLON);
		container.add(button);
	}
	
	private void parseGroup(Container container) throws Exception {
		evaluateToken(GROUP);
		ButtonGroup group = new ButtonGroup();
		needRadio = true;
		parseRadioButtons(container, group);
		evaluateToken(END);
		evaluateToken(SEMICOLON);
	}
	
	private boolean parseRadioButtons(Container container, ButtonGroup group) throws Exception {
		if (parseRadioButton(container, group)) {
			needRadio = false;
			if (parseRadioButtons(container, group)) {
				return true;
			}
			return true;
		} else {
			if (needRadio) {
				throw new SyntaxError(currentToken.line,
						"Expected RADIO. Found " + currentToken.value);
			}
		}
		return false;
	}
	
	private boolean parseRadioButton(Container container, ButtonGroup group) throws Exception {
		if (currentToken.keyword == RADIO) {
			evaluateToken(RADIO);
			evaluateToken(STRING);
			JRadioButton radio = new JRadioButton(string);
			evaluateToken(SEMICOLON);
			group.add(radio);
			container.add(radio);
			if (needRadio)
				radio.setSelected(true);
			return true;
		}
		return false;
	}
	
	private void parseLabel(Container container) throws Exception {
		evaluateToken(LABEL);
		evaluateToken(STRING);
		JLabel label = new JLabel(string);
		evaluateToken(SEMICOLON);
		container.add(label);
	}
	
	private void parsePanel(Container container) throws Exception {
		evaluateToken(PANEL);
		JPanel panel = new JPanel();
		parseLayout(panel);
		
		needWidget = true;
		parseWidgets(panel);
		
		evaluateToken(END);
		evaluateToken(SEMICOLON);
		container.add(panel);
	}
	
	private void parseTextField(Container container) throws Exception {
		evaluateToken(TEXTFIELD);
		evaluateToken(NUMBER);
		JTextField textfield = new JTextField(number);
		evaluateToken(SEMICOLON);
		container.add(textfield);
	}
	
}
