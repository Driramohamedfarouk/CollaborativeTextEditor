package text_editor.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static text_editor.gui.App.QUEUE_NAME;

public class BrokerUtils {
    public static void emitMessage(String text, String[] queues){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
             /*
             * why QUEUE_NAME ?
             * */
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

    public static void  emitMessage(String text, String queues)  {
        emitMessage(text, new String[] {queues});
    }

    public static Channel establish_connection(String queue_name){
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
        System.out.println(" [*] Waiting for messages from :"+queue_name);
        return  channel ;
    }

    // defining the actionPerformed method
    //public abstract void actionPerformed(ActionEvent e) ;
    public static void receive(String queue_name, JTextArea textArea){

        Channel channel = establish_connection(queue_name);
        //tf2.setText(" [*] Waiting for messages. Press the button to receive \n");
        textArea.setText("No one wrote yet");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //System.out.println(" [x] Received '" + message + "'");
            textArea.setText(message);
        };

        try {
            channel.basicConsume(queue_name, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static  void receive(String queue_name,JLabel label){
        Channel channel = establish_connection(queue_name);
        //tf2.setText(" [*] Waiting for messages. Press the button to receive \n");
        label.setText("No one is writing here");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //System.out.println(" [x] Received '" + message + "'");
            label.setText(message);
        };

        try {
            channel.basicConsume(queue_name, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
