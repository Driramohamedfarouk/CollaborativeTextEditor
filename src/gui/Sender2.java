package gui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static gui.Sender1.TO_FRIEND2;


public class Sender2 extends App {
    protected final static String TO_FRIEND= "to_friend";


    public Sender2() {
        super();
        //this.b1.setEnabled(false);
        //this.tf1.setEnabled(false);
        setTitle("Sender 2");
        this.b1.setLabel("receive");
        this.b1.addActionListener(this);
        this.tf1.setText("You are not allowed to write here");
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
        this.tf1.setText(" [*] Waiting for messages. Press the button to receive \n");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            tf1.append(" [x] Received '" + message + "' "+ "\n");
        };

        try {
            channel.basicConsume(TO_FRIEND2, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==this.b2){
        String[] my_queues = {QUEUE_NAME,TO_FRIEND} ;
        emitMessage("Person B : "+tf2.getText(),my_queues);
    }else if (source==this.b1) {
            receive(TO_FRIEND2);
        }
    }

    public static void main(String[] args) {
        new Sender2();
    }
}