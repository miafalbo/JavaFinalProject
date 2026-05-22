import java.util.ArrayList;

public class WaitForFood implements CustomerState {

    private CustomerSprite customer;
    private boolean foodGiven;
    private ArrayList<SolidSprite> itemList;
    private boolean accurate;

    public WaitForFood(CustomerSprite customer) {
        this.customer = customer;
        foodGiven = false;
        itemList = new ArrayList<SolidSprite>();
        accurate = true;
    }

    public void giveFood() {
        foodGiven = true;
    }

    public void update() {
        if(foodGiven) {
            customer.setState(new CustomerComplete(customer));
        }
    }
}
