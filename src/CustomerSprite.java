import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomerSprite extends SolidSprite {

    private ArrayList<SolidSprite> order;
    private CustomerState state;
    private boolean correctOrder;
    private RenderEngine game;
    private Image image;
    private double x;
    private double y;
    private boolean visible;
    private Image readyImage;
    private Image notReadyImage;

    public CustomerSprite(Image image, double x, double y, double width, double height, RenderEngine game) throws IOException {
        super(image, x, y, width, height);
        order = new ArrayList<SolidSprite>();
        state = new CustomerRest(this);
        this.x = x;
        this.y = y;
        this.game = game;
        this.image = image;
        visible = false;
        correctOrder = true;

        //store two images of customer counter blocks
        File notReady = new File("src/Assets/counter.png");
        File ready = new File("src/Assets/orderWait.png");
        notReadyImage = ImageIO.read(notReady);
        readyImage = ImageIO.read(ready);
    }

    public void setState(CustomerState state) {
        this.state = state;
    }

    public CustomerState getState() {
        return state;
    }

    public boolean checkAccuracy(ArrayList<SolidSprite> itemList) {
        //check if order is accurate

        //count number of drinks and number of food items in each list
        int orderCoffee = 0;
        int orderTea = 0;
        int orderChoc = 0;
        int orderCroiss = 0;
        int orderPainChoc = 0;
        int orderPainSuisse = 0;

        int itemCoffee = 0;
        int itemTea = 0;
        int itemChoc = 0;
        int itemCroiss = 0;
        int itemPainChoc = 0;
        int itemPainSuisse = 0;

        //first, check if lists are the same size
        if(order.size() == itemList.size()) {
            //then count how many of each drink and food are in each list
            for(int i=0; i<order.size(); i++) {
                //add drink to count
                if(order.get(i) instanceof DrinkSprite) {
                    DrinkSprite currentSprite = (DrinkSprite) order.get(i);
                    switch(currentSprite.getDrink().getCurrentDrink()) {
                        case(0) :
                            itemCoffee++;
                            break;
                        case(1) :
                            itemTea++;
                            break;
                        case(2) :
                            itemChoc++;
                            break;
                    }
                } else {
                    //add food to count
                    FoodSprite currentSprite = (FoodSprite) order.get(i);
                    switch(currentSprite.getFood().getCurrentFood()) {
                        case(0) :
                            itemCroiss++;
                            break;
                        case(1) :
                            itemPainChoc++;
                            break;
                        case(2) :
                            itemPainSuisse++;
                            break;
                    }
                }

                //add drink to count
                if(itemList.get(i) instanceof DrinkSprite) {
                    DrinkSprite currentSprite = (DrinkSprite) itemList.get(i);
                    switch(currentSprite.getDrink().getCurrentDrink()) {
                        case(0) :
                            orderCoffee++;
                            break;
                        case(1) :
                            orderTea++;
                            break;
                        case(2) :
                            orderChoc++;
                            break;
                    }
                } else {
                    //add food to count
                    FoodSprite currentSprite = (FoodSprite) itemList.get(i);
                    switch(currentSprite.getFood().getCurrentFood()) {
                        case(0) :
                            orderCroiss++;
                            break;
                        case(1) :
                            orderPainChoc++;
                            break;
                        case(2) :
                            orderPainSuisse++;
                            break;
                    }
                }
            }

            //check if there is the same number of each item
            if((orderCoffee == itemCoffee) && (orderTea == itemTea) && (orderChoc == itemChoc) && (orderCroiss == itemCroiss) && (orderPainChoc == itemPainChoc) && (orderPainSuisse == itemPainSuisse)) {
                correctOrder = true;
            } else {
                correctOrder = false;
            }

        } else {
            correctOrder = false;
        }

        //update state of the customer
        state.giveFood();

        //add 500 points to score if the order was accurate
        if(correctOrder) {
            game.addToScore(500);
            return true;
        }

        correctOrder = false;
        return false;
    }

    public ArrayList<SolidSprite> getOrder() {
        return order;
    }

    public void generateOrder() throws IOException {
        //generate random number of drinks and food for order
        double numDrinks = 1 + Math.round(Math.random()*3);
        double numFood = Math.round(Math.random()*5);

        File food = new File("src/Assets/foodSprites.png");
        Image foodImage = ImageIO.read(food);

        //generate random types of drinks
        for(int i=0; i<numDrinks; i++) {
            int drinkType = (int)Math.round(Math.random()*2);
            switch(drinkType){
                case(0):
                    order.add(new DrinkSprite(foodImage, 25, 270, 130, 130, Drink.COFFEE));
                    break;
                case(1):
                    order.add(new DrinkSprite(foodImage, 25, 270, 130, 130, Drink.TEA));
                    break;
                case(2):
                    order.add(new DrinkSprite(foodImage, 25, 270, 130, 130, Drink.CHOC_CHAUD));
                    break;
            }
        }

        //generate random types of food
        for(int i=(int)numDrinks; i<numDrinks+numFood; i++) {
            int foodType = (int)(Math.round(Math.random()*2));

            switch(foodType){
                case(0):
                    order.add(new FoodSprite(foodImage, 25, 270, 130, 130, Food.CROISSANT));
                    break;
                case(1):
                    order.add(new FoodSprite(foodImage, 25, 270, 130, 130, Food.PAIN_CHOC));
                    break;
                case(2):
                    order.add(new FoodSprite(foodImage, 25, 270, 130, 130, Food.PAIN_SUISSE));
            }

        }

    }

    public void setOrder(ArrayList<SolidSprite> order) {
        this.order = order;
    }

    public void update() {
        state.update();
        if(state instanceof WaitForFood) {
            visible = true;
        } else {
            visible = false;
        }
    }

    public void draw(Graphics g){
        //if the customer is ready to order, display exclamation
        if(visible) {
            image = readyImage;
        } else {
            image = notReadyImage;
        }
        g.drawImage(image, (int)x, (int)y, null);
    }

}
