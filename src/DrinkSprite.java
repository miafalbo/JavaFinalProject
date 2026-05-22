import java.awt.*;

public class DrinkSprite extends SolidSprite {

    private Drink drink = Drink.COFFEE;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public DrinkSprite(Image image, double x, double y, int width, int height, Drink drink) {
        super(image, x, y, width, height);
        this.drink = drink;

        //get correct coordinates for sprite, depending on type of drink
        switch(drink.getCurrentDrink()) {
            case(0):
                x1 = 33;
                x2 = 48;
                break;
            case(1):
                x1 = 0;
                x2 = 15;
                break;
            case(2):
                x1 = 64;
                x2 = 79;
        }
        y1 = 0;
        y2 = 15;
    }

    public Drink getDrink() {
        return drink;
    }

    public void changePosition(int x, int y, int width, int height) {
        super.setX(x);
        super.setY(y);
        super.setWidth(width);
        super.setHeight(height);
    }

    public String getDrinkName() {
        switch(drink.getCurrentDrink()) {
            case(0) :
                return "Coffee";
            case(1) :
                return "Tea";
        }
        return "Chocolat chaud";
    }

    @Override
    public void draw(Graphics g) {

        int spriteWidth = (int)(super.getX() + super.getWidth());
        int spriteHeight = (int)(super.getY() + super.getHeight());

        //draw the image
        g.drawImage(super.getImage(), (int)super.getX(), (int)super.getY(), spriteWidth, spriteHeight, x1, y1, x2, y2, null);
    }
}
