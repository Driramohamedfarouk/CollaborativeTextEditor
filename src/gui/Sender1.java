package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static gui.Sender2.TO_FRIEND;

public class Sender1 extends App {

    protected final static String TO_FRIEND2 = "to_friend2";
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
    public Sender1() {

        receive(TO_FRIEND,Sender2.tf2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );
      /*  tf2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });*/
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
