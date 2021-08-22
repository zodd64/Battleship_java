package Controller;

import Data.Grid;
import Data.Ship;
import Graphics.Mainboard;
import java.awt.Color;
import java.util.Random;

public class AI {

    private boolean streak = false, alert = false, goR, goL, goU, goD;
    private int o = 0, rowS, colS, row, col, hits, stop = 0, p1deaths = 0, go27 = 0, cnt4 = 0;
    private int notFreeUL, notFreeUR, notFreeLL, notFreeLR, notFree, notFreeL, notFreeR, notFreeU, notFreeD; //metrites gia apofugh adieksodou tou AI.
    private int rTwo, rThree, rFour;//randoms
    private Random rand;
    private int gs = 10;
    public int getgo27() {
        return this.go27;
    }

    /**
     * sets a player1's ship hp = 0 if it was hit in all its marks 
     * @param p1ship
     * @param p1board 
     */
    public void cpucheck(Ship[] p1ship, Grid p1board) {

        for (int k = 0; k < 5; k++) {
            if (p1ship[k].GetRot() == 0) {
                for (int x = 0; x < p1ship[k].getShip(); x++) {
                    if (p1board.grid[p1ship[k].GetCordx()][p1ship[k].GetCordy() + x].gethitmark() == false) {
                        cnt4++;
                    }
                    if (cnt4 == p1ship[k].getShip()) {
                        p1ship[k].SetHp(0);
                    }
                }

            } else if (p1ship[k].GetRot() == 1) {
                for (int x = 0; x < p1ship[k].getShip(); x++) {
                    if (p1board.grid[p1ship[k].GetCordx() + x][p1ship[k].GetCordy()].gethitmark() == false) {
                        cnt4++;
                    }
                    if (cnt4 == p1ship[k].getShip()) {
                        p1ship[k].SetHp(0);

                    }
                }
            }
            cnt4 = 0;

        }
    }

    /**
     * checks how many p1ships are destroyed
     * @param p1ship
     * @return 
     */
    public int P1ShipsDead(Ship[] p1ship) {

        for (int k = 0; k < 5; k++) {
            if (p1ship[k].GetHp() == 0) {
                p1deaths++;
            }
        }
        return p1deaths;
    }

