import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {

    //private variables
    private double speed = 5;
    private final int spriteSheetNumberOfColumn = 1;
    private int timeBetweenFrame = 200;
    private Direction direction = Direction.NORTH;
    private boolean atMachine = false;
    private boolean atFood = false;
    private boolean atCustomer = false;
    private CustomerSprite currentCustomer;

    //constructor
    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    //direction setter method
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //position setter method
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    //draws the correct frame for the sprite
    @Override
    public void draw(Graphics g) {

        int index = (int)(System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);
        int attitude = direction.getFrameLineNumber();

        int xPos1 = 0;
        int xPos2 = 0;
        //draw the image using drawImage
        if(attitude == 0 || attitude == 1) {
            xPos1 = 88;
            xPos2 = 177;
        } else {
            xPos1 = 0;
            xPos2 = 88;
        }

        int yPos1 = 0;
        int yPos2 = 164;

        int spriteWidth = (int)(super.getX() + super.getWidth());
        int spriteHeight = (int)(super.getY() + super.getHeight());

        g.drawImage(super.getImage(), (int)super.getX(), (int)super.getY(), spriteWidth, spriteHeight, (int)xPos1, yPos1, xPos2, yPos2, null);
    }

    //moves the sprite in the correct direction
    private void move() {
        switch (direction.getFrameLineNumber()) {
            //NORTH
            case (2):
                super.setY(super.getY() - speed);
                break;
            //SOUTH
            case (0):
                super.setY(super.getY() + speed);
                break;
            //EAST
            case (3):
                super.setX(super.getX() + speed);
                break;
            //WEST
            case (1):
                super.setX(super.getX() - speed);
                break;
            default:
                break;

        }
    }

    //checks if the sprite will hit any objects if it moves
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        double nextX = super.getX();
        double nextY = super.getY();
        switch (direction.getFrameLineNumber()) {
            //NORTH
            case (2):
                nextY-=speed;
                break;
            //SOUTH
            case (0):
                nextY+=speed;
                break;
            //EAST
            case (3):
                nextX+=speed;
                break;
            //WEST
            case (1):
                nextX-=speed;
                break;
            default:
                break;
        }

        Rectangle2D.Double hitBox = new Rectangle2D.Double(nextX, nextY, super.getWidth(), super.getHeight());

        // return false if the element is a SolidSprite, there is an intersection, and the element is NOT
        // the DynamicSprite itself
        // return true otherwise
        for(int i=0; i<environment.size(); i++) {

            //create a rectangle for the current sprite
            Sprite currentSprite = environment.get(i);
            Rectangle2D.Double currentSpriteBox = new Rectangle2D.Double(currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth(), currentSprite.getHeight());

            //check if the two sprite rectangles overlap
            if((currentSprite instanceof CounterSprite) && hitBox.intersects(currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth(), currentSprite.getHeight())) {
                switch((currentSprite.getType()).getCurrentType()) {
                    case(0) :
                        atMachine = false;
                        atFood = false;
                        break;
                    case(1) :
                        atMachine = true;
                        break;
                    case(2) :
                        atFood = true;
                        break;
                }
                return false;
            } else if ((currentSprite instanceof CustomerSprite) && hitBox.intersects(currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth(), currentSprite.getHeight())) {
                currentCustomer = (CustomerSprite) currentSprite;
                atCustomer = true;
                return false;
            }
        }
        atMachine = false;
        atFood = false;
        atCustomer = false;
        return true;
    }

    //getter methods for checking if character is at stations
    public boolean atMachine() {
        return atMachine;
    }

    public boolean atCustomer() {
        return atCustomer;
    }

    public boolean atFood() {
        return atFood;
    }

    public CustomerSprite getCurrentCustomer() {
        if(atCustomer) {
            return currentCustomer;
        }
        return null;
    }

    //moves the sprite in the correct direction if nothing is in the way
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

}