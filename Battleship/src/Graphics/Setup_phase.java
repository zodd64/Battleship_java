package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class Setup_phase {

    private JFrame frame;
    private JPanel setframe;//to prwto layer me to titlo apo panw kai ta dio koumpia se morfi gridbag
    private JPanel boards;//to deutero layer opou piani ton meseo xoro me ta boards
    private JPanel boarding;
    private JPanel shipping;//xorisma 60/40 sto boarding shipping panw sto boards

    private JPanel board;// to mple gried
    private JPanel ship;// to grid me ta ploia
    private JPanel announcement; //extra borderlayout gia na mpi omorfa to jlabel announce panw apo ta ship
    private JPanel[] space;// panel gia keno xwro giro apo ta borderlayout

    private JLabel announcer;
    private JLabel announce1;
    private JLabel announce2;

    public JButton[][] grid1;
    public JButton[] grid2;
    private JToggleButton rotate;
    private JButton starting;
    private boolean rotation;
    private int shipS=5,shipMSize=5,extend=0;//ShipS ari8mos twn ploin, shipMSize : maximum megethos ploiou, extend: sto 0 epitrepontai max 5 8esewn ploia, sto 1 6 klp
    public  int gs=10;

    
    private GridBagConstraints c[];

    /**
     * creates an instance of the Setup_phase class which creates the Setup_phase GUI
     */
    public Setup_phase() {

        frame = new JFrame();
        setframe = new JPanel(new GridBagLayout());;
        boards = new JPanel(new GridBagLayout());
        boarding = new JPanel(new BorderLayout());
        shipping = new JPanel(new BorderLayout());
        board = new JPanel(new GridLayout(gs, gs));
        ship = new JPanel(new GridLayout(shipS, shipMSize, 0, 5));
        space = new JPanel[8];
        announcement = new JPanel(new BorderLayout());

        announcer = new JLabel("Please select ships from the left and place them on your board. Press \"start Game\" when you are ready");
        announce1 = new JLabel("Your Board", SwingConstants.CENTER);
        announce2 = new JLabel("ships", SwingConstants.CENTER);

        rotate = new JToggleButton("Rotate Ship");
        starting = new JButton("Start Game");
        grid1 = new JButton[gs][gs];
        grid2 = new JButton[shipS*shipMSize];

        c = new GridBagConstraints[6];

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.add(setframe);
        gridsetup();
        
        for (int i = 0; i < 7; i++) {
            space[i] = new JPanel();
        }       

        for (int i = 0; i < 6; i++) {
            c[i] = new GridBagConstraints();
        }

        c[0].weightx = 0.1;
        c[0].gridx = 2;
        c[0].gridy = 0;
        c[0].insets = new Insets(15, 0, 0, 0);
        setframe.add(announcer, c[0]);

        c[1].fill = GridBagConstraints.BOTH;
        c[1].weightx = 0;
        c[1].weighty = 0.1;
        c[1].gridwidth = 4;
        c[1].gridx = 0;
        c[1].gridy = 2;
        c[1].insets = new Insets(40, 40, 40, 40);
        setframe.add(boards, c[1]);

        c[2].gridx = 0;
        c[2].gridy = 3;
        c[2].insets = new Insets(0, 10, 10, 0);
        setframe.add(rotate, c[2]);

        c[3].gridx = 3;
        c[3].gridy = 3;
        c[3].insets = new Insets(0, 0, 10, 10);
        setframe.add(starting, c[3]);

        c[4].fill = GridBagConstraints.BOTH;
        c[4].weightx = 0.6;
        c[4].weighty = 0.1;
        c[4].gridx = 2;
        c[4].gridy = 0;
        boards.add(boarding, c[4]);

        c[5].fill = GridBagConstraints.BOTH;
        c[5].weightx = 0.4;
        c[5].weighty = 0.1;
        c[5].gridx = 0;
        c[5].gridy = 0;
        boards.add(shipping, c[5]);

        shipping.add(announcement, BorderLayout.CENTER);
        boarding.add(board, BorderLayout.CENTER);
        announcement.add(ship, BorderLayout.CENTER);

        boarding.add(space[0], BorderLayout.WEST);
        boarding.add(space[1], BorderLayout.EAST);
        boarding.add(space[2], BorderLayout.SOUTH);
        boarding.add(announce1, BorderLayout.NORTH);
        shipping.add(space[3], BorderLayout.WEST);
        shipping.add(space[4], BorderLayout.SOUTH);
        shipping.add(space[5], BorderLayout.NORTH);
        announcement.add(announce2, BorderLayout.NORTH);

        space[0].setPreferredSize(new Dimension(80, 80));
        space[1].setPreferredSize(new Dimension(50, 50));
        space[2].setPreferredSize(new Dimension(50, 50));
        space[3].setPreferredSize(new Dimension(50, 50));
        space[4].setPreferredSize(new Dimension(130, 130));
        space[5].setPreferredSize(new Dimension(100, 100));
    }

    public JFrame GetFrame() {
        return frame;
    }

    public JButton GetStart() {
        return starting;
    }

    public void SetGrid2(JButton[] grid2) {
        this.grid2 = grid2;
    }

    public JButton[] GetGrid2() {
        return grid2;
    }
    
    
    public void SetGrid1(JButton[][] grid1) {
        this.grid1 = grid1;
    }
    
     public JButton[][] GetGrid1() {
        return grid1;
    }

    public void SetShip(JPanel ship) {
        this.ship = ship;
    }

    public JPanel GetShip() {
        return ship;
    }

    public JToggleButton GetRotate() {
        return rotate;
    }

    public Boolean GetRotation() {
        return rotation;
    }
/**
 * creates a 2D grid of JButtons of cyan color that represent player1's board
 * and a few series of Gray JButtons that represent player1's ships
 */
    private void gridsetup() {

        for (int i = 0; i < gs; i++) {
            for (int j = 0; j < gs; j++) {
                grid1[i][j] = new JButton();
                grid1[i][j].setBackground(Color.CYAN);
                grid1[i][j].setForeground(Color.CYAN);
                grid1[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
                board.add(grid1[i][j]);
            }
        }

        for (int i = 0; i <(shipS*shipMSize); i++) {
            grid2[i] = new JButton();
            grid2[i].setBackground(Color.WHITE);
            grid2[i].setForeground(Color.WHITE);
            grid2[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
            ship.add(grid2[i]);
        }

        for (int i = 0; i < 5; i++) {
            grid2[i].setBackground(Color.GRAY);
            grid2[i].setForeground(Color.GRAY);
            grid2[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        }

        for (int i = 5+extend; i < 9+extend; i++) {
            grid2[i].setBackground(Color.GRAY);
            grid2[i].setForeground(Color.GRAY);
            grid2[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        }

        for (int i = 10+extend*2; i < 13+extend*2; i++) {
            grid2[i].setBackground(Color.GRAY);
            grid2[i].setForeground(Color.GRAY);
            grid2[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        }

        for (int i = 15+extend*3; i < 18+extend*3; i++) {
            grid2[i].setBackground(Color.GRAY);
            grid2[i].setForeground(Color.GRAY);
            grid2[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        }

        for (int i = 20+extend*4; i < 22+extend*4; i++) {
            grid2[i].setBackground(Color.GRAY);
            grid2[i].setForeground(Color.GRAY);
            grid2[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        }
      

    }

}
