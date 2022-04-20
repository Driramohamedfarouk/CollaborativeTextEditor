package text_editor.gui;

public class Sender2 extends App {
    public Sender2() {
        frame.setLocation(910,10);
        this.setUSER_NAME("user 2");
        this.setId(1);
        set_up(id,this);
    }


    public static void main(String[] args) {
        new Sender2();
    }
}