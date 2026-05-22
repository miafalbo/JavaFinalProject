import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Graphics;

public class RenderEngine extends JPanel implements Engine {

    //private variables
    private ArrayList<Displayable> renderList;
    private long startTime;
    private long milli;
    private long seconds;
    private boolean timerRunning;
    private GameEngine gameEngine;
    private DynamicSprite hero;
    private CustomerSprite currentCustomer;
    private CurrentPlate plate;
    private int totalScore = 0;
    private GameScreen gameScreen;

    //buttons
    JButton drinkButton;
    JButton foodButton;
    JButton orderButton;

    private MainPanel mainPanel;

    //constructor
    public RenderEngine(GameEngine gameEngine, MainPanel mainPanel, DynamicSprite hero, CurrentPlate plate, GameScreen gameScreen) {
        renderList  = new ArrayList<Displayable>();
        startTime = System.currentTimeMillis();
        timerRunning = true;
        this.gameEngine = gameEngine;
        this.mainPanel = mainPanel;
        this.hero = hero;
        this.plate = plate;
        this.gameScreen = gameScreen;

        setLayout(null);

        //create button for drink station
        drinkButton = new JButton("Drink Station");
        drinkButton.setBounds(840, 100, 140, 50);
        drinkButton.addActionListener(e -> {
            mainPanel.showScreen(MainPanel.DRINK);
            requestFocusInWindow();
        });

        //create button for food station
        foodButton = new JButton("Food Station");
        foodButton.setBounds(840, 200, 140, 50);
        foodButton.addActionListener(e -> {
            mainPanel.showScreen(MainPanel.FOOD);
            requestFocusInWindow();
        });

        //create button for serving order
        orderButton = new JButton("Serve Order");
        orderButton.setBounds(840, 400, 140, 50);
        orderButton.addActionListener(e -> {
            currentCustomer.checkAccuracy(plate.getItemList());
            plate.clearPlate();
            gameScreen.regainFocus();
        });
        orderButton.setFocusable(false);

        //create button for current plate
        JButton plateButton = new JButton("Current Plate");
        plateButton.setBounds(840, 300, 140, 50);
        plateButton.addActionListener(e -> {
            mainPanel.showScreen(MainPanel.PLATE);
            requestFocusInWindow();
        });

        //add buttons without visibility
        add(drinkButton);
        add(foodButton);
        add(orderButton);
        drinkButton.setVisible(true);
        foodButton.setVisible(true);
        orderButton.setVisible(true);

        add(plateButton);
    }

    //render list methods
    public void setRenderList(ArrayList<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    //paint method displays correct level on screen
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        //draw each item that is in the render list
        for(int i=0; i<renderList.size(); i++) {
            renderList.get(i).draw(g);
        }

        //draw the name of the game on the screen
        g.setColor(new Color(38, 17, 4));
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Le Cafe", 25, 50);

        //display total score on bottom right corner of the screen
        g.setColor(new Color(38, 17, 4));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Current Score:", 840, 600);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString(String.valueOf(totalScore), 840, 630);


        CustomerSprite customer = hero.getCurrentCustomer();
        if(customer != null) {
            g.setColor(Color.WHITE);
            g.fillRect(250, 200, 250, 120);
            g.setColor(Color.BLACK);
            g.drawRect(250, 200, 250, 120);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("ORDER:", 270, 230);

            ArrayList<SolidSprite> itemList = customer.getOrder();
            int x = 270;
            int foodY = 250;
            int drinkY = 250;

            if(customer.getState() instanceof WaitForFood) {
                for(int i=0; i<itemList.size(); i++) {
                    if(itemList.get(i) instanceof FoodSprite) {
                        x = 370;
                        g.setFont(new Font("Arial", Font.PLAIN, 10));
                        g.drawString(((FoodSprite) itemList.get(i)).getFoodName(), x, foodY);
                        foodY += 15;
                    } else {
                        x = 270;
                        g.setFont(new Font("Arial", Font.PLAIN, 10));
                        g.drawString(((DrinkSprite) itemList.get(i)).getDrinkName(), x, drinkY);
                        drinkY += 15;
                    }
                }
            }

        }


    }

    //paints the component regularly
    @Override
    public void update() {
        if(hero.atMachine()) {
            drinkButton.setVisible(true);
            foodButton.setVisible(false);
            orderButton.setVisible(false);
        } else if (hero.atFood()) {
            drinkButton.setVisible(false);
            foodButton.setVisible(true);
            orderButton.setVisible(false);
        } else if (hero.atCustomer()) {
            currentCustomer = hero.getCurrentCustomer();
            orderButton.setVisible(true);
            currentCustomer.update();
        } else {
            drinkButton.setVisible(false);
            foodButton.setVisible(false);
            orderButton.setVisible(false);
        }
        repaint();
    }

    public void addToScore(int score) {
        totalScore += score;
    }

    public int getScore() {
        return totalScore;
    }
}
