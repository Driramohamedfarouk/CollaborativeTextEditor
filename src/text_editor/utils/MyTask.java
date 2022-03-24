package text_editor.utils;

import javax.swing.*;
import java.util.Date;
import java.util.TimerTask;

import static text_editor.utils.BrokerUtils.emitMessage;

public class MyTask extends TimerTask {
    private long last_pressed_time ;
    private String queue_name ;
    private JLabel label ;

    public MyTask(long last_pressed_time, String queue_name, JLabel label) {
        this.last_pressed_time = last_pressed_time;
        this.queue_name = queue_name;
        this.label = label;
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("Inside run");
            if (System.currentTimeMillis() - GuiUtils.getFinish_time() > 0) {
                label.setText("No one is writing here");
                emitMessage("No one is writing here", queue_name);
            } else {
                System.out.println("I tested no ");
            }
        }
    }

    public void setLast_pressed_time(long last_pressed_time) {
        this.last_pressed_time = last_pressed_time;
    }

    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }
}