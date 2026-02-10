import javax.swing.*;
import java.awt.*;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame parent = new JFrame("bvchidw");

      parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      parent.setLayout(new BorderLayout(10, 10));
      parent.setResizable(true);
      String[] titles = { "gaming", "lol", "buifewc", "ndind", "njkfsa" };
      String[] layouts = { BorderLayout.CENTER, BorderLayout.SOUTH, BorderLayout.EAST, BorderLayout.NORTH, BorderLayout.WEST};

      for (int i = 0; i < titles.length; i++) {
        parent.add(new JButton(titles[i]), layouts[i]);
      }

      parent.setSize(100, 100);
      parent.setLocationRelativeTo(null);
      parent.setVisible(true);
    });
  }
}
