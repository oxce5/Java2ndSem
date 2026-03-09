import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CenterWindow extends JPanel {
  private LabeledField receiptIDInput;
  private LabeledField storeNameInput;
  private LabeledField totalCostInput;
  private LabeledField taxInput;
  private LabeledField finalAmount;

  private JButton recordButton;
  private JButton viewButton;
  private JButton sumButton;
  private JButton deleteButton;

  private LabeledField[] fieldsArray = {
    new LabeledField("Receipt ID"),
    new LabeledField("Store Name"),
    new LabeledField("Total Cost"),
    new LabeledField("Tax"),
    new LabeledField("Final Amount")

  };

  private JButton[] buttonsArray = {
    new JButton("Record"),
    new JButton("View"),
    new JButton("Sum"),
    new JButton("Delete")
  };

  public CenterWindow() {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 0.6;
    c.fill = GridBagConstraints.BOTH;
    FormsPanel forms = new FormsPanel(
      fieldsArray[0],
      fieldsArray[1],
      fieldsArray[2],
      fieldsArray[3],
      fieldsArray[4]
    );
    add(forms.getFormsPanel(), c);

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 0.4;
    c.fill = GridBagConstraints.BOTH;
    ButtonsPanel buttons = new ButtonsPanel(fieldsArray, buttonsArray);
    add(buttons.getButtonsPanel(), c);
  }
}
