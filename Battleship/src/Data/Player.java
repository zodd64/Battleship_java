package Data;

public class Player {

    private String name;
/**
 * creates an instance of the Player class which keeps player names as data
 * @param name 
 */
    public Player(String name) {
        this.name = name;
    }

    public void setPlayer(String name) {
        this.name = name;
    }

    public String getPlayer() {
        return this.name;
    }
}
