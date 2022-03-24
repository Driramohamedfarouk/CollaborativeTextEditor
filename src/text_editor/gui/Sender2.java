package text_editor.gui;

import text_editor.utils.GuiUtils;
import text_editor.utils.MessageBean;

import static text_editor.utils.BrokerUtils.receive;

public class Sender2 extends App {
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND} ;
    public Sender2() {
        frame.setLocation(950,50);
        this.setUSER_NAME("user 2");
        setUsersNameQueue("users_queue_2");
        receive(Sender1.TO_FRIEND2, tf1);
        receive(USERS_QUEUE_1,label1);
        //receive(USERS_QUEUE_2,label2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );

        GuiUtils.add_typing_operations(tf2,label2,my_queues,this);

    }


    public static void main(String[] args) {
        new Sender2();
    }
}