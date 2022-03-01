package gui;

import java.awt.event.ActionEvent;

public class Sender1 extends App {



    public Sender1() {
        super();
        this.b2.setEnabled(false);
        this.tf2.setEnabled(false);
        this.tf2.setText("You are not allowed to write here");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        emitMessage("PersonA : "+tf1.getText());
    }

    public static void main(String[] args) {
        new Sender1();
    }
}
