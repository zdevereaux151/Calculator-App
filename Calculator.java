import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.swing.border.LineBorder;

public class Calculator{
    int width=360;
    int height=540;
    JFrame frame= new JFrame("Calculator");

    public Calculator(){
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(FlowLayout());
        frame.setVisible(true);


    }
}