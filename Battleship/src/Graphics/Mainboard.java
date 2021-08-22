package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class Mainboard extends JFrame {

    private JFrame frame;
    private JPanel mainframe1, mainframe2;//to aristero kai to deksi panel apo to split 
    private JPanel p1, p2; // to aristero kai to deksi board
    private JSplitPane jsp; //gia to xorisma tou menou stin mesi
    public JButton[][] grid1;
    public JButton[][] grid2;
    private JPanel[] space;// keno giro apo to borderlayout
    private JLabel name1, name2;// o titlos panw apo ta boards
    public  int gs=10;

    /**
     * creates an instance of the Mainboard class which creates the Game GUI after the setup phase is done
     * @param playername 
     */
    public Mainboard(String playername) {

        frame = new JFrame("Battleship - Play Game");
        mainframe1 = new JPanel(new BorderLayout());
        mainframe2 = new JPanel(new BorderLayout());
        p1 = new JPanel(new GridLayout(gs, gs));
        p2 = new JPanel(new GridLayout(gs, gs));
        space = new JPanel[8];

        grid1 = new JButton[gs][gs];
        grid2 = new JButton[gs][gs];
        name1 = new JLabel(playername + "'s Board", SwingConstants.CENTER);
        name2 = new JLabel("CPU's Board", SwingConstants.CENTER);

        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, mainframe1, mainframe2);
        jsp.setResizeWeight(0.5);
        jsp.setEnabled(false);
        frame.setContentPane(jsp);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        initialize_grids();
        initialize_borders();
    }

    private void initialize_grids() {

        for (int i = 0; i < 7; i++) {
            space[i] = new JPanel();
        }

        for (int i = 0; i < gs; i++) {
            for (int j = 0; j < gs; j++) {
                grid1[i][j] = new JButton();
                grid1[i][j].setBackground(Color.CYAN);
                grid1[i][j].setForeground(Color.CYAN);
                grid1[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
                p1.add(grid1[i][j]);
            }
        }
        for (int i = 0; i < gs; i++) {
            for (int j = 0; j < gs; j++) {
                grid2[i][j] = new JButton();
                grid2[i][j].setBackground(Color.CYAN);
                grid2[i][j].setForeground(Color.CYAN);
                grid2[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
                p2.add(grid2[i][j]);
            }
        }
    }

    private void initialize_borders() {

        mainframe1.add(p1, BorderLayout.CENTER);
        mainframe2.add(p2, BorderLayout.CENTER);
        mainframe1.add(space[0], BorderLayout.WEST);
        mainframe2.add(space[1], BorderLayout.WEST);
        mainframe1.add(space[2], BorderLayout.EAST);
        mainframe2.add(space[3], BorderLayout.EAST);
        mainframe1.add(space[4], BorderLayout.SOUTH);
        mainframe2.add(space[5], BorderLayout.SOUTH);
        mainframe1.add(space[6], BorderLayout.NORTH);
        mainframe1.add(name1, BorderLayout.NORTH);
        mainframe2.add(name2, BorderLayout.NORTH);

        space[0].setPreferredSize(new Dimension(50, 50));
        space[1].setPreferredSize(new Dimension(50, 50));
        space[2].setPreferredSize(new Dimension(50, 50));
        space[3].setPreferredSize(new Dimension(50, 50));
        space[4].setPreferredSize(new Dimension(50, 50));
        space[5].setPreferredSize(new Dimension(50, 50));
        space[6].setPreferredSize(new Dimension(50, 50));
        name1.setPreferredSize(new Dimension(50, 50));
        name2.setPreferredSize(new Dimension(50, 50));
    }

    public void SetGrid1(JButton[][] grid1) {
        this.grid1 = grid1;
    }

    public JButton[][] GetGrid1() {
        return grid1;
    }

    public void SetGrid2(JButton[][] grid2) {
        this.grid2 = grid2;
    }

    public JButton[][] GetGrid2() {
        return grid2;
    }

}
