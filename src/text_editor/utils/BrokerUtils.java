package text_editor.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import text_editor.gui.App;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;


public class BrokerUtils {
    private static final String QUEUE_NAME = "server_queue";
    private static Connection conn = null ;

    public static void emitMessage(String text, String[] queues){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //String message = text;
            for (String queue : queues ) {
                channel.basicPublish("", queue, null, text.getBytes(StandardCharsets.UTF_8));
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
        setConn(connection);
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
    public static void receive(String queue_name, JTextArea textArea,JLabel label ,int textAreaID,App app){

        Channel channel = establish_connection(queue_name);
        //tf2.setText(" [*] Waiting for messages. Press the button to receive \n");
        textArea.setText("No one wrote yet");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //System.out.println(" [x] Received '" + message + "'");
            String[] arrOfStr = message.split(":", 2);
            int senderId = Integer.parseInt(arrOfStr[0].trim()) +1 ;
            if(senderId==1000){
                textArea.setBackground(Color.WHITE);
                label.setText(arrOfStr[1]);
            }else {
                label.setText("user " + senderId);
                textArea.setText(arrOfStr[1]);
                textArea.setBackground(Color.LIGHT_GRAY);
            }
            /*if (message.contains("user")){
                textArea.setBackground(Color.LIGHT_GRAY);
            }else{

            }*/
        };

        try {
            channel.basicConsume(queue_name, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static  void receive(String queue_name,JLabel label,JTextArea textArea){
        Channel channel = establish_connection(queue_name);
        //tf2.setText(" [*] Waiting for messages. Press the button to receive \n");
        label.setText("No one is writing here");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //System.out.println(" [x] Received '" + message + "'");
            label.setText(message);
            if (message.contains("user")){
                textArea.setBackground(Color.LIGHT_GRAY);
            }else{
                textArea.setBackground(Color.WHITE);
            }
        };
        try {
            channel.basicConsume(queue_name, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static  void receive(String queue_name){
        Channel channel = establish_connection(queue_name);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] arrOfStr = message.split(":", 3);
            int id = Integer.parseInt(arrOfStr[0].trim());
            int from_textArea = Integer.parseInt(arrOfStr[1].trim()) ;
            //System.out.println("user "+id+" wrote : '"+arrOfStr[2]+"' in area "+from_textArea);
             for(int i=0;i<App.getN();i++){
                if(i!=id){
                    emitMessage(arrOfStr[0]+":"+arrOfStr[2],App.getSERVER_EMIT_QUEUE()[i][from_textArea]);
                }
            }
        };
        try {
            channel.basicConsume(queue_name, true, deliverCallback, consumerTag -> { });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        BrokerUtils.conn = conn;
    }
}
