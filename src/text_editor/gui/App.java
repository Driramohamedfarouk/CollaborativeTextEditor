package text_editor.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//TODO : model all queues and remove unnecessary ones -> done
//TODO : make an observable on the labels to avoid using queues ->done
//TODO : consider OOP strong encapsulation principles
public class App  {
    JTextArea tf1;
    JTextArea tf2;
    final String title = "Collaborative Text Editor";
    public JFrame frame = new JFrame(title);
    protected JLabel label1 = new JLabel("No one is writing here");
    protected JLabel label2 = new JLabel("No one is writing here");
    public final static String QUEUE_NAME = "to_receiver";

    public String USER_NAME ;
    public String USERS_NAME_QUEUE ;
    private String[] my_queues ;

    public final static String TO_FRIEND2 = "to_friend2";
    public final static String USERS_QUEUE_2 = "users_queue_2" ;

    public final static String TO_FRIEND= "to_friend";
    public static final String USERS_QUEUE_1 = "users_queue_1" ;

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
        frame.add(p);
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(50, 50);
        frame.setVisible(true);
    }

    public JTextArea getTf1() {
        return tf1;
    }

    public void setTf1(JTextArea tf1) {
        this.tf1 = tf1;
    }

    public JTextArea getTf2() {
        return tf2;
    }

    public void setTf2(JTextArea tf2) {
        this.tf2 = tf2;
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

    public void setLabel1(JLabel label1) {
        this.label1 = label1;
    }

    public void setLabel2(JLabel label2) {
        this.label2 = label2;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUsersNameQueue() {
        return this.USERS_NAME_QUEUE;
    }

    public void setUsersNameQueue(String usersNameQueue) {
        this.USERS_NAME_QUEUE = usersNameQueue;
    }
}
