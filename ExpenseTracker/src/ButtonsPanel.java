import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel {
  private JPanel buttons;

  ButtonsPanel(
    LabeledField[] fieldsArray,
    JButton[] buttonsArray
  ) {
    ArrayList<Expense> expenses = new FileIO().ReadFile(Path.of("expenses.txt"));
    buttons = new JPanel();

    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

    for (JButton button : buttonsArray) {
      buttons.add(button);
      buttons.add(Box.createVerticalStrut(20));
    }

    new Controllers(
      fieldsArray,
      buttonsArray,
      expenses
    );
  }

  public JPanel getButtonsPanel() {
    return this.buttons;
  }
}
