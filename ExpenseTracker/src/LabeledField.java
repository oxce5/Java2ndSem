import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledField extends JPanel {
  public JTextField textInput = new JTextField(20);

  LabeledField(String label) {
    this(label, 10);
  }

  LabeledField(String label, int padding) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JLabel labelText = new JLabel(label);
    labelText.setAlignmentX(Component.LEFT_ALIGNMENT);
    textInput.setAlignmentX(Component.LEFT_ALIGNMENT);
    textInput.setMaximumSize(textInput.getPreferredSize());
    add(labelText);
    add(Box.createVerticalStrut(padding));
    add(textInput);
  }

  public String getText() {
    return textInput.getText();
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    textInput.setText(text);
  }

  public void enabled(boolean enabled) {
    textInput.setEnabled(enabled);
  }

  public void editable(boolean enabled) {
    textInput.setEditable(enabled);
  }

  public void addActionListener(ActionListener listener) {
    textInput.addActionListener(e -> listener.actionPerformed(
      new java.awt.event.ActionEvent(
        this,
        e.getID(), 
        e.getActionCommand()
    )));
  }
}
