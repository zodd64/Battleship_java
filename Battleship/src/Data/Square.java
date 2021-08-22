package Data;

public class Square {

    private String position;
    private boolean free;
    private boolean hitmark;
    private boolean alert;

    /**
     * creates an instance of the Square class
     * @param position 
     */
    public Square(String position) {
        this.position = position;
        this.free = true;
        this.hitmark = false;
        this.alert = false;
    }

    public String getPosition() {
        return this.position;
    }

    public void sethitmark(boolean hitmark) {
        this.hitmark = hitmark;
    }

    public boolean gethitmark() {
        return this.hitmark;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean getFree() {
        return this.free;
    }
}
