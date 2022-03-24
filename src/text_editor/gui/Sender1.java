package text_editor.gui;

import text_editor.utils.GuiUtils;
import text_editor.utils.MessageBean;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static text_editor.utils.BrokerUtils.receive;

public class Sender1 extends App {


    private String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
    public Sender1() {

        frame.setLocation(50,50);
        this.setUSER_NAME("user 1");
        setUsersNameQueue("users_queue_1");
       /* tf1.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        tf2.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });*/
        receive(TO_FRIEND, tf2);
        receive(USERS_QUEUE_2,label2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(m ->
                tf2.setText((String) m.getNewValue())
        );
        GuiUtils.add_typing_operations(tf1,label1,my_queues,Sender1.this);

    }

    public String getToFriend2() {
        return TO_FRIEND2;
    }

    public String getUserName() {
        return this.USER_NAME;
    }



    public String getUsersQueue2() {
        return USERS_QUEUE_2;
    }

    public String[] getMy_queues() {
        return my_queues;
    }

    public void setMy_queues(String[] my_queues) {
        this.my_queues = my_queues;
    }

    public static void main(String[] args) {
        new Sender1();
    }
}
