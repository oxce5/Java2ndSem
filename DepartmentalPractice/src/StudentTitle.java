import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentTitle extends JPanel {
  StudentTitle() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JLabel title = new JLabel("Student");
    JLabel subtitle = new JLabel("Enrollment System");
    add(title);
    add(Box.createVerticalStrut(10));
    add(subtitle);
  }
}

