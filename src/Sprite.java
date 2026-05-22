import java.awt.*;
import java.io.IOException;

public class Sprite implements Displayable {

    //private variables
    private Image image;
    //position variables
    private double x;
    private double y;
    //size variables
    private double width;
    private double height;

    //constructor
    public Sprite (Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public CounterType getType() {
        return CounterType.COUNTER;
    }

    //displays the image at position (x,y)
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    //setter methods
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setImage(Image image) { this.image = image; }

    //getter methods
    public Image getImage() {
        return image;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
}
