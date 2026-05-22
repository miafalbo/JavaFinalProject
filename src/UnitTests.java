import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {

    @Test
    public void testAddToPlate() {
        CurrentPlate plate = new CurrentPlate();
        FoodSprite croissant = new FoodSprite(null, 1, 1, 1, 1, Food.CROISSANT);
        plate.addItem(croissant);
        assertEquals(1, plate.getItemList().size());
    }

    @Test
    public void testEmptyPlate() {
        CurrentPlate plate = new CurrentPlate();
        assertTrue(plate.getItemList().isEmpty());
    }

    @Test
    public void testCustomerOrderGenerated() throws IOException {
        CustomerSprite customer = new CustomerSprite(null, 1, 1, 1, 1, null);
        customer.generateOrder();
        assertTrue(customer.getOrder().size() > 0);
    }

    @Test
    public void testCorrectOrder() throws IOException {

        CurrentPlate plate = new CurrentPlate();
        RenderEngine render = new RenderEngine(null, null, null, null, null);
        CustomerSprite customer = new CustomerSprite(null, 1, 1, 1, 1, render);

        FoodSprite croissant = new FoodSprite(null, 1, 1, 1, 1, Food.CROISSANT);
        DrinkSprite coffee = new DrinkSprite(null, 1, 1, 1, 1, Drink.COFFEE);

        plate.addItem(croissant);
        plate.addItem(coffee);

        ArrayList<SolidSprite> order = new ArrayList<>();
        order.add(croissant);
        order.add(coffee);

        customer.setOrder(order);

        assertTrue(customer.checkAccuracy(plate.getItemList()));
    }

    @Test
    public void testIncorrectOrder() throws IOException {

        CurrentPlate plate = new CurrentPlate();
        RenderEngine render = new RenderEngine(null, null, null, null, null);
        CustomerSprite customer = new CustomerSprite(null, 1, 1, 1, 1, render);

        FoodSprite croissant = new FoodSprite(null, 1, 1, 1, 1, Food.CROISSANT);
        DrinkSprite coffee = new DrinkSprite(null, 1, 1, 1, 1, Drink.COFFEE);

        plate.addItem(croissant);

        ArrayList<SolidSprite> order = new ArrayList<>();
        order.add(croissant);
        order.add(coffee);

        customer.setOrder(order);

        assertFalse(customer.checkAccuracy(plate.getItemList()));
    }

    @Test
    public void testScoreIncrease() {

        RenderEngine game = new RenderEngine(null, null, null, null, null);
        game.addToScore(500);
        assertEquals(500, game.getScore());
    }

    @Test
    public void testMachineStateChange() {
        MachineSprite machine = new MachineSprite(null, 1, 1, 1, 1, Drink.COFFEE);
        machine.setState(new MachineBrew(machine));
        assertTrue(machine.getState() instanceof MachineBrew);
    }
    
}
