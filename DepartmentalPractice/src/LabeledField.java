import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledField extends JPanel {
  private JLabel label;
  private JTextField textField;


  LabeledField(String labelText, int COLUMNS) {
    setLayout(new GridBagLayout());
    label = new JLabel(labelText);
    textField = new JTextField(COLUMNS);
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
    gbc.anchor = GridBagConstraints.WEST; 
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets = new java.awt.Insets(0, 0, 2, 0);
    add(label, gbc);

    gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new java.awt.Insets(0, 0, 0, 0); 
    add(textField, gbc);
  }

  LabeledField(String labelText) {
    this(labelText, 10);
  }

  public String getText() {
    return textField.getText();
  }

  public void setText(String text) {
    textField.setText(text);
  }
}
