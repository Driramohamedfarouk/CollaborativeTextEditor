package text_editor.gui;

import text_editor.utils.GuiUtils;
import text_editor.utils.MessageBean;

import static text_editor.gui.Sender2.TO_FRIEND;
import static text_editor.utils.BrokerUtils.emitMessage;
import static text_editor.utils.BrokerUtils.receive;

public class Sender1 extends App {

    protected final static String TO_FRIEND2 = "to_friend2";
    private final static  String USER_NAME = "user 1";
    private static final String USERS_QUEUE_1 = "users_queue_1" ;
    private static final String USERS_QUEUE_2 = "users_queue_2" ;
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND2} ;
    public Sender1() {
        frame.setLocation(50,50);
        receive(TO_FRIEND, tf2);
        //receive(USERS_QUEUE_1,label1);
        receive(USERS_QUEUE_2,label2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );
        //bean.addPropertyChangeListener(e->label2.setText((String) e.getNewValue()));
        //GuiUtils.informUsers(tf2,label2,USER_NAME,USERS_QUEUE_2);
        //GuiUtils.informUsers(tf1,label1,USER_NAME,USERS_QUEUE_1);
        GuiUtils.add_typing_operations(tf1,label1,my_queues,USERS_QUEUE_1,USER_NAME,this);
    }

    public static void main(String[] args) {
        new Sender1();
    }
}
