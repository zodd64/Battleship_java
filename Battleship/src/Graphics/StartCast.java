package Graphics;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class StartCast {

    private JFrame frame;
    private JOptionPane start;
    private JLabel label;

    /**
     * creates an instance of the StartCast class which in turn creates and shows a message that informs which player goes first
     * @param playername 
     */
    public StartCast(String playername) {

        label = new JLabel(playername + " play's first", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        start.showMessageDialog(frame,
                label,
                "Game Starts",
                start.PLAIN_MESSAGE);
    }
}
