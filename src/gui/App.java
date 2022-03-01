package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// Our class extends Frame class and implements ActionListener interface
public abstract class App extends Frame implements ActionListener {
    // creating instances of TextField and Button class
    TextArea tf1;
    TextArea tf2;
    Button b1, b2;
    protected final static String QUEUE_NAME = "to_receiver";
    // instantiating using constructor
    App() {
        b1= new Button("send");
        b2= new Button("send");
        b1.addActionListener(this);
        b2.addActionListener(this);
        tf1=  new TextArea("Person A writes here");
        tf2=  new TextArea("Person B writes here");

        // setting size, layout and visibility of frame
        setTitle("Collaborative Text Editor");
        setSize(800,600);
        setLayout(new GridLayout(2, 2));
        add(tf1);
        add(b1);
        add(tf2);
        add(b2);
        pack();
        setVisible(true);
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
    public abstract void actionPerformed(ActionEvent e) ;

}
