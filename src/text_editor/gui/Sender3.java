package text_editor.gui;

public class Sender3 extends App {

    private final int id = 2;

    public Sender3() {
        frame.setLocation(950,1000);
        this.setUSER_NAME("user 3");
        this.setId(2);
        setUsersNameQueue("users_queue_3");
        set_up(id , this);
    }

    public static void main(String[] args) {
        new Sender3();
    }
}
