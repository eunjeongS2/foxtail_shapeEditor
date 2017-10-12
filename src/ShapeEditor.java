import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeEditor extends PApplet {

    private List<Shape> shapes = new ArrayList<>();
    private Shape shape;
    private int offsetX;
    private int offsetY;
    private static final String FILE_PATH = "/Users/eunjeong/IdeaProjects/170924 ShapeEditor/src/shape.txt";

    @Override
    public void settings() {
        size(600, 600);
    }

    @Override
    public void setup() {
        background(255);

    }

    @Override
    public void draw() {
        background(255);
        drawShape();
    }

    public static void main(String[] args) {
        PApplet.main("ShapeEditor");

    }

    private void drawShape() {
        for (Shape e : shapes) {
            e.draw(this);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);

        if (key == '1') {
            shape = new Rect(255,1);
        } else if (key == '2') {
            shape = new Circle(255,2);
        } else if (key == '3') {
            shape = new Triangle(255,3);
        } else if (key == 'd') {

            shape = selectShape(mouseX, mouseY);

            if (shape == null) {
                return;
            }

            shape = shape.clone();
            shape.setX(shape.getX() + 40);
            shapes.add(shape);
            shape = null;
        } else if (key == 's'){
            saveFile();
        } else if (key == 'o'){
            getFile();
        }

    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (shape == null) {
            return;
        }

        shape.setX(mouseX);
        shape.setY(mouseY);

        shapes.add(shape);
        shape = null;

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (shape == null) {
            shape = selectShape(mouseX, mouseY);
        }else {

            shape.setColor(128);
            shape.setX(mouseX - offsetX);
            shape.setY(mouseY - offsetY);
        }
    }

    @Override
    public void mouseReleased() {
        if (shape == null) {
            return;
        }
        shape.setColor(255);
        shape=null;
        offsetX = 0;
        offsetY = 0;
    }

    private Shape selectShape(int x, int y) {
        for(int i=shapes.size()-1; i>=0; i--){
            if(shapes.get(i).findShape(x,y)){
                offsetX = x-shapes.get(i).getX();
                offsetY = y-shapes.get(i).getY();
                return shapes.get(i);
            }
        }
        return null;
    }

    private void saveFile(){
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            for(Shape shape : shapes){
                bos.write((shape.getType()+":"+shape.getX()+":"+shape.getY()+"\n").getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getFile(){
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int ch;
            String str="";

            while ((ch = bis.read()) != -1) {
                str +=(char) ch;
            }

            getShape(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getShape(String str){
        String data[] = str.split("\n");
        String strings[];
        Shape shape = null;

        reset();

        for(String s : data){
            strings = s.split(":");
            int type = parseInt(strings[0]);
            int x = parseInt(strings[1]);
            int y = parseInt(strings[2]);

            switch (type){
                case 1 :
                    shape = new Rect(255, 1);
                    break;
                case 2 :
                    shape = new Circle(255, 2);
                    break;
                case 3 :
                    shape = new Triangle(255, 3);
                    break;
            }

            if(shape == null){
                return;
            }
            shape.setX(x);
            shape.setY(y);
            shapes.add(shape);
        }
    }

    private void reset() {
        shapes = new ArrayList<>();
    }

}