package text_editor.gui;

public class Sender1 extends App {


    public Sender1() {
        frame.setLocation(10,10);
        this.setUSER_NAME("user 1");
        this.setId(0);
        set_up(id ,this);
    }

    public static void main(String[] args) {
        new Sender1();
    }
}
