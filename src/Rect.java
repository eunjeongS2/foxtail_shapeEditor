import processing.core.PApplet;

public class Rect extends Shape{

    public Rect(int color, int type) {
        super(color, type);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(getColor());
        pApplet.rect(getX() - 15, getY() - 15, 30, 30);
    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }

    @Override
    public boolean findShape(int posX, int posY){
        return posX <= getX() + 15 && posX >= getX() - 15 && posY <= getY() + 15 && posY >= getY() - 15;
    }
}
