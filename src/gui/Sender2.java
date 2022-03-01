package gui;

import java.awt.event.ActionEvent;

public class Sender2 extends App {
    public Sender2() {
        super();
        this.b1.setEnabled(false);
        this.tf1.setEnabled(false);
        this.tf1.setText("You are not allowed to write here");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        emitMessage("Person B : "+tf2.getText());
    }

    public static void main(String[] args) {
        new Sender2();
    }
}