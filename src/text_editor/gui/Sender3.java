package text_editor.gui;

public class Sender3 extends App {

    public Sender3() {
        frame.setLocation(950,600);
        this.setUSER_NAME("user 3");
        this.setId(2);
        set_up(id , this);
    }

    public static void main(String[] args) {
        new Sender3();
    }
}
