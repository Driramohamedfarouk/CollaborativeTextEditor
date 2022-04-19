package text_editor.gui;

public class Sender1 extends App {


    public Sender1() {
        frame.setLocation(50,50);
        this.setUSER_NAME("user 1");
        this.setId(0);
        setUsersNameQueue("users_queue_1");
        set_up(id ,this);
    }

    public static void main(String[] args) {
        new Sender1();
    }
}
