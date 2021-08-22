package Graphics;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class EndCast {

    private JFrame frame;
    private JOptionPane end;
    private JLabel label;
/**
 * creates an instance of EndCast
 * pops up a message with the winners name
 * @param playername 
 */
    public EndCast(String playername) {

        label = new JLabel(playername + " is the winner!!!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        end.showMessageDialog(frame,
                label,
                "Game Ended",
                end.PLAIN_MESSAGE);
    }
}
