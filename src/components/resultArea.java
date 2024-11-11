package components;

import java.awt.Font;

import javax.swing.JTextArea;

public class resultArea extends JTextArea {

    public resultArea(){
        setFont(new Font("Monospaced", Font.PLAIN, 20));
        setEditable(false);
    }
}