import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiUtils {
  public static JTextField createLabeledField(String field, JPanel parent, int maxChar) {
    JPanel labeledField = new JPanel();
    labeledField.setLayout(new BoxLayout(labeledField, BoxLayout.Y_AXIS));
    labeledField.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel label = new JLabel(field);
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    labeledField.add(label);
    labeledField.add(Box.createVerticalStrut(10));

    JTextField input = new JTextField(maxChar);
    input.setAlignmentX(Component.LEFT_ALIGNMENT);
    input.setMaximumSize(new Dimension(Integer.MAX_VALUE, input.getPreferredSize().height));
    labeledField.add(input);

    parent.add(labeledField);

    return input;
  }
}
