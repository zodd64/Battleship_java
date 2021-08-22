package Data;

public class Turn {

    private Player current;
    /**
     * creates an instance of the Turn class
     * @param current 
     */
    public Turn(Player current) {
        this.current = current;
    }

    public void SetTurn(Player current) {
        this.current = current;
    }

    public Player GetTurn() {
        return this.current;
    }
}
