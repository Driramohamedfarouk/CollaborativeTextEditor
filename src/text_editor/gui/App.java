package text_editor.gui;

import text_editor.utils.GuiUtils;
import text_editor.utils.MessageBean;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static text_editor.utils.BrokerUtils.receive;

//TODO : model all queues and remove unnecessary ones -> done
//TODO : make an observable on the labels to avoid using queues ->done
//TODO : consider OOP strong encapsulation principles
public class App  {
    private static int N =3   ;
    ArrayList<JTextArea> textAreas ;
    ArrayList<JLabel> labels ;
    String[][] FROM_TO = new String[N][N];
    String[][] USER_NAMES_QUEUES  = new String[N][N] ;
    int id  ;

    final String title = "Collaborative Text Editor";
    public JFrame frame = new JFrame(title);

    public String USER_NAME ;
    public String USERS_NAME_QUEUE ;

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
            textAreas.add(new JTextArea("Person"+i+1 +"writes here")) ;
            labels.add(new JLabel("No one is writing here"));
            lp.add(textAreas.get(i));
            rp.add(labels.get(i));
            for (int j =0 ; j<N; j++){
                FROM_TO[i][j]="from_"+(i+1)+"_to_"+(j+1) ;
                USER_NAMES_QUEUES[i][j]= "users_queue_"+(i+1)+"inform"+(j+1) ;
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
            if(i!=id){
                receive(FROM_TO[i][id],textAreas.get(i));
                receive(USER_NAMES_QUEUES[i][id],labels.get(i));
            }
        }
        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(m ->
                textAreas.get(id).setText((String) m.getNewValue())
        );
        GuiUtils.add_typing_operations(textAreas.get(id),labels.get(id),FROM_TO[id],app);
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }
    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUsersNameQueue() { return this.USERS_NAME_QUEUE; }
    public void setUsersNameQueue(String usersNameQueue) {
        this.USERS_NAME_QUEUE = usersNameQueue;
    }

    public static int getN() {
        return N;
    }

    public static void setN(int n) {
        N = n;
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

    public void setLabels(ArrayList<JLabel> labels) {
        this.labels = labels;
    }

    public String[][] getUSER_NAMES_QUEUES() {
        return USER_NAMES_QUEUES;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
