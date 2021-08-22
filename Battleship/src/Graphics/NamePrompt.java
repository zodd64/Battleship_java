package Graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NamePrompt {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField userName;
    private JButton loginButton;

    /**
     * creates an instance of the NamePrompt class which creates the NamePrompt GUI
     */
    public NamePrompt() {

        frame = new JFrame("Name");
        frame.setSize(350, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        label = new JLabel("Enter your name and press \"Start\"");
        label.setBounds(10, 20, 80, 25);

        userName = new JTextField(15);
        userName.setBounds(10, 20, 80, 25);

        loginButton = new JButton("Start");
        loginButton.setBounds(10, 80, 80, 25);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(label, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(userName, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(loginButton, c);

        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    public JFrame GetFrame() {
        return frame;
    }

    public JButton GetLogin() {
        return loginButton;
    }

    public JTextField GetField() {
        return userName;
    }
    
    
    
}
