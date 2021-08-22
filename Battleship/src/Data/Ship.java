package Data;

public class Ship {

    private String type;
    private int size;
    private int hp;
    private int placedrot;
    private int cordx;
    private int cordy;

    private Player owner;
/**
 * creates an instance of the Ship class which is used to store each ship's information
 * @param type
 * @param size
 * @param owner 
 */
    public Ship(String type, int size, Player owner) {
        this.type = type;
        this.size = size;
        this.owner = owner;
        this.hp = size;
        int placedrot;
        int cordx;
        int cordy;
    }

    public int getShip() {
        return this.size;
    }

    public String getShipName() {
        return this.type;
    }

    public void SetRot(int placedrot) {
        this.placedrot = placedrot;
    }

    public int GetRot() {
        return placedrot;
    }

    public void SetCordx(int cordx) {
        this.cordx = cordx;
    }

    public int GetCordx() {
        return cordx;
    }

    public void SetCordy(int cordy) {
        this.cordy = cordy;
    }

    public int GetCordy() {
        return cordy;
    }

    public void SetHp(int hp) {
        this.hp = hp;
    }

    public int GetHp() {
        return hp;
    }

}
