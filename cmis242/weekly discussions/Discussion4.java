package discussion4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.ScriptEngineManager;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Discussion4 {

    public static class GUI {
        
        private JButton one = new JButton("1");
        private JButton two = new JButton("2");
        private JButton three = new JButton("3");
        private JButton four = new JButton("4");
        private JButton five = new JButton("5");
        private JButton six = new JButton("6");
        private JButton seven = new JButton("7");
        private JButton eight = new JButton("8");
        private JButton nine = new JButton("9");
        private JButton zero = new JButton("0");
        private JButton decimal = new JButton(".");
        private JButton equals = new JButton("=");
        private JButton add = new JButton("+");
        private JButton subtract = new JButton("-");
        private JButton multiply = new JButton("*");
        private JButton divide = new JButton("/");
        private JButton clear = new JButton("CLR");
        private JLabel display = new JLabel("");
        private JPanel displayPanel = new JPanel(new FlowLayout());
        private JPanel numberPanel = new JPanel(new GridLayout(4, 3, 8, 8));
        private JPanel operatorPanel = new JPanel(new GridLayout(4, 1, 8, 8));
        private JPanel outerPanel = new JPanel(new BorderLayout());

        public GUI() {
            
            // Display Panel for Calculator
            displayPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            displayPanel.add(display);

            // Number Panel for Calculator
            numberPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            numberPanel.add(one); 
            numberPanel.add(two);
            numberPanel.add(three);
            numberPanel.add(four);
            numberPanel.add(five);
            numberPanel.add(six);
            numberPanel.add(seven);
            numberPanel.add(eight);
            numberPanel.add(nine);
            numberPanel.add(zero);
            numberPanel.add(decimal);
            numberPanel.add(equals);
            
            // Operator Panel for Calculator
            operatorPanel.setBorder
                (BorderFactory.createEmptyBorder(4, 4, 4, 4));
            operatorPanel.add(add);
            operatorPanel.add(subtract);
            operatorPanel.add(multiply);
            operatorPanel.add(divide);
            operatorPanel.add(clear);
            
            // Outer Panel for Child Panels
            outerPanel.add(displayPanel, BorderLayout.NORTH);
            outerPanel.add(numberPanel, BorderLayout.CENTER);            
            outerPanel.add(operatorPanel, BorderLayout.EAST);
            
            // Listener for the calculator buttons
            event userAction = new event();
            one.addActionListener(userAction);
            two.addActionListener(userAction);
            three.addActionListener(userAction);
            four.addActionListener(userAction);
            five.addActionListener(userAction);
            six.addActionListener(userAction);
            seven.addActionListener(userAction);
            eight.addActionListener(userAction);
            nine.addActionListener(userAction);
            zero.addActionListener(userAction);
            decimal.addActionListener(userAction);
            add.addActionListener(userAction);
            subtract.addActionListener(userAction);
            multiply.addActionListener(userAction);
            divide.addActionListener(userAction);
            equals.addActionListener(userAction);
            clear.addActionListener(userAction);
            
             // frame to hold the combined panels and display to screen
            JFrame frame = new JFrame("Mike's Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(outerPanel);
            frame.setSize(350, 350);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true); 
        }
            
        public class event implements ActionListener {

            public void actionPerformed(ActionEvent userAction) {

                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String calculation;
                String results;
                String operators = userAction.getActionCommand();
                
                if (operators.equals("1")) {
                    display.setText(display.getText()+"1");
                } 

                if (operators.equals("2")) {
                    display.setText(display.getText()+"2");
                } 
                
                if (operators.equals("3")) {
                    display.setText(display.getText()+"3");
                } 
                
                if (operators.equals("4")) {
                    display.setText(display.getText()+"4");
                } 
                
                if (operators.equals("5")) {
                    display.setText(display.getText()+"5");
                } 
                
                if (operators.equals("6")) {
                    display.setText(display.getText()+"6");
                } 
                
                if (operators.equals("7")) {
                    display.setText(display.getText()+"7");
                } 
                
                if (operators.equals("8")) {
                    display.setText(display.getText()+"8");
                } 
                
                if (operators.equals("9")) {
                    display.setText(display.getText()+"9");
                } 
                
                if (operators.equals("0")) {
                    display.setText(display.getText()+"0");
                } 
                
                if (operators.equals(".")) {
                    display.setText(display.getText()+".");
                }
                
                if (operators.equals("+")) {
                    display.setText(display.getText()+"+");
                }
                
                if (operators.equals("-")) {
                    display.setText(display.getText()+"-");
                }
                
                if (operators.equals("/")) {
                    display.setText(display.getText()+"/");
                }
                
                if (operators.equals("*")) {
                    display.setText(display.getText()+"*");
                }
                
                if (operators.equals("CLR")) {
                    display.setText("");
                }
                
                if (operators.equals("=")) {
                    calculation = display.getText();
                    
                    try {
                        results = (engine.eval(calculation)).toString();
                        display.setText(results);
                    } catch (ScriptException ex) {
                        System.out.println(ex);
                    }
                    
                } 
                
                } // end public void actionPerformed(ActionEvent userAction)

         } // end public class event implements ActionListener
        
    } // end public static class Display
            
        
    public static void main(String[] args) {
        
        GUI calculator = new GUI();  
        
    }
    
}
