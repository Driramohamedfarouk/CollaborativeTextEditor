package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static gui.Sender1.TO_FRIEND2;


public class Sender2 extends App {
    protected final static String TO_FRIEND= "to_friend";
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND} ;

    public Sender2() {

        receive(TO_FRIEND2,Sender1.tf1);

        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );


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


    public static void main(String[] args) {
        new Sender2();
    }
}