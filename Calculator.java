import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.LineBorder;


public class Calculator {
    int width=360;
    int height=540;
    boolean expectingNum=false;

    String[] buttonValues= {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "", "="
    };
    String[] rightSymbols= {"+", "-", "x", "÷", "="};
    String[] topSymbols= {"AC", "+/-", "%"};

    JFrame frame= new JFrame("Calculator");
    JLabel displayLabel= new JLabel();
    JPanel topPanel= new JPanel();
    JPanel buttonPanel= new JPanel();

    String A= null;
    String operator =null;
    String B=null;

    public Calculator(){
        
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.gray);


        displayLabel.setBackground(Color.black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setOpaque(true);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.black);
        topPanel.setPreferredSize(new Dimension(width,120));

        displayLabel.setBorder(BorderFactory.createEmptyBorder(0,10,15,10)); 
        topPanel.add(displayLabel,BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

       

        JPanel buttonPanel= new JPanel(new GridLayout(5,4,5,5));
        buttonPanel.setBackground(Color.black);
        frame.add(buttonPanel, BorderLayout.CENTER);

       
        for(int i=0; i < buttonValues.length ; i++){
            JButton button = new JButton();
            String buttonValue=  buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);

            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(Color.gray);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setForeground(Color.white);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(Color.orange);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setForeground(Color.white);

            }
            else{
                button.setBackground(Color.darkGray);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setForeground(Color.white);


            }
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button= (JButton) e.getSource();
                    String buttonValue=button.getText();

                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue.equals("=")){
                            if(A!=null && operator !=null){
                                
                                String[] parts = displayLabel.getText().split("[+x÷-]");
                                if (parts.length > 1) {
                                    B = parts[1].trim();
                                } else {
                                    B = displayLabel.getText();
                                }

                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                double result = 0;

                                if(operator.equals("+")){
                                    result = numA + numB;
                                }
                                else if(operator.equals("x")){
                                    result = numA * numB;
                                }
                                else if(operator.equals("÷")){
                                    result = numA / numB;
                                }
                                else if(operator.equals("-")){
                                    result = numA - numB;

                                }
                                displayLabel.setText(removeZeroDecimal(result));
                                A = Double.toString(result);
                                operator = null;
                                B = null;

                            }
                        }
                        else if("+-x÷".contains(buttonValue)){
                            if(operator==null){
                                A=displayLabel.getText();
                               
                            }
                            operator=buttonValue;
                            displayLabel.setText(A + " "+ operator);
                            expectingNum=true;
                        }
                       
                     
                    }
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue.equals("AC")){
                            clearAll();
                            displayLabel.setText("0");
                        }
                          else if(buttonValue.equals("+/-")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }  
                        else if(buttonValue.equals("%")){
                            double numDisplay= Double.parseDouble(displayLabel.getText());
                            numDisplay /=100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        } 
                    }else{
                        if(buttonValue.equals(".")){
                            String currentText=displayLabel.getText();
                            String currentSegment;
                            if (operator != null && currentText.contains(operator)) {
                                currentSegment = currentText.substring(currentText.lastIndexOf(operator) + 1).trim();
                            } else {
                                currentSegment = currentText.trim();
                            }
                        
                            if (!currentSegment.contains(".")) {
                                displayLabel.setText(currentText + ".");
                            }
                        }
                        else if ("0123456789".contains(buttonValue)) {
                            if (operator == null) {
                                if (A == null || displayLabel.getText().equals("0")) {
                                    displayLabel.setText(buttonValue);  
                                } else {
                                    displayLabel.setText(displayLabel.getText() + buttonValue);  
                                }
                                A = displayLabel.getText(); 
                            } 
                            else {
                                if (expectingNum) {
                                    displayLabel.setText(A + " " + operator + " " + buttonValue);
                                    expectingNum = false;
                                } else {
                                    displayLabel.setText(displayLabel.getText() + buttonValue);
                                }
                            }
                        }
                    }
                }
            });
        }
        frame.setVisible(true);


    }

    public void clearAll(){
        A=null;
        operator=null;
        B=null;
        expectingNum=false;
    }


    public String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1==0){
            return Integer.toString((int)numDisplay);
        }else{
            return Double.toString(numDisplay);
        }
    }


}


