package Controller;

import Data.Grid;
import Data.Player;
import Data.Ship;
import Data.Turn;
import Graphics.EndCast;
import Graphics.Information;
import Graphics.Setup_phase;
import Graphics.MainMenu;
import Graphics.Mainboard;
import Graphics.NamePrompt;
import Graphics.StartCast;
import graphics.Shipdestroyed;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Dictator {

    private Player player1, player2;
    private Ship[] p1ship, p2ship;
    private Mainboard battlefield;
    private MainMenu main;
    private NamePrompt name;
    private Setup_phase setup;
    private Information info;
    private String text;
    private Grid p1board, p2board;
    private StartCast first;
    private EndCast last;
    private Shipdestroyed destroy;
    private AI bigBrain;
    private Random rand;
    boolean rotation = true;
    boolean enable;
    private boolean action;
    private boolean[] ship_used;
    private int counter, i, j, k, chosenship, go17 = 0, go27 = 0, cnt, k1, cnt4 = 0;
    private Turn turner;
    private boolean[] ship_died1, ship_died2;
    public int gs = 10;
    private int shipS = 5,shipMSize=5, extend=0;  //ShipS ari8mos twn ploin, shipMSize : maximum megethos ploiou, extend: sto 0 epitrepontai max 5 8esewn ploia, sto 1 6 klp

    /**
     * Creates a new instance of the Dictator class With 2 players and 2
     * boards(one for each player) Also creates a new instance of the Main Menu
     * with action listeners on each button
     */
    public Dictator() {

        action = false;
        enable = false;
        main = new MainMenu();
        player2 = new Player("Skynet");
        rand = new Random();
        p1board = new Grid(player1);
        p2board = new Grid(player2);
        ship_used = new boolean[]{false, false, false, false, false,false};
        ship_died1 = new boolean[]{false, false, false, false, false,false};
        ship_died2 = new boolean[]{false, false, false, false, false,false};
        main.GetPlay().addActionListener(new MainActionListener());
        main.GetHelp().addActionListener(new MainActionListener());
        main.GetQuit().addActionListener(new MainActionListener());
        
    }

    /**
     * Checks if player1's ships are destroyed and announces it.(Currently
     * unused)
     */
    private void Castships1() {

        for (int k = 0; k < shipS; k++) {
            if (p1ship[k].GetHp() == 0 && ship_died1[k] == false) {
                destroy = new Shipdestroyed(text, p1ship[k].getShipName());
                ship_died1[k] = true;
            }

        }

    }

    /**
     * checks if player2's ships are destroyed and announces it.
     */
    private void Castships2() {

        for (int k = 0; k < shipS; k++) {
            if (p2ship[k].GetHp() == 0 && ship_died2[k] == false) {
                destroy = new Shipdestroyed("Skynet", p2ship[k].getShipName());
                ship_died2[k] = true;
            }

        }

    }

    /**
     * Enables the repositioning of a ship during setup_phase
     *
     * @param kek
     */
    private void remove(int kek) {

        if (p1ship[kek].GetRot() == 0) {
            for (int x = 0; x < p1ship[kek].getShip(); x++) {
                if (p1board.grid[p1ship[kek].GetCordx()][p1ship[kek].GetCordy() + x].gethitmark() == true) {
                    for (int k2 = 0; k2 < p1ship[kek].getShip(); k2++) {
                        p1board.grid[p1ship[kek].GetCordx()][p1ship[kek].GetCordy() + k2].sethitmark(false);
                        setup.grid1[p1ship[kek].GetCordx()][p1ship[kek].GetCordy() + k2].setBackground(Color.CYAN);
                    }
                }
            }

        } else if (p1ship[kek].GetRot() == 1) {
            for (int x = 0; x < p1ship[kek].getShip(); x++) {
                if (p1board.grid[p1ship[kek].GetCordx() + x][p1ship[kek].GetCordy()].gethitmark() == true) {
                    for (int k2 = 0; k2 < p1ship[kek].getShip(); k2++) {
                        p1board.grid[p1ship[kek].GetCordx() + k2][p1ship[kek].GetCordy()].sethitmark(false);
                        setup.grid1[p1ship[kek].GetCordx() + k2][p1ship[kek].GetCordy()].setBackground(Color.CYAN);
                    }
                }
            }
        }
    }

    /**
     * sets a player2's ship hp = 0 if it was hit in all its marks
     */
    private void ucheck() {

        for (int k = 0; k < shipS; k++) {
            if (p2ship[k].GetRot() == 0) {
                for (int x = 0; x < p2ship[k].getShip(); x++) {
                    if (p2board.grid[p2ship[k].GetCordx()][p2ship[k].GetCordy() + x].gethitmark() == false) {
                        cnt4++;
                    }
                    if (cnt4 == p2ship[k].getShip()) {
                        p2ship[k].SetHp(0);
                    }
                }

            } else if (p2ship[k].GetRot() == 1) {
                for (int x = 0; x < p2ship[k].getShip(); x++) {
                    if (p2board.grid[p2ship[k].GetCordx() + x][p2ship[k].GetCordy()].gethitmark() == false) {
                        cnt4++;
                    }
                    if (cnt4 == p2ship[k].getShip()) {
                        p2ship[k].SetHp(0);
                    }
                }
            }
            cnt4 = 0;
        }
    }

    /**
     * if it's player2's turn initiate attack to player1
     */
    private void fight() {

        if (turner.GetTurn() == player2) {
            action = bigBrain.cpuattack(p1board, battlefield, p1ship);
        }
        action = true;
    }

    /**
     * dictates who plays first
     */
    private void first_blood() {

        counter = rand.nextInt(2);;
        if (counter == 0) {
            turner = new Turn(player1);
            first = new StartCast(text);
        } else {
            turner = new Turn(player2);
            first = new StartCast("Skynet");
        }
    }

    /**
     * brings player1's ships from the Setup_Phase into the main_board
     */
    private void initialize_shipsonfield() {

        for (i = 0; i < gs; i++) {
            for (j = 0; j < gs; j++) {
                if (setup.grid1[i][j].getBackground() == Color.GRAY) {
                    battlefield.grid1[i][j].setBackground(Color.GRAY);
                }
            }
        }
    }

    /**
     * initializes p1 and p2 ships
     */
    private void initialize_ships() {

        p1ship = new Ship[shipS];
        p2ship = new Ship[shipS];

        p1ship[0] = new Ship("aircraft carrier", 5, player1);
        p2ship[0] = new Ship("aircraft carrier", 5, player2);

        p1ship[1] = new Ship("battleship", 4, player1);
        p2ship[1] = new Ship("battleship", 4, player2);

        p1ship[2] = new Ship("submarine", 3, player1);
        p2ship[2] = new Ship("submarine", 3, player2);

        p1ship[3] = new Ship("destroyer", 3, player1);
        p2ship[3] = new Ship("destroyer", 3, player2);

        p1ship[4] = new Ship("patrol boat", 2, player1);
        p2ship[4] = new Ship("patrol boat", 2, player2);    

        
    }

    /**
     * randomly spawn player2's ships
     */
    private void randomize_cpuships() {

        boolean taken = true;
        int rot;
        int rowX, rowY, colX, colY;

        for (int k = 0; k < shipS; k++) {

            do {
                taken = true;
                // 0 = to ploio mpainei euthia, 1 = mpainei katheta
                rot = rand.nextInt(2);
                //
                rowX = rand.nextInt(gs); //dialekse seira
                colX = rand.nextInt(gs + 1 - (p2ship[k].getShip()));

                rowY = rand.nextInt(gs + 1 - (p2ship[k].getShip()));
                colY = rand.nextInt(gs);

                if (rot == 0) {

                    for (int x = 0; x < p2ship[k].getShip(); x++) {
                        if (p2board.grid[rowX][colX + x].gethitmark() == true) {
                            break;
                        }
                        if (x + 1 == p2ship[k].getShip()) {
                            p2ship[k].SetRot(rot);
                            p2ship[k].SetCordx(rowX);
                            p2ship[k].SetCordy(colX);
                            for (x = 0; x < p2ship[k].getShip(); x++) {
                                p2board.grid[rowX][colX + x].sethitmark(true);
                                //battlefield.grid2[rowX][colX + x].setBackground(Color.GRAY); //shows cpu ships
                            }
                            taken = false;
                        }

                    }

                } else if (rot == 1) {

                    for (int x = 0; x < p2ship[k].getShip(); x++) {
                        if (p2board.grid[rowY + x][colY].gethitmark() == true) {
                            break;
                        }
                        if (x + 1 == p2ship[k].getShip()) {
                            p2ship[k].SetRot(rot);
                            p2ship[k].SetCordx(rowY);
                            p2ship[k].SetCordy(colY);
                            for (x = 0; x < p2ship[k].getShip(); x++) {
                                p2board.grid[rowY + x][colY].sethitmark(true);
                                //battlefield.grid2[rowY + x][colY].setBackground(Color.GRAY); //shows cpu ships
                            }
                            taken = false;
                        }

                    }

                }

            } while (taken);

        }
    }

    /**
     * Main_menu action_listener
     */
    private class MainActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == main.GetPlay()) {
                name = new NamePrompt();
                name.GetLogin().addActionListener(new PromptActionListener());
                name.GetField().getDocument().addDocumentListener(new Promptdoc());
                name.GetLogin().setEnabled(false);
                main.GetFrame().setVisible(false);
            } else if (e.getSource() == main.GetHelp()) {
                info = new Information();
                info.GetBack().addActionListener(new HelpActionListener());

            } else if (e.getSource() == main.GetQuit()) {
                main.GetFrame().dispose();
            }
        }
    }

    /**
     * Help action_listener responsible for disposing the help window
     */
    private class HelpActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == info.GetBack()) {
                info.GetFrame().dispose();
            }
        }
    }

    /**
     * DocumentListener which makes sure the player choose a name before being
     * able to continue
     */
    private class Promptdoc implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {

            if (name.GetField().getDocument().getLength() == 0) {
                name.GetLogin().setEnabled(false);
            } else {
                name.GetLogin().setEnabled(true);
            }

        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (name.GetField().getDocument().getLength() == 0) {
                name.GetLogin().setEnabled(false);
            } else {
                name.GetLogin().setEnabled(true);
            }

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (name.GetField().getDocument().getLength() == 0) {
                name.GetLogin().setEnabled(false);
            } else {
                name.GetLogin().setEnabled(true);
            }
        }
    }

    /**
     * action_listener responsible for initialing the setup functionality after
     * a name has been chosen
     */
    private class PromptActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == name.GetLogin()) {
                text = name.GetField().getText(); //perasma onomatos se string              
                player1 = new Player(text);// initialization tou player 1 me to onoma pou perasme sto string
                p1board = new Grid(player1);//initialize player  grids
                name.GetFrame().dispose();
                setup = new Setup_phase();
                setup.GetStart().addActionListener(new SetupActionListener());
                setup.GetStart().setEnabled(false);
                setup.GetRotate().addActionListener(new SetupActionListener());
                initialize_ships();
                for (int i = 0; i < gs; i++) {
                    for (int j = 0; j < gs; j++) {
                        setup.grid1[i][j].addMouseListener(new SetupMouseListener());
                    }
                }
                for (int i = 0; i < shipS*shipMSize; i++) {
                    setup.grid2[i].addMouseListener(new SetupMouseListener());
                }
            }
        }
    }

    /**
     * setup_phase action listener which initiates the game after the start game
     * is pressed
     */
    private class SetupActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == setup.GetStart()) {
                setup.GetFrame().dispose();
                battlefield = new Mainboard(text);
                for (int i = 0; i < gs; i++) {
                    for (int j = 0; j < gs; j++) {
                        battlefield.grid2[i][j].addMouseListener(new MainMouseListener());
                    }
                }
                bigBrain = new AI();
                randomize_cpuships();
                initialize_shipsonfield();
                first_blood();
                fight();
            }

            //rotate ship
            if (setup.GetRotate().isSelected()) {
                rotation = false;
            } else {
                rotation = true;
            }

        }
    }

    /**
     * setup_phase mouse listener that is responsible positioning and
     * repositioning ships
     */
    private class SetupMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

            //selecting ship
            for (i = 0; i < 5; i++) {
                if (e.getSource() == setup.grid2[i] && ship_used[0] == false && enable == false) {
                    enable = true;
                    for (j = 0; j < 5; j++) {
                        setup.grid2[j].setBackground(Color.YELLOW);
                    }
                    chosenship = 0;
                } else if (e.getSource() == setup.grid2[i] && ship_used[0] == true && enable == false) {
                    remove(0);
                    ship_used[0] = false;
                    cnt--;
                    for (j = 0; j < 5; j++) {
                        setup.grid2[j].setBackground(Color.GRAY);
                    }
                }
            }

            for (i = 5+extend; i < 9+extend; i++) {
                if (e.getSource() == setup.grid2[i] && ship_used[1] == false && enable == false) {
                    enable = true;
                    for (j = 5+extend; j < 9+extend; j++) {
                        setup.grid2[j].setBackground(Color.YELLOW);
                    }
                    chosenship = 1;
                } else if (e.getSource() == setup.grid2[i] && ship_used[1] == true && enable == false) {
                    remove(1);
                    cnt--;
                    ship_used[1] = false;
                    for (j = 5+extend; j < 9+extend; j++) {
                        setup.grid2[j].setBackground(Color.GRAY);
                    }
                }
            }

            for (i = 10+extend*2; i < 13+extend*2; i++) {
                if (e.getSource() == setup.grid2[i] && ship_used[2] == false && enable == false) {
                    enable = true;
                    for (j = 10+extend*2; j < 13+extend*2; j++) {
                        setup.grid2[j].setBackground(Color.YELLOW);
                    }
                    chosenship = 2;
                } else if (e.getSource() == setup.grid2[i] && ship_used[2] == true && enable == false) {
                    remove(2);
                    cnt--;
                    ship_used[2] = false;
                    for (j = 10+extend*2; j < 13+extend*2; j++) {
                        setup.grid2[j].setBackground(Color.GRAY);
                    }
                }
            }

            for (i = 15+extend*3; i < 18+extend*3; i++) {
                if (e.getSource() == setup.grid2[i] && ship_used[3] == false && enable == false) {
                    enable = true;
                    for (j = 15+extend*3; j < 18+extend*3; j++) {
                        setup.grid2[j].setBackground(Color.YELLOW);
                    }
                    chosenship = 3;
                } else if (e.getSource() == setup.grid2[i] && ship_used[3] == true && enable == false) {
                    remove(3);
                    cnt--;
                    ship_used[3] = false;
                    for (j = 15+extend*3; j < 18+extend*3; j++) {
                        setup.grid2[j].setBackground(Color.GRAY);
                    }
                }
            }
            for (int i = 20+extend*4; i < 22+extend*4; i++) {
                if (e.getSource() == setup.grid2[i] && ship_used[4] == false && enable == false) {
                    enable = true;
                    for (j = 20+extend*4; j < 22+extend*4; j++) {
                        setup.grid2[j].setBackground(Color.YELLOW);
                    }
                    chosenship = 4;
                } else if (e.getSource() == setup.grid2[i] && ship_used[4] == true && enable == false) {
                    remove(4);
                    cnt--;
                    ship_used[4] = false;
                    for (j = 20+extend*4; j < 22+extend*4; j++) {
                        setup.grid2[j].setBackground(Color.GRAY);
                    }
                }
            }

            if (enable == true) {         //put ship in place
                if (rotation == true) {
                    for (i = 0; i < gs; i++) {
                        for (j = 0; j < gs; j++) {
                            if (e.getSource() == setup.grid1[i][j] && i < gs + 1 - p1ship[chosenship].getShip()) {
                                for (int k = 0; k < p1ship[chosenship].getShip(); k++) {
                                    if (setup.grid1[i + k][j].getBackground() == Color.RED) {
                                        //JOptionPane.showMessageDialog(null,  "Can't place your ship here!");
                                        break;

                                    } else if (k + 1 == p1ship[chosenship].getShip()) {
                                        p1ship[chosenship].SetRot(1);
                                        p1ship[chosenship].SetCordx(i);
                                        p1ship[chosenship].SetCordy(j);

                                        for (int k2 = 0; k2 < p1ship[chosenship].getShip(); k2++) {
                                            p1board.grid[i + k2][j].sethitmark(true);
                                            setup.grid1[i + k2][j].setBackground(Color.GRAY);
                                            //setup.grid1[i+ k2][j].setText(Integer.toString(p1ship[chosenship].getShip())); //vazei noumera sta ploia otan ta vazeis vash size
                                        }
                                        enable = false;
                                        ship_used[chosenship] = true;
                                        cnt++;
                                    }

                                }
                            }else if(e.getSource() == setup.grid1[i][j] && i >= gs+1 - p1ship[chosenship].getShip()){
                                //JOptionPane.showMessageDialog(null,  "Can't place your ship here!");
                            }
                        }
                    }
                } else if (rotation == false) {
                    for (i = 0; i < gs; i++) {
                        for (j = 0; j < gs; j++) {
                            if (e.getSource() == setup.grid1[i][j] && j < gs + 1 - p1ship[chosenship].getShip()) {
                                for (int k = 0; k < p1ship[chosenship].getShip(); k++) {
                                    if (setup.grid1[i][j + k].getBackground() == Color.RED) {
                                        //JOptionPane.showMessageDialog(null,  "Can't place your ship here!");
                                        break;
                                    } else if (k + 1 == p1ship[chosenship].getShip()) {
                                        p1ship[chosenship].SetRot(0);
                                        p1ship[chosenship].SetCordx(i);
                                        p1ship[chosenship].SetCordy(j);

                                        for (int k2 = 0; k2 < p1ship[chosenship].getShip(); k2++) {
                                            p1board.grid[i][j + k2].sethitmark(true);
                                            setup.grid1[i][j + k2].setBackground(Color.GRAY);
                                            //setup.grid1[i+ k2][j].setText(Integer.toString(p1ship[chosenship].getShip())); //vazei noumera sta ploia otan ta vazeis vash size
                                        }
                                        enable = false;
                                        ship_used[chosenship] = true;
                                        cnt++;
                                    }
                                }
                            }else if(e.getSource() == setup.grid1[i][j] && i >= gs+1 - p1ship[chosenship].getShip()){
                                //JOptionPane.showMessageDialog(null,  "Can't place your ship here!");
                            }
                        }
                    }
                }
                if (chosenship == 0 && enable == false) {
                    for (j = 0; j < 5; j++) {
                        setup.grid2[j].setBackground(Color.WHITE);
                    }
                } else if (chosenship == 1 && enable == false) {
                    for (j = 5+extend; j < 9+extend; j++) {
                        setup.grid2[j].setBackground(Color.WHITE);
                    }
                } else if (chosenship == 2 && enable == false) {
                    for (j = 10+extend*2; j < 13+extend*2; j++) {
                        setup.grid2[j].setBackground(Color.WHITE);
                    }
                } else if (chosenship == 3 && enable == false) {
                    for (j = 15+extend*3; j < 18+extend*3; j++) {
                        setup.grid2[j].setBackground(Color.WHITE);
                    }
                } else if (chosenship == 4 && enable == false) {
                    for (j = 20+extend*4; j < 22+extend*4; j++) {
                        setup.grid2[j].setBackground(Color.WHITE);
                    }
                }             

            }
            if (cnt == shipS) {
                setup.GetStart().setEnabled(true);
            } else {
                setup.GetStart().setEnabled(false);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            if (enable == true) {
                if (rotation == true) {
                    for (i = 0; i < gs; i++) {
                        for (j = 0; j < gs; j++) {
                            if (e.getSource() == setup.grid1[i][j] && i < gs + 1 - p1ship[chosenship].getShip()) {
                                for (k = 0; k < p1ship[chosenship].getShip(); k++) {
                                    if (setup.grid1[i + k][j].getBackground() == Color.GRAY) {
                                        setup.grid1[i + k][j].setBackground(Color.RED);                    //aferesi tou mouser enter kai exit epeidi svini ama pas apo panw
                                    } else {
                                        setup.grid1[i + k][j].setBackground(Color.GREEN);                    //aferesi tou mouser enter kai exit epeidi svini ama pas apo panw
                                    }
                                }
                            } else if (e.getSource() == setup.grid1[i][j] && i >= gs + 1 - p1ship[chosenship].getShip()) {
                                for (k1 = 0; k1 < p1ship[chosenship].getShip(); k1++) {
                                    if (i + k1 < gs) {
                                        if (setup.grid1[i + k1][j].getBackground() == Color.CYAN) {
                                            setup.grid1[i + k1][j].setBackground(Color.RED);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (rotation == false) {

                    for (i = 0; i < gs; i++) {
                        for (j = 0; j < gs; j++) {
                            if (e.getSource() == setup.grid1[i][j] && j < gs + 1 - p1ship[chosenship].getShip()) {
                                for (k = 0; k < p1ship[chosenship].getShip(); k++) {
                                    if (setup.grid1[i][j + k].getBackground() == Color.GRAY) {
                                        setup.grid1[i][j + k].setBackground(Color.RED);                    //aferesi tou mouser enter kai exit epeidi svini ama pas apo panw
                                    } else {
                                        setup.grid1[i][j + k].setBackground(Color.GREEN);
                                    }
                                }
                            } else if (e.getSource() == setup.grid1[i][j] && j >= gs + 1 - p1ship[chosenship].getShip()) {
                                for (k1 = 0; k1 < p1ship[chosenship].getShip(); k1++) {
                                    if (j + k1 < gs) {
                                        if (setup.grid1[i][j + k1].getBackground() == Color.CYAN) {
                                            setup.grid1[i][j + k1].setBackground(Color.RED);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

            if (enable == true) {
                if (rotation == true) {
                    for (i = 0; i < gs; i++) {
                        for (j = 0; j < gs; j++) {
                            if (e.getSource() == setup.grid1[i][j] && i < gs + 1 - p1ship[chosenship].getShip()) {
                                for (k = 0; k < p1ship[chosenship].getShip(); k++) {
                                    if (setup.grid1[i + k][j].getBackground() == Color.RED) {
                                        setup.grid1[i + k][j].setBackground(Color.GRAY);
                                    } else {
                                        setup.grid1[i + k][j].setBackground(Color.CYAN);
                                    }
                                }
                            } else if (e.getSource() == setup.grid1[i][j] && i >= gs + 1 - p1ship[chosenship].getShip()) {
                                for (k1 = 0; k1 < p1ship[chosenship].getShip(); k1++) {
                                    if (i + k1 < gs) {
                                        if (setup.grid1[i + k1][j].getBackground() == Color.RED) {
                                            setup.grid1[i + k1][j].setBackground(Color.CYAN);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (rotation == false) {

                    for (i = 0; i < gs; i++) {
                        for (j = 0; j < gs; j++) {
                            if (e.getSource() == setup.grid1[i][j] && j < gs + 1 - p1ship[chosenship].getShip()) {
                                for (k = 0; k < p1ship[chosenship].getShip(); k++) {
                                    if (setup.grid1[i][j + k].getBackground() == Color.RED) {
                                        setup.grid1[i][j + k].setBackground(Color.GRAY);
                                    } else {
                                        setup.grid1[i][j + k].setBackground(Color.CYAN);
                                    }
                                }
                            } else if (e.getSource() == setup.grid1[i][j] && j >= gs + 1 - p1ship[chosenship].getShip()) {
                                for (k1 = 0; k1 < p1ship[chosenship].getShip(); k1++) {
                                    if (j + k1 < gs) {
                                        if (setup.grid1[i][j + k1].getBackground() == Color.RED) {
                                            setup.grid1[i][j + k1].setBackground(Color.CYAN);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * main mouse listener that is responsible for when the player chooses which
     * square to attack
     */
    private class MainMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (action == true) {
                for (i = 0; i < gs; i++) {
                    for (j = 0; j < gs; j++) {
                        if (e.getSource() == battlefield.grid2[i][j] && p2board.grid[i][j].getFree() == true) {
                            p2board.grid[i][j].setFree(false);
                            battlefield.grid2[i][j].setBackground(Color.WHITE);
                            action = false;
                            if (p2board.grid[i][j].gethitmark() == true) {
                                p2board.grid[i][j].sethitmark(false);
                                battlefield.grid2[i][j].setBackground(Color.RED);
                                go17++;
                                ucheck();
                                Castships2();

                            }
                            Thread t1 = new Thread(new Runnable() {
                                public void run() {
                                    try {

                                        TimeUnit.SECONDS.sleep(1);

                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Dictator.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    action = bigBrain.cpuattack(p1board, battlefield, p1ship);
                                    if (bigBrain.getgo27() == 17) {
                                        last = new EndCast("skynet");
                                        action = false;
                                    }
                                }
                            });
                            t1.start();
                            t1.setPriority(Thread.MIN_PRIORITY);

                            if (go17 == 17) {
                                last = new EndCast(text);
                                action = false;
                            }
                        }
                    }
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            if (action == true) {
                for (i = 0; i < gs; i++) {
                    for (j = 0; j < gs; j++) {
                        if (e.getSource() == battlefield.grid2[i][j]) {
                            if (battlefield.grid2[i][j].getBackground() == Color.CYAN) {
                                battlefield.grid2[i][j].setBackground(Color.GREEN);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

            if (action == true) {
                for (i = 0; i < gs; i++) {
                    for (j = 0; j < gs; j++) {
                        if (e.getSource() == battlefield.grid2[i][j]) {
                            if (battlefield.grid2[i][j].getBackground() == Color.GREEN) {
                                battlefield.grid2[i][j].setBackground(Color.CYAN);
                            }
                        }
                    }
                }
            }
        }
    }

}
