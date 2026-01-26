import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {
   static final int padding_x = 50;
   static final int padding_y = 50;
   static final int field_w = 188;
   static final int field_h = 27;
   static final int button_w = 94;
   static final int button_h = 24;

  public static void main(String[] args) {
    new Main();
  }

  Main() {
    JLabel type = new JLabel("Customer Type");
    JLabel consumption = new JLabel("Consumption");
    JLabel bill = new JLabel("Total Bill");

    add(type).setBounds(padding_x, 50, 150, 50);
    add(consumption).setBounds(padding_x, 50 + padding_x * 1, 150, 50);
    add(bill).setBounds(padding_x, 50 + padding_x * 2, 150, 50);

    JTextField typeArea = new JTextField();
    JTextField consumptionArea = new JTextField();
    JTextField billArea = new JTextField();

    add(typeArea).setBounds(padding_x + 150, 65, field_w, field_h);
    add(consumptionArea).setBounds(padding_x + 150, 65 + padding_x * 1, field_w, field_h);
    add(billArea).setBounds(padding_x + 150, 65 + padding_x * 2, field_w, field_h);

    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");

    add(add).setBounds(padding_x, 65 + padding_x * 3, button_w, button_h);
    add(update).setBounds(padding_x + 60 * 2, 65 + padding_x * 3, button_w, button_h);
    add(delete).setBounds(padding_x + 60 * 4, 65 + padding_x * 3, button_w, button_h);

    setLayout(null);
    setSize(750, 500);
    setResizable(true);
    setVisible(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Vehicle Rental System");
  }
}
