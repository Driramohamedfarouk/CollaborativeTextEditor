package text_editor.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.TimerTask;

import static text_editor.utils.BrokerUtils.emitMessage;

public class MyTask extends TimerTask {
    private long last_pressed_time ;
    private String[] queue_names ;
    private JLabel label ;
    private JTextArea textArea ;

    public MyTask(long last_pressed_time, String[] queue_names, JLabel label,JTextArea textArea) {
        this.last_pressed_time = last_pressed_time;
        this.queue_names = queue_names;
        this.label = label;
        this.textArea = textArea ;
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("Inside run");
            if (System.currentTimeMillis() - GuiUtils.getFinish_time() > 0) {
                label.setText("No one is writing here");
                textArea.setBackground(Color.WHITE);
                emitMessage("No one is writing here", queue_names);
            } else {
                System.out.println("I tested no ");
            }
        }
    }

    public String[] getQueue_names() {
        return queue_names;
    }

    public void setQueue_names(String[] queue_names) {
        this.queue_names = queue_names;
    }
}