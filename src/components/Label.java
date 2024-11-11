package components;
import javax.swing.JLabel;


public class Label extends JLabel{
  
    public Label(String title) {
        super(title);
        setFont(this.getFont().deriveFont(20f));
    }
}
