package Graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

public class Information {

    private JFrame frame;
    private JLabel inform;
    private JButton button;

    /**
     * creates an instance of Information page explaining the rules and the way the mechanics of the game works
     */
    public Information() {

        frame = new JFrame("Information");
        frame.setLayout(new GridBagLayout());
        inform = new JLabel("<html>The player gets 2 grids. One is used for his own ships and one to record the shots to the oppenent.<br> <br>"
                + "There are 5 types of ships of various sizes. You get one of each kind.<br>"
                + "Before the game starts the player secretly arrange his ships on his grid horizontaly or vertically without them overlapping.<br>"
                + "You can reposition the ships by pressing the ship button again after its placed to return it to its original spot.<br><br>"
                + "After the ships has been positioned, the player and cpu take rounds targeting a square in the opponents grid,<br>"
                + "If a target is found the enemy grid turns red on that particular spot,if not the spot turns white <br>"
                + "The same applies for the players grid if the cpu find a target on your grid<br><br>"
                + "Every time you sink an enemy ship its announced on the screen<br>"
                + "The game is won when someone sinks all 5 of the opponents ships</html>");

        button = new JButton("Go Back");

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 1;
        frame.add(inform, c);

        c.gridx = 0;
        c.gridy = 2;
        frame.add(button, c);

        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JButton GetBack() {
        return button;
    }

    public JFrame GetFrame() {
        return frame;
    }
}
