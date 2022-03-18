package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gui.BrokerUtils.emitMessage;
import static gui.BrokerUtils.receive;
import static gui.Sender2.TO_FRIEND;

public class Sender1 extends App {

    protected final static String TO_FRIEND2 = "to_friend2";
    private final static  String USER_NAME = "user 1";
    private static final String USERS_QUEUE_1 = "users_queue_1" ;
    private static final String USERS_QUEUE_2 = "users_queue_2" ;
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
    public Sender1() {

        frame.setLocation(50,50);
        receive(TO_FRIEND,Sender2.tf2);
        receive(USERS_QUEUE_1,label1);
        receive(USERS_QUEUE_2,label2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );
        tf1.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                label1.setText(USER_NAME);
                emitMessage(USER_NAME,USERS_QUEUE_1);
            }
        });
        tf2.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                label2.setText(USER_NAME);
                emitMessage(USER_NAME,USERS_QUEUE_2);
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

    public static void main(String[] args) {
        new Sender1();
    }
}
