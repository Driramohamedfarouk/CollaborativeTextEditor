package text_editor.utils;

import text_editor.gui.App;
import text_editor.gui.SingletonServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

import static text_editor.utils.BrokerUtils.emitMessage;

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
                //textArea.setBackground(Color.LIGHT_GRAY);
                //app.setId(textAreaID);
                //emitMessage(String.valueOf(textAreaID),queue_name[textAreaID][]);
                //app.set_up(textAreaID,app);

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
                                             int textAreaID ,
                                             JLabel label,
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
                if(app.getLabels().get(textAreaID).getText().equals("No one is writing here") ||
                        app.getLabels().get(textAreaID).getText().equals(app.getUSER_NAME())
                ) {
                    String username = app.getUSER_NAME();
                    app.getLabels().get(textAreaID).setText(username);
                    textArea.setBackground(Color.LIGHT_GRAY);
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),
                            app.getLabels().get(textAreaID).getText()+
                                    " is writing here now , Please wait until he finishes!",
                            "Warning !!!", JOptionPane.ERROR_MESSAGE);
                }
                //BrokerUtils.emitMessage(username, app.getUSER_NAMES_QUEUES()[id]);
            }

            //TODO : add sleep thant emit message : no one writes here->done


            //TODO: needs to be synchronized
            @Override
            public void keyReleased(KeyEvent e) {
                emitMessage(app.getId()+":"+textAreaID+":"+textArea.getText(), SingletonServer.getInstance().getServerQueue());
                setFinish_time(System.currentTimeMillis()) ;
                synchronized (this){
                    timer.cancel();
                    //System.out.println("cancelled all scheduled work");
                    timer = new Timer();
                    timer.schedule(new MyTask(finish_time, SingletonServer.getInstance().getServerQueue(), textAreaID ,label,textArea),MAX_ELAPSED_TIME);
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
