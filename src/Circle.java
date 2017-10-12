import processing.core.PApplet;

public class Circle extends Shape {

    public Circle(int color, int type) {
        super(color, type);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(getColor());
        pApplet.ellipse(getX(), getY(), 40, 40);
    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

    @Override
    public boolean findShape(int posX, int posY){
        return (posX - getX()) * (posX - getX()) + (posY - getY()) * (posY - getY()) <= 20 * 20;
    }
}
