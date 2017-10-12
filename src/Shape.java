import processing.core.PApplet;

public abstract class Shape implements Cloneable {
    private int x;
    private int y;
    private int color;
    private int type;

    public Shape(int color, int type) {
        this.color = color;
        this.type = type;
    }

    public void setType(int type) { this.type = type; }

    public int getType() {
        return type;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(PApplet pApplet);

    public abstract boolean findShape(int posX, int posY);

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
