import java.io.IOException;

public class CustomerRest implements CustomerState {

    private CustomerSprite customer;
    private long startTime;
    private long readyTime;
    private double remainingTime;

    public CustomerRest(CustomerSprite customer) throws IOException {
        this.customer = customer;
        customer.generateOrder();
        startTime = System.currentTimeMillis();

        //generate random amount of time before customer is ready to order
        //update to wait to order when time runs out
        readyTime = startTime + 5000 + (long)(Math.random()*10000);
    }

    @Override
    public void giveFood() {}

    public void update() {
        if(System.currentTimeMillis() >= readyTime) {
            customer.setState(new WaitForFood(customer));
        }
    }
}
