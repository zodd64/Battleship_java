package Data;

public class Grid {

    private Player owner;
    public Square grid[][];
    public  int gs=10;

    /**
     * creates an instance of the Grid class which in turn initializes a 2D Array of squares
     * @param owner 
     */
    public Grid(Player owner) {
        this.owner = owner;
        this.grid = new Square[gs][gs];
        initialize();
    }

    public Square[][] GetGrid() {
        return this.grid;
    }

    public Player GetOwner() {
        return this.owner;
    }

    private void initialize() {
        char letter = 'A';
        int num;
        for (int i = 0; i < gs; i++) {
            num = 1;
            for (int j = 0; j < gs; j++) {
                grid[i][j] = new Square(letter + Integer.toString(num));
                num++;
            }
            letter++;
        }
    }
}
