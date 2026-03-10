import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StudentButtons extends JPanel {
  private StudentListener listener;

  StudentButtons() {
    add(createButton("Register", 
      e -> {
        if (listener != null) listener.onAdd();
      })
    );
    add(createButton("Update", 
      e -> {
        if (listener != null) listener.onUpdate();
      })
    );
    add(createButton("Delete", 
      e -> {
        if (listener != null) listener.onDelete();
      })
    );
  }

  private JButton createButton(String buttonText, ActionListener action) {
    JButton button = new JButton(buttonText);
    button.addActionListener(action);
    return button;
  }

  public void setStudentActionListener(StudentListener listener) {
      this.listener = listener;
  }
}

