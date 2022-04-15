package text_editor.utils;

import text_editor.gui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;

import static text_editor.utils.BrokerUtils.emitMessage;
import static text_editor.utils.BrokerUtils.getConn;

public class GuiUtils {
    //public static long start_time ;
    public static long finish_time ;
    public static final long MAX_ELAPSED_TIME = 3000 ;
    static Timer timer = new Timer() ;

    public static void informUsers(JTextArea textArea,int textAreaID,App app) {
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                //label.setText(user_name);
                //BrokerUtils.emitMessage(user_name, queue_name);
                textArea.setBackground(Color.LIGHT_GRAY);
                try {
                    getConn().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                //emitMessage(String.valueOf(textAreaID),queue_name[textAreaID][]);
                app.set_up(textAreaID,app);

                //TODO : try this
                /*
                close the connection
                reEstablish the connection
                inform all users by a message so they can re set up their app with the new ID
                 */
                //app.set_up(textAreaID,app);
            }
        });
    }

    //TODO: keep only necessary params
    public static void add_typing_operations(JTextArea textArea,
                                             JLabel label,
                                             String[] my_queues,
                                             App app){
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //bean.setMessage(tf1.getText());
                //BrokerUtils.emitMessage(USER_NAME,USERS_QUEUE_2);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //bean.setMessage(tf1.getText());
                String username  = app.getUSER_NAME() ;
                int id = app.getId();
                app.getLabels().get(id).setText(username);
                textArea.setBackground(Color.LIGHT_GRAY);
                BrokerUtils.emitMessage(username, app.getUSER_NAMES_QUEUES()[id]);
            }

            //TODO : add sleep thant emit message : no one writes here->done

            @Override
            public void keyReleased(KeyEvent e) {
                emitMessage(textArea.getText(),my_queues);
                setFinish_time(System.currentTimeMillis()) ;
                synchronized (this){
                    timer.cancel();
                    //System.out.println("cancelled all scheduled work");
                    timer = new Timer();
                    timer.schedule(new MyTask(finish_time, app.getUSER_NAMES_QUEUES()[app.getId()], label,textArea),MAX_ELAPSED_TIME);
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
