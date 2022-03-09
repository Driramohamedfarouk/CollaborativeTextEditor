package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static gui.Sender1.TO_FRIEND2;


public class Sender2 extends App {
    protected final static String TO_FRIEND= "to_friend";
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND} ;

    public Sender2() {

        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );

        tf1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                receive(TO_FRIEND2);
            }
        });
        tf2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //bean.setMessage(tf1.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //bean.setMessage(tf1.getText());

            }

            @Override
            public void keyReleased(KeyEvent e) {
                emitMessage(tf2.getText(),my_queues);
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
        this.tf1.setText("no one wrote yet ");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            tf1.setText(message);
        };

        try {
            channel.basicConsume(TO_FRIEND2, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new Sender2();
    }
}