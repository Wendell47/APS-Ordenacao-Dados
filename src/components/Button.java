package components;

import javax.swing.JButton;

public class Button extends JButton {
    public Button(String title){
        super(title);
        setFont(this.getFont().deriveFont(20f));
    }
}
