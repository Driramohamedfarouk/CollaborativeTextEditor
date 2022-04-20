package text_editor.gui;

import text_editor.utils.GuiUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static text_editor.utils.BrokerUtils.receive;

//TODO : model all queues and remove unnecessary ones -> done
//TODO : make an observable on the labels to avoid using queues ->done
//TODO : consider OOP strong encapsulation principles -> done
public class App  {
    private static final int N =3   ;
    ArrayList<JTextArea> textAreas ;
    ArrayList<JLabel> labels ;
    int id  ;

    private static final String[][] SERVER_EMIT_QUEUE = new String[N][N];


    final String title = "Collaborative Text Editor";
    public JFrame frame = new JFrame(title);

    public String USER_NAME ;

    App() {
        textAreas = new ArrayList<JTextArea>();
        labels = new ArrayList<JLabel>();
        JPanel p = new JPanel();
        JPanel rp = new JPanel();
        rp.setSize(100,600);
        JPanel lp = new JPanel();
        p.setLayout(new GridLayout(1,2));
        rp.setLayout(new GridLayout(N,1));
        lp.setLayout(new GridLayout(N,1));
        for(int i =0 ; i<N; i++){
            textAreas.add(new JTextArea("click here to start writing")) ;
            labels.add(new JLabel("No one is writing here"));
            lp.add(textAreas.get(i));
            rp.add(labels.get(i));
            for (int j =0 ; j<N; j++){
                SERVER_EMIT_QUEUE[i][j]="user_"+(i+1)+"_area_"+(j+1) ;
            }
        }
        p.add(rp); p.add(lp);
        frame.add(p);
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(50, 50);
        frame.setVisible(true);
    }

    public void set_up(int id , App app){
        for(int i=0;i<getN();i++){
                receive(SERVER_EMIT_QUEUE[id][i],textAreas.get(i),labels.get(i),i,app);
                GuiUtils.add_typing_operations(textAreas.get(i),i,labels.get(i),app);
        }
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }
    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }


    public static int getN() {
        return N;
    }


    public ArrayList<JTextArea> getTextAreas() {
        return textAreas;
    }

    public void setTextAreas(ArrayList<JTextArea> textAreas) {
        this.textAreas = textAreas;
    }

    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static String[][] getSERVER_EMIT_QUEUE() {
        return SERVER_EMIT_QUEUE;
    }
}
