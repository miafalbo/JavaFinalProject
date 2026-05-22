import java.awt.*;
import java.io.IOException;

public class SolidSprite extends Sprite {

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public SolidSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    public void setPosition(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void changePosition(int x, int y, int width, int height) {
    }

    @Override
    public void draw(Graphics g) {

        int spriteWidth = (int)(super.getX() + super.getWidth());
        int spriteHeight = (int)(super.getY() + super.getHeight());

        g.drawImage(super.getImage(), (int)super.getX(), (int)super.getY(), spriteWidth, spriteHeight, x1, y1, x2, y2, null);

    }
}
