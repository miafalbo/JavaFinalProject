import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FoodStation extends JPanel {

    private FoodSprite croissant;
    private FoodSprite painChoc;
    private FoodSprite painSuisse;

    private JButton croisStartButton;
    private JButton chocStartButton;
    private JButton suisseStartButton;

    public FoodStation(CurrentPlate plate, MainPanel mainPanel) throws IOException {
        File foodImage = new File("src/Assets/foodSprites.png");

        //creat food sprites
        croissant = new FoodSprite(ImageIO.read(foodImage), 25, 270, 130, 130, Food.CROISSANT);
        painChoc = new FoodSprite(ImageIO.read(foodImage), 220, 265, 130, 130, Food.PAIN_CHOC);
        painSuisse = new FoodSprite(ImageIO.read(foodImage), 430, 260, 130, 130, Food.PAIN_SUISSE);

        //back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 200, 50);
        add(backButton);
        backButton.addActionListener(e -> {
            mainPanel.showScreen(MainPanel.GAME);
        });

        //create collect buttons
        setLayout(null);
        croisStartButton = new JButton("COLLECT");
        chocStartButton = new JButton("COLLECT");
        suisseStartButton = new JButton("COLLECT");

        croisStartButton.setBounds(50, 420, 100, 40);
        chocStartButton.setBounds(250, 420, 100, 40);
        suisseStartButton.setBounds(450, 420, 100, 40);

        add(croisStartButton);
        add(chocStartButton);
        add(suisseStartButton);

        //add item to current plate when collect button is pressed
        croisStartButton.addActionListener(e -> {
            plate.addItem(croissant);
        });
        chocStartButton.addActionListener(e -> {
            plate.addItem(painChoc);
        });
        suisseStartButton.addActionListener(e -> {
            plate.addItem(painSuisse);
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw counter
        g.setColor(new Color(115, 76, 54));
        g.fillRect(0, 340, 1000, 6000);

        //set food positions
        croissant.changePosition(25, 270, 130, 130);
        painChoc.changePosition(220, 265, 130, 130);
        painSuisse.changePosition(430, 260, 130, 130);

        //draw food
        croissant.draw(g);
        painChoc.draw(g);
        painSuisse.draw(g);
    }


}
