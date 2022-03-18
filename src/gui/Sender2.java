package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gui.BrokerUtils.emitMessage;
import static gui.BrokerUtils.receive;
import static gui.Sender1.TO_FRIEND2;
import gui.BrokerUtils.* ;

public class Sender2 extends App {
    protected final static String TO_FRIEND= "to_friend";
    private final static  String USER_NAME = "user 2";
    private static final String USERS_QUEUE_1 = "users_queue_1" ;
    private static final String USERS_QUEUE_2 = "users_queue_2" ;
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND} ;
    //private BrokerUtils brokerUtils = new BrokerUtils();
    public Sender2() {

        frame.setLocation(950,50);
        receive(TO_FRIEND2,Sender1.tf1);
        receive(USERS_QUEUE_1,label1);
        receive(USERS_QUEUE_2,label2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );
        tf2.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                label2.setText(USER_NAME);
                emitMessage(USER_NAME,USERS_QUEUE_2);
            }
        });
        tf1.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                label1.setText(USER_NAME);
                emitMessage(USER_NAME,USERS_QUEUE_1);
            }
        });

        tf2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //bean.setMessage(tf1.getText());
                emitMessage(USER_NAME,USERS_QUEUE_2);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //bean.setMessage(tf1.getText());
                emitMessage(USER_NAME,USERS_QUEUE_2);
            }

            //TODO : add sleep thant emit message : no one writes here
            @Override
            public synchronized void keyReleased(KeyEvent e) {
                emitMessage(tf2.getText(),my_queues);
                // TODO : find a solution for this wait
                /*for(int i = 0xFFFFFF;i>0;i--);
                emitMessage("No one is writing here",USERS_QUEUE_2);*/
            }
        });
    }


    public static void main(String[] args) {
        new Sender2();
    }
}