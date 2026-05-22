import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainPanel extends JPanel {

    public static final String GAME = "GAME";
    public static final String DRINK = "DRINK";
    public static final String FOOD = "FOOD";
    public static final String PLATE = "PLATE";

    private GameScreen gameScreen;

    private CardLayout layout;

    public MainPanel(JFrame frame) throws Exception {

        layout = new CardLayout();
        setLayout(layout);

        CurrentPlate plate = new CurrentPlate();

        //create panels
        gameScreen = new GameScreen(plate, this);
        DrinkStation drinkStation = new DrinkStation( plate, this);
        FoodStation foodStation = new FoodStation(plate, this);
        PlateStation plateStation = new PlateStation(plate, this);

        //add cards
        add(gameScreen, GAME);
        add(drinkStation, DRINK);
        add(foodStation, FOOD);
        add(plateStation, PLATE);

        layout.show(this, GAME);
    }

    public void showScreen(String screen) {
        layout.show(this, screen);
        if(screen.equals(GAME)) {
            gameScreen.regainFocus();
        }
    }
}
