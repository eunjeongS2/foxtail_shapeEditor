import processing.core.PApplet;

public class Triangle extends Shape{

    public Triangle(int color, int type) {
        super(color, type);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(getColor());
        pApplet.triangle(getX() - 20, getY() + 20, getX(), getY() - 20, getX() + 20, getY() + 20);
    }

    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }

    @Override
    public boolean findShape(int posX, int posY){
        double a1 = (getX()-(0.5)*getY()+10-posX+0.5*posY)*0.025;
        double a2 = (20+getY()-posY)*0.025;
        double a3 = (10-getX()-(getY()*0.5)+posX+(posY*0.5))*0.025;

        return a1>=0 && a2>=0 && a3>=0;
    }
}