    /**
     * Player2 looks for squares to strike and strikes
     * 
     * @param p1board
     * @param battlefield
     * @param p1ship
     * @return 
     */
    public boolean cpuattack(Grid p1board, Mainboard battlefield, Ship[] p1ship) {
        rand = new Random();
        hits = p1deaths = notFree = notFreeL = notFreeR = notFreeU = notFreeD = notFreeUL = notFreeUR = notFreeLL = notFreeLR = 0;
        boolean check = true;
        if (alert == false) {
            if (stop < 3) {
                for (row = 0; row < gs; row++) {//psakse gia kenes 8eseis anamesa se xtuphmata ana seira kai xtupa.
                    for (col = 0; col < gs-2; col++) {
                        if (battlefield.grid1[row][col].getBackground() == Color.RED && p1board.grid[row][col + 1].getFree() == true && battlefield.grid1[row][col + 2].getBackground() == Color.RED) {
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                                stop++;
                            }
                            hits++;
                            break;

                        }
                    }
                    if (hits > 0) {
                        break;
                    }
                }
                if (hits == 0) {
                    for (row = 0; row < gs-2; row++) {//psakse gia kenes 8eseis anamesa se xtuphmata ana sthlh kai xtupa.
                        for (col = 0; col < gs; col++) {
                            if (battlefield.grid1[row][col].getBackground() == Color.RED && p1board.grid[row + 1][col].getFree() == true && battlefield.grid1[row + 2][col].getBackground() == Color.RED) {
                                p1board.grid[row + 1][col].setFree(false);
                                if (p1board.grid[row + 1][col].gethitmark() == true) {
                                    p1board.grid[row + 1][col].sethitmark(false);
                                    battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                    go27++;
                                    cpucheck(p1ship, p1board);
                                    if (P1ShipsDead(p1ship) > o) {
                                        o++;
                                        alert = false;
                                    }
                                } else {
                                    battlefield.grid1[row + 1][col].setBackground(Color.WHITE);
                                    stop++;
                                }
                                hits++;
                                break;
                            }
                        }
                        if (hits > 0) {
                            break;
                        }
                    }
                }
            }
            if (hits == 0) {
                do {

                    rowS = row = rand.nextInt(gs);
                    colS = col = rand.nextInt(gs);
                    check = true;

                    if (p1board.grid[row][col].getFree() == true) {
                        p1board.grid[row][col].setFree(false);
                        check = false;
                    }
                } while (check);

                if (p1board.grid[row][col].gethitmark() == true) {
                    p1board.grid[row][col].sethitmark(false);
                    battlefield.grid1[row][col].setBackground(Color.RED);
                    go27++;
                    alert = true;
                    cpucheck(p1ship, p1board);
                    if (P1ShipsDead(p1ship) > o) {
                        o++;
                        alert = false;
                    }

                } else {
                    battlefield.grid1[row][col].setBackground(Color.WHITE);
                }
            }
        } else if (alert == true) {  // ean tuxaia petuxes kati

            do {
                notFreeUL = notFreeUR = notFreeLL = notFreeLR = notFreeR = notFreeL = notFreeU = notFreeD = notFree = 0;
                rTwo = rand.nextInt(2);
                rThree = rand.nextInt(3);
                rFour = rand.nextInt(4);

                if (streak == false) { //
                    colS = col;
                    rowS = row;
                    goR = goL = goU = goD = false;
                    if (row < 1 && col < 1) { //an ksekinas sthn panw aristerh gwnia                        
                        if (rTwo == 0 && p1board.grid[row][col + 1].getFree() == true) { //koita deksia
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                colS++;
                                goR = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col + 1].getFree() == false) {
                            notFreeUL++;
                        }

                        if (rTwo == 1 && p1board.grid[row + 1][col].getFree() == true) { // koita katw                   
                            p1board.grid[row + 1][col].setFree(false);
                            if (p1board.grid[row + 1][col].gethitmark() == true) {
                                p1board.grid[row + 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goD = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row + 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row + 1][col].getFree() == false) {
                            notFreeUL++;
                        }

                        if (notFreeUL > 1) {//an egklovisteis pare nea eleu8erh row kai col

                            do {
                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                alert = true;
                                cpucheck(p1ship, p1board);
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }

                    } else if (row > 8 && col < 1) { //an ksekinas sthn katw aristerh gwnia
                        if (rTwo == 0 && p1board.grid[row][col + 1].getFree() == true) { //koitaei deksia
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goR = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col + 1].getFree() == false) {
                            notFreeLL++;
                        }
                        if (rTwo == 1 && p1board.grid[row - 1][col].getFree() == true) { // koitaei panw                   
                            p1board.grid[row - 1][col].setFree(false);
                            if (p1board.grid[row - 1][col].gethitmark() == true) {
                                p1board.grid[row - 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row - 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goU = true;
                                rowS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row - 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row - 1][col].getFree() == false) {
                            notFreeLL++;
                        }
                        if (notFreeLL > 1) {//an egklovisteis pare nea eleu8erh row kai col

                            do {
                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                alert = true;
                                cpucheck(p1ship, p1board);
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }

                    } else if (row > 1 && col > gs-2) { //an ksekinas sthn panw deksia gwnia
                        if (rTwo == 0 && p1board.grid[row][col - 1].getFree() == true) { // koitae aristera                    
                            p1board.grid[row][col - 1].setFree(false);
                            if (p1board.grid[row][col - 1].gethitmark() == true) {
                                p1board.grid[row][col - 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col - 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goL = true;
                                colS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row][col - 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col - 1].getFree() == false) {
                            notFreeUR++;
                        }

                        if (rTwo == 1 && p1board.grid[row + 1][col].getFree() == true) { // koita katw                   
                            p1board.grid[row + 1][col].setFree(false);
                            if (p1board.grid[row + 1][col].gethitmark() == true) {
                                p1board.grid[row + 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goD = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row + 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row + 1][col].getFree() == false) {
                            notFreeUR++;
                        }
                        if (notFreeUR > 1) {//an egklovisteis pare nea eleu8erh row kai col

                            do {
                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                cpucheck(p1ship, p1board);
                                go27++;
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }

                    } else if (row > gs-2 && col > gs-2) { //an ksekinas sthn katw deksia gwnia
                        if (rTwo == 0 && p1board.grid[row][col - 1].getFree() == true) { // koitae aristera                    
                            p1board.grid[row][col - 1].setFree(false);
                            if (p1board.grid[row][col - 1].gethitmark() == true) {
                                p1board.grid[row][col - 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col - 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goL = true;
                                colS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row][col - 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col - 1].getFree() == false) {
                            notFreeLR++;
                        }
                        if (rTwo == 1 && p1board.grid[row - 1][col].getFree() == true) { // koitaei panw                   
                            p1board.grid[row - 1][col].setFree(false);
                            if (p1board.grid[row - 1][col].gethitmark() == true) {
                                p1board.grid[row - 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row - 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goU = true;
                                rowS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row - 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row - 1][col].getFree() == false) {
                            notFreeLR++;
                        }
                        if (notFreeLR > 1) {//an egklovisteis pare nea eleu8erh row kai col
                            do {
                                rowS = row = rand.nextInt(10);
                                colS = col = rand.nextInt(10);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                    break;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }

                    } else if (col < 1) { //an vriskesai sthn aristerh pleura
                        if (rThree == 0 && p1board.grid[row][col + 1].getFree() == true) { //koitaei deksia
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goR = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col + 1].getFree() == false) {
                            notFreeL++;
                        }

                        if (rThree == 1 && p1board.grid[row + 1][col].getFree() == true) { // koitaei katw                   
                            p1board.grid[row + 1][col].setFree(false);
                            if (p1board.grid[row + 1][col].gethitmark() == true) {
                                p1board.grid[row + 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goD = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row + 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row + 1][col].getFree() == false) {
                            notFreeL++;
                        }

                        if (rThree == 2 && p1board.grid[row - 1][col].getFree() == true) { // koitaei panw                   
                            p1board.grid[row - 1][col].setFree(false);
                            if (p1board.grid[row - 1][col].gethitmark() == true) {
                                p1board.grid[row - 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row - 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goU = true;
                                rowS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row - 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row - 1][col].getFree() == false) {
                            notFreeL++;
                        }

                        if (notFreeL > 2) {//an egklovisteis pare nea eleu8erh row kai col
                            do {

                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                    break;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }
                    } else if (col > gs-2) { //an vriskesai sthn deksia pleura
                        if (rThree == 0 && p1board.grid[row][col - 1].getFree() == true) { //koitaei aristera
                            p1board.grid[row][col - 1].setFree(false);
                            if (p1board.grid[row][col - 1].gethitmark() == true) {
                                p1board.grid[row][col - 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col - 1].setBackground(Color.RED);
                                go27++;
                                goL = true;
                                streak = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col - 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col - 1].getFree() == false) {
                            notFreeR++;
                        }

                        if (rThree == 1 && p1board.grid[row + 1][col].getFree() == true) { // koitaei katw                   
                            p1board.grid[row + 1][col].setFree(false);
                            if (p1board.grid[row + 1][col].gethitmark() == true) {
                                p1board.grid[row + 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goD = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row + 1][col].setBackground(Color.WHITE);

                            }
                            notFreeR = 0;
                            break;
                        } else if (p1board.grid[row + 1][col].getFree() == false) {
                            notFreeL++;
                        }

                        if (rThree == 2 && p1board.grid[row - 1][col].getFree() == true) { // koitaei panw                   
                            p1board.grid[row - 1][col].setFree(false);
                            if (p1board.grid[row - 1][col].gethitmark() == true) {
                                p1board.grid[row - 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row - 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goU = true;
                                rowS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row - 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row - 1][col].getFree() == false) {
                            notFreeR++;
                        }

                        if (notFreeR > 2) {//an egklovisteis pare nea eleu8erh row kai col
                            do {
                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                    break;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }
                    } else if (row < 1) { //an vriskesai sthn panw pleura
                        if (rThree == 0 && p1board.grid[row][col + 1].getFree() == true) { //koitaei deksia
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goR = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col + 1].getFree() == false) {
                            notFreeU++;
                        }
                        if (rThree == 1 && p1board.grid[row][col - 1].getFree() == true) { //koitaei aristera
                            p1board.grid[row][col - 1].setFree(false);
                            if (p1board.grid[row][col - 1].gethitmark() == true) {
                                p1board.grid[row][col - 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col - 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goL = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col - 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col - 1].getFree() == false) {
                            notFreeU++;
                        }
                        if (rThree == 2 && p1board.grid[row + 1][col].getFree() == true) { // koitaei katw                   
                            p1board.grid[row + 1][col].setFree(false);
                            if (p1board.grid[row + 1][col].gethitmark() == true) {
                                p1board.grid[row + 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goD = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row + 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row + 1][col].getFree() == false) {
                            notFreeU++;
                        }

                        if (notFreeU > 2) {//an egklovisteis pare nea eleu8erh row kai col
                            do {

                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                    break;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }
                    } else if (row > 8) { //an vriskesai sthn katw pleura
                        if (rThree == 0 && p1board.grid[row][col + 1].getFree() == true) { //koitaei deksia
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goR = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col + 1].getFree() == false) {
                            notFreeD++;
                        }
                        if (rThree == 1 && p1board.grid[row][col - 1].getFree() == true) { //koitaei aristera
                            p1board.grid[row][col - 1].setFree(false);
                            if (p1board.grid[row][col - 1].gethitmark() == true) {
                                p1board.grid[row][col - 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col - 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goL = true;
                                colS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col - 1].setBackground(Color.WHITE);

                            }
                            break;
                        } else if (p1board.grid[row][col - 1].getFree() == false) {
                            notFreeD++;
                        }
                        if (rThree == 2 && p1board.grid[row - 1][col].getFree() == true) { // koitaei panw                   
                            p1board.grid[row - 1][col].setFree(false);
                            if (p1board.grid[row - 1][col].gethitmark() == true) {
                                p1board.grid[row - 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row - 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goU = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row - 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row - 1][col].getFree() == false) {
                            notFreeD++;
                        }

                        if (notFreeD > 2) {//an egklovisteis pare nea eleu8erh row kai col
                            do {
                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                    break;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }
                    } else { //an den vriskesai se gwnia h pleura
                        if (rFour == 0 && p1board.grid[row][col + 1].getFree() == true) { //koitaei deksia
                            p1board.grid[row][col + 1].setFree(false);
                            if (p1board.grid[row][col + 1].gethitmark() == true) {
                                p1board.grid[row][col + 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col + 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                colS++;
                                goR = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }

                            } else {
                                battlefield.grid1[row][col + 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col + 1].getFree() == false) {
                            notFree++;
                        }

                        if (rFour == 1 && p1board.grid[row][col - 1].getFree() == true) { // koitaei aristera                    
                            p1board.grid[row][col - 1].setFree(false);
                            if (p1board.grid[row][col - 1].gethitmark() == true) {
                                p1board.grid[row][col - 1].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row][col - 1].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goL = true;
                                colS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row][col - 1].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row][col - 1].getFree() == false) {
                            notFree++;
                        }

                        if (rFour == 2 && p1board.grid[row + 1][col].getFree() == true) { // koitaei katw                   
                            p1board.grid[row + 1][col].setFree(false);
                            if (p1board.grid[row + 1][col].gethitmark() == true) {
                                p1board.grid[row + 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row + 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goD = true;
                                rowS++;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row + 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row + 1][col].getFree() == false) {
                            notFree++;
                        }

                        if (rFour == 3 && p1board.grid[row - 1][col].getFree() == true) { // koitaei panw                   
                            p1board.grid[row - 1][col].setFree(false);
                            if (p1board.grid[row - 1][col].gethitmark() == true) {
                                p1board.grid[row - 1][col].sethitmark(false);
                                cpucheck(p1ship, p1board);
                                battlefield.grid1[row - 1][col].setBackground(Color.RED);
                                go27++;
                                streak = true;
                                goU = true;
                                rowS--;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                }
                            } else {
                                battlefield.grid1[row - 1][col].setBackground(Color.WHITE);
                            }
                            break;
                        } else if (p1board.grid[row - 1][col].getFree() == false) {
                            notFree++;
                        }

                        if (notFree > 3) { //an egklovisteis pare nea eleu8erh row kai col
                            do {

                                rowS = row = rand.nextInt(gs);
                                colS = col = rand.nextInt(gs);
                                check = true;

                                if (p1board.grid[row][col].getFree() == true) {
                                    p1board.grid[row][col].setFree(false);
                                    check = false;
                                }
                            } while (check);

                            if (p1board.grid[row][col].gethitmark() == true) {
                                p1board.grid[row][col].sethitmark(false);
                                battlefield.grid1[row][col].setBackground(Color.RED);
                                go27++;
                                cpucheck(p1ship, p1board);
                                alert = true;
                                if (P1ShipsDead(p1ship) > o) {
                                    o++;
                                    alert = false;
                                    break;
                                }

                            } else {
                                battlefield.grid1[row][col].setBackground(Color.WHITE);
                                alert = false;
                            }
                            break;
                        }
                    }
                } else {//if streak == true
                    if (goL == true) { //pame aristera
                        if (colS < 1) { //an pefteis se toixo mhn sunexizeis eu8eia
                            streak = false;
                            goL = false;
                        } else {
                            if (p1board.grid[rowS][colS - 1].getFree() == true) {//einai eleu8ero to kommati gia xtuphma?
                                p1board.grid[rowS][colS - 1].setFree(false);
                                if (p1board.grid[rowS][colS - 1].gethitmark() == true) {
                                    p1board.grid[rowS][--colS].sethitmark(false);
                                    cpucheck(p1ship, p1board);
                                    battlefield.grid1[rowS][colS].setBackground(Color.RED);
                                    go27++;
                                    if (P1ShipsDead(p1ship) > o) {
                                        o++;
                                        alert = false;
                                        streak = false;
                                        goL = false;
                                    }
                                } else {
                                    battlefield.grid1[rowS][colS - 1].setBackground(Color.WHITE);
                                    streak = false;
                                    goL = false;
                                }
                                break;
                            } else {
                                streak = false;
                                goL = false;
                            }
                        }
                    } else if (goR == true) { //pame deksia
                        if (colS > 8) { //an pefteis se toixo mhn sunexizeis eu8eia
                            streak = false;
                            goR = false;
                        } else {
                            if (p1board.grid[rowS][colS + 1].getFree() == true) {//einai eleu8ero to kommati gia xtuphma?
                                p1board.grid[rowS][colS + 1].setFree(false);
                                if (p1board.grid[rowS][colS + 1].gethitmark() == true) {
                                    p1board.grid[rowS][++colS].sethitmark(false);
                                    cpucheck(p1ship, p1board);
                                    battlefield.grid1[rowS][colS].setBackground(Color.RED);
                                    go27++;
                                    if (P1ShipsDead(p1ship) > o) {
                                        o++;
                                        alert = false;
                                        streak = false;
                                        goR = false;
                                    }
                                } else {
                                    battlefield.grid1[rowS][colS + 1].setBackground(Color.WHITE);
                                    streak = false;
                                    goR = false;
                                }
                                break;
                            } else {
                                streak = false;
                                goR = false;
                            }
                        }
                    } else if (goU == true) { //pame panw
                        if (rowS < 1) { //an pefteis se toixo mhn sunexizeis eu8eia
                            streak = false;
                            goU = false;
                        } else {
                            if (p1board.grid[rowS - 1][colS].getFree() == true) {//einai eleu8ero to kommati gia xtuphma?
                                p1board.grid[rowS - 1][colS].setFree(false);
                                if (p1board.grid[rowS - 1][colS].gethitmark() == true) {
                                    p1board.grid[--rowS][colS].sethitmark(false);
                                    cpucheck(p1ship, p1board);
                                    battlefield.grid1[rowS][colS].setBackground(Color.RED);
                                    go27++;
                                    if (P1ShipsDead(p1ship) > o) {
                                        o++;
                                        alert = false;
                                        streak = false;
                                        goU = false;
                                    }
                                } else {
                                    battlefield.grid1[rowS - 1][colS].setBackground(Color.WHITE);
                                    streak = false;
                                    goU = false;
                                }
                                break;
                            } else {
                                streak = false;
                                goU = false;
                            }
                        }
                    } else if (goD == true) {//pame katw
                        if (rowS > gs-2) { //an pefteis se toixo mhn sunexizeis eu8eia
                            streak = false;
                            goD = false;
                        } else {
                            if (p1board.grid[rowS + 1][colS].getFree() == true) {//einai eleu8ero to kommati gia xtuphma?
                                p1board.grid[rowS + 1][colS].setFree(false);
                                if (p1board.grid[rowS + 1][colS].gethitmark() == true) {
                                    p1board.grid[++rowS][colS].sethitmark(false);
                                    cpucheck(p1ship, p1board);
                                    battlefield.grid1[rowS][colS].setBackground(Color.RED);
                                    go27++;
                                    if (P1ShipsDead(p1ship) > o) {
                                        o++;
                                        alert = false;
                                        streak = false;
                                        goD = false;
                                    }
                                } else {
                                    battlefield.grid1[rowS + 1][colS].setBackground(Color.WHITE);
                                    streak = false;
                                    goD = false;
                                }
                                break;
                            } else {
                                streak = false;
                                goD = false;
                            }
                        }
                    }
                }
            } while (alert);

        }
        return (true);
    }

}
