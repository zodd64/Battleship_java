package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class MainMenu {

    private JFrame frame;
    private JPanel menu; //borderlayout gia to menou
    private JPanel btns;// grid  gia tis 3 epiloges mas sto CENTER tou borderlayout
    private JButton play;
    private JButton help;
    private JButton quit;
    private JPanel[] space;// Panels gia to keno giro apo ta koumpia

    /**
     * creates an instance of the MainMenu class which in turn creates the Main Menu GUI
     */
    public MainMenu() {

        frame = new JFrame("Battleship - Main Menu");
        menu = new JPanel(new BorderLayout());
        btns = new JPanel(new GridLayout(3, 1, 0, 30));
        space = new JPanel[4];
        play = new JButton("Play");
        help = new JButton("Help");
        quit = new JButton("Quit");

        for (int i = 0; i < 4; i++) {
            space[i] = new JPanel();
        }

        initialize_layout();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300, 330);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void initialize_layout() {
        menu.add(btns, BorderLayout.CENTER);
        menu.add(space[0], BorderLayout.WEST);
        menu.add(space[1], BorderLayout.EAST);
        menu.add(space[2], BorderLayout.NORTH);
        menu.add(space[3], BorderLayout.SOUTH);
        space[0].setPreferredSize(new Dimension(50, 50));
        space[1].setPreferredSize(new Dimension(50, 50));
        space[2].setPreferredSize(new Dimension(50, 50));
        space[3].setPreferredSize(new Dimension(50, 50));
        frame.add(menu);
        btns.add(play);
        btns.add(help);
        btns.add(quit);
    }

    public JFrame GetFrame() {
        return frame;
    }

    public JButton GetPlay() {
        return play;
    }

    public JButton GetHelp() {
        return help;
    }

    public JButton GetQuit() {
        return quit;
    }
}
