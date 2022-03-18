package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App  {
    static JTextArea tf1;
    static JTextArea tf2;
    final String title = "Collaborative Text Editor";
    JFrame frame = new JFrame(title);
    protected JLabel label1 = new JLabel("user1");
    protected JLabel label2 = new JLabel("user1");
    protected final static String QUEUE_NAME = "to_receiver";
    App() {
        label2.setBackground(Color.PINK);
        tf1=  new JTextArea("Person A writes here");
        tf2=  new JTextArea("Person B writes here");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,12));
        p.add(label1);
        p.add(tf1);
        p.add(label2);
        p.add(tf2);
        frame.add(p);
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(50, 50);
        frame.setVisible(true);

    }
}
