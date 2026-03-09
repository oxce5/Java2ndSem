import java.awt.BorderLayout;

import javax.swing.JFrame;

public class RootWindow {
  RootWindow() {
    JFrame root = new JFrame("Expenses Tracker");
    root.add(new CenterWindow(), BorderLayout.CENTER);
    root.pack();
    root.revalidate();
    root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    root.setSize(800,600);
    root.setLocationRelativeTo(null);
    root.setVisible(true);
  }
}
