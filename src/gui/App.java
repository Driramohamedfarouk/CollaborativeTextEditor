package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// Our class extends Frame class and implements ActionListener interface
public class App  {
    // creating instances of TextField and Button class
    JTextArea tf1;
    JTextArea tf2;
    final String title = "Collaborative Text Editor";

    protected final static String QUEUE_NAME = "to_receiver";
    // instantiating using constructor
    App() {
        tf1=  new JTextArea("Person A writes here");
        tf2=  new JTextArea("Person B writes here");
        JFrame frame = new JFrame(title);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        p.add(tf1);
        p.add(tf2);
        frame.add(p);
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(50, 50);
        frame.setVisible(true);

    }
    protected void emitMessage(String text, String[] queues){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = text;
            for (String queue : queues ) {
                channel.basicPublish("", queue, null, message.getBytes(StandardCharsets.UTF_8));
                //System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (IOException | TimeoutException ioException) {
            ioException.printStackTrace();
        }
    }

    // defining the actionPerformed method
    //public abstract void actionPerformed(ActionEvent e) ;

}
