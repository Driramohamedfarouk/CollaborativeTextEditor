package text_editor.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//TODO : model all queues and remove unnecessary ones
//TODO : make an observable on the labels to avoid using queues
public class App  {
    static JTextArea tf1;
    static JTextArea tf2;
    final String title = "Collaborative Text Editor";
    public JFrame frame = new JFrame(title);
    protected JLabel label1 = new JLabel("user1");
    protected JLabel label2 = new JLabel("user1");
    public final static String QUEUE_NAME = "to_receiver";
    App() {
        label2.setBackground(Color.PINK);
        tf1=  new JTextArea("Person A writes here");
        tf2=  new JTextArea("Person B writes here");
        JPanel p = new JPanel();
        JPanel rp = new JPanel();
        rp.setSize(100,600);
        JPanel lp = new JPanel();
        p.setLayout(new GridLayout(1,2));
        rp.setLayout(new GridLayout(2,1));
        lp.setLayout(new GridLayout(2,1));
        rp.add(label1);
        rp.add(label2);
        lp.add(tf1);
        lp.add(tf2);
        p.add(rp); p.add(lp);
        /*label1.setSize(100,300);
        label2.setSize(100,300);
        p.add(label1);
        p.add(tf1);
        p.add(label2);
        p.add(tf2);*/
        frame.add(p);
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(50, 50);
        frame.setVisible(true);

    }

    public static JTextArea getTf1() {
        return tf1;
    }

    public static void setTf1(JTextArea tf1) {
        App.tf1 = tf1;
    }

    public static JTextArea getTf2() {
        return tf2;
    }

    public static void setTf2(JTextArea tf2) {
        App.tf2 = tf2;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1.setText(label1);
    }

    public JLabel getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2.setText(label2);
    }
}
