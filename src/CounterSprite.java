import java.awt.*;

public class CounterSprite extends SolidSprite implements Displayable {

    private Image image;
    private double x;
    private double y;
    private CounterType type;

    public CounterSprite(Image image, double x, double y, double width, double height, CounterType type) {
        super(image, x, y, width, height);
        this.image = image;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public CounterType getType() {
        return type;
    }

    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

}