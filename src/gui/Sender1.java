package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;


import java.awt.event.KeyEvent;

import static gui.Sender2.TO_FRIEND;

public class Sender1 extends App {

    protected final static String TO_FRIEND2 = "to_friend2";
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
    public Sender1() {

        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );
        tf2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                receive(TO_FRIEND);
            }
        });
        tf1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
               // bean.setMessage(tf2.getText());

            }

            @Override
            public void keyReleased(KeyEvent e) {
                emitMessage(tf1.getText(),my_queues);
            }
        });



    }

    public void receive(String queue_name){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException ioException) {
            ioException.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            channel.queueDeclare(queue_name, false, false, false, null);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println(" [*] Waiting for messages. Press the button to receive");
        tf2.setText(" [*] Waiting for messages. Press the button to receive \n");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //System.out.println(" [x] Received '" + message + "'");
            tf2.setText(message);
        };

        try {
            channel.basicConsume(TO_FRIEND, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

/*    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==this.b1){
            String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
            emitMessage("Person A : "+tf1.getText(),my_queues);
        }else if (source==this.b2) {
            receive(TO_FRIEND);
        }
    }*/

    public static void main(String[] args) {
        new Sender1();
    }
}
