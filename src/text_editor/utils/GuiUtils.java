package text_editor.utils;

import text_editor.gui.App;

import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;

import static text_editor.utils.BrokerUtils.emitMessage;

public class GuiUtils {
    //public static long start_time ;
    public static long finish_time ;
    public static final long MAX_ELAPSED_TIME = 3000 ;
    static Timer timer = new Timer() ;

    public static void informUsers(JTextArea textArea,JLabel label ,String user_name, String queue_name){
        /*textArea.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                label.setText(user_name);
                BrokerUtils.emitMessage(user_name,queue_name);
            }
        });*/
    }

    public static void add_typing_operations(JTextArea textArea,
                                             JLabel label,
                                             String[] my_queues,
                                             String name_queue,
                                             String user_name,
                                             App how){
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //bean.setMessage(tf1.getText());
                //BrokerUtils.emitMessage(USER_NAME,USERS_QUEUE_2);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //bean.setMessage(tf1.getText());
                switch (user_name){
                    case "user 1" :
                        how.setLabel1(user_name);
                    case "user 2" :
                        how.setLabel2(user_name);
                    default:
                        break;
                }
                BrokerUtils.emitMessage(user_name,name_queue);
                //start_time = System.currentTimeMillis() ;
                //System.out.println("pressed at "+start_time);
            }

            //TODO : add sleep thant emit message : no one writes here

            @Override
            public void keyReleased(KeyEvent e) {
                emitMessage(textArea.getText(),my_queues);
                setFinish_time(System.currentTimeMillis()) ;
                synchronized (this){
                    timer.cancel();
                    //System.out.println("cancelled all scheduled work");
                    timer = new Timer();
                    timer.schedule(new MyTask(finish_time,name_queue,label),MAX_ELAPSED_TIME);
                }
            }
        });

    }

    public static long getFinish_time() {
        return finish_time;
    }

    public static void setFinish_time(long finish_time) {
        GuiUtils.finish_time = finish_time;
    }
}
