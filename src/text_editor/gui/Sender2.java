package text_editor.gui;

public class Sender2 extends App {
    private int id =1;
    public Sender2() {
        frame.setLocation(950,50);
        this.setUSER_NAME("user 2");
        this.setId(1);
        SingletonSender.getInstance().getMy_app().add(this);
        setUsersNameQueue("users_queue_2");
        set_up(id,this);
    }


    public static void main(String[] args) {
        new Sender2();
    }
}