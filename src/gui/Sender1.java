package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static gui.Sender2.TO_FRIEND;

public class Sender1 extends App {

    protected final static String TO_FRIEND2 = "to_friend2";

    public Sender1() {
        super();
        setTitle("Sender1");
        //this.b2.setEnabled(false);
        //this.tf2.setEnabled(false);
        this.tf2.setText("You are not allowed to write here");
        this.b2.setLabel("receive");
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
            System.out.println(" [x] Received '" + message + "'");
            tf2.append(" [x] Received '" + message + "'"+ "\n");
        };

        try {
            channel.basicConsume(TO_FRIEND, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==this.b1){
            String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
            emitMessage("Person A : "+tf1.getText(),my_queues);
        }else if (source==this.b2) {
            receive(TO_FRIEND);
        }
    }

    public static void main(String[] args) {
        new Sender1();
    }
}
