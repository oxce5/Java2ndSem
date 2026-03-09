import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FormsPanel {
  JPanel forms;

  FormsPanel(
    LabeledField receiptIDInput,
    LabeledField storeNameInput,
    LabeledField totalCostInput,
    LabeledField taxInput,
    LabeledField finalAmount
  ) {
    forms = new JPanel();
    forms.setLayout(new BoxLayout(forms, BoxLayout.Y_AXIS));

    forms.add(storeNameInput);
    forms.add(Box.createVerticalStrut(20));
    forms.add(totalCostInput);
    forms.add(Box.createVerticalStrut(20));
    forms.add(taxInput);
    forms.add(Box.createVerticalStrut(20));
    forms.add(finalAmount);
    forms.add(Box.createVerticalStrut(20));

    taxInput.setText("12%");
    taxInput.enabled(false);
    finalAmount.enabled(false);
  }

  public JPanel getFormsPanel() {
    return this.forms;
  }
}
