package text_editor.gui;

import text_editor.utils.GuiUtils;
import text_editor.utils.MessageBean;
import text_editor.utils.MyTask;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import static text_editor.utils.BrokerUtils.emitMessage;
import static text_editor.utils.BrokerUtils.receive;

public class Sender2 extends App {
    protected final static String TO_FRIEND= "to_friend";
    private final static  String USER_NAME = "user 2";
    private static final String USERS_QUEUE_1 = "users_queue_1" ;
    private static final String USERS_QUEUE_2 = "users_queue_2" ;
    private String[] my_queues = {QUEUE_NAME,TO_FRIEND} ;
    //private BrokerUtils brokerUtils = new BrokerUtils();
    public Sender2() {
        frame.setLocation(950,50);
        receive(Sender1.TO_FRIEND2, tf1);
        receive(USERS_QUEUE_1,label1);
        //receive(USERS_QUEUE_2,label2);
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->
                tf2.setText((String) e.getNewValue())
        );
        //bean.addPropertyChangeListener(e->label1.setText((String) e.getNewValue()));
        //GuiUtils.informUsers(tf2,label2,USER_NAME,USERS_QUEUE_2);
        //GuiUtils.informUsers(tf1,label1,USER_NAME,USERS_QUEUE_1);
        GuiUtils.add_typing_operations(tf2,label2,my_queues,USERS_QUEUE_2,USER_NAME,this);

    }


    public static void main(String[] args) {
        new Sender2();
    }
}