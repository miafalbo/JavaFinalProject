import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MachineSprite extends SolidSprite {

    private MachineState state;
    private Drink drink = Drink.COFFEE;
    private long brewTime;
    private long remainingTime;
    private DrinkSprite currentDrink;

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public MachineSprite(Image image, double x, double y, int width, int height, Drink drink) {
        super(image, x, y, width, height);
        state = new MachineRest(this);
        this.drink = drink;
    }

    public void pressButton(CurrentPlate plate) throws IOException {
        if(state instanceof MachineComplete) {
            File drinkImage = new File("src/Assets/foodSprites.png");
            currentDrink = new DrinkSprite(ImageIO.read(drinkImage), 0, 0, 100, 100, drink);
            plate.addItem(currentDrink);
        }
        state.pressButton();
    }

    public void setRemainingTime(long time) {
        remainingTime = time;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setBrewTime(long time) {
        brewTime = time;
    }

    public long getBrewTime() {
        return brewTime;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public Drink getDrink() {
        return drink;
    }

    @Override
    public void draw(Graphics g) {

        int spriteWidth = (int)(super.getX() + super.getWidth());
        int spriteHeight = (int)(super.getY() + super.getHeight());

        //draw the image
        g.drawImage(super.getImage(), (int)super.getX(), (int)super.getY(), spriteWidth, spriteHeight, x1, y1, x2, y2, null);
    }

    public void getPic(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void update() {
        state.update();
    }

}
