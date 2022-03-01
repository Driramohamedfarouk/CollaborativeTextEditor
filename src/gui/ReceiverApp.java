package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.sound.midi.Receiver;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ReceiverApp extends Frame implements ActionListener {
    TextArea tf1;
    Button b1 ;
    private final static String QUEUE_NAME = "hello";


    ReceiverApp(){
        b1 = new Button("Receive");
        b1.addActionListener(this);
        tf1=  new TextArea("Person A did not write yet");
        // setting size, layout and visibility of frame
        setTitle("Collaborative Text Editor");
        setSize(800,600);
        setLayout(new GridLayout(2, 2));
        add(tf1);
        add(b1);
        pack();
        setVisible(true);
    }

   public void receive(){
       ConnectionFactory factory = new ConnectionFactory();
       factory.setHost("localhost");
       Connection connection = null;
       try {
           connection = factory.newConnection();
       } catch (IOException ioException) {
           ioException.printStackTrace();
       } catch (TimeoutException timeoutException) {
           timeoutException.printStackTrace();
       }
       Channel channel = null;
       try {
           channel = connection.createChannel();
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }

       try {
           channel.queueDeclare(QUEUE_NAME, false, false, false, null);
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }
       System.out.println(" [*] Waiting for messages. Press the button to receive");
       tf1.setText(" [*] Waiting for messages. Press the button to receive \n");
       DeliverCallback deliverCallback = (consumerTag, delivery) -> {
           String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
           System.out.println(" [x] Received '" + message + "'");
           tf1.append(" [x] Received '" + message + "'"+ "\n");

       };
       try {
           channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.receive();
    }


    public static void main(String[] args) {
        new ReceiverApp();
    }
}
