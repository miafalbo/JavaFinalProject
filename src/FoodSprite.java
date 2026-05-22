import java.awt.*;

public class FoodSprite extends SolidSprite {

    private Food food = Food.CROISSANT;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public FoodSprite(Image image, double x, double y, int width, int height, Food food) {
        super(image, x, y, width, height);
        this.food = food;

        //set correct sprite coordinates depending on type of food
        if(food == Food.PAIN_CHOC) {
            x1 = 63;
            x2 = 79;
        } else {
            x1 = 0;
            x2 = 15;
        }

        if(food == Food.PAIN_SUISSE) {
            y1 = 79;
            y2 = 95;
        } else {
            y1 = 48;
            y2 = 64;
        }
    }

    public void changePosition(int x, int y, int width, int height) {
        super.setX(x);
        super.setY(y);
        super.setWidth(width);
        super.setHeight(height);
    }

    public Food getFood() {
        return food;
    }

    public String getFoodName() {
        switch(food.getCurrentFood()) {
            case(0) :
                return "Croissant";
            case(1) :
                return "Pain au chocolat";
        }
        return "Pain suisse";
    }

    @Override
    public void draw(Graphics g) {

        int spriteWidth = (int)(super.getX() + super.getWidth());
        int spriteHeight = (int)(super.getY() + super.getHeight());

        //draw the image
        g.drawImage(super.getImage(), (int)super.getX(), (int)super.getY(), spriteWidth, spriteHeight, x1, y1, x2, y2, null);
    }

}
