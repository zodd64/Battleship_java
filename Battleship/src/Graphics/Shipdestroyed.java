package graphics;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Shipdestroyed {

    private JFrame frame;
    private JOptionPane ship;
    private JLabel label;

    /**
     * creates an instance of the Ship destroyed class which creates and shows a message about a player's specific ship being destroyed
     * @param playername
     * @param shipname 
     */
    public Shipdestroyed(String playername, String shipname) {

        label = new JLabel(playername + "'s " + shipname + " has been destroyed!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        ship.showMessageDialog(frame,
                label,
                "Ship Destroyed",
                ship.PLAIN_MESSAGE);
    }
}
