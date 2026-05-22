import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.cos;

public class PlateStation extends JPanel {

    private CurrentPlate currentPlate;
    private SolidSprite plateSprite;

    public PlateStation(CurrentPlate plate, MainPanel mainPanel) throws IOException {
        this.currentPlate = plate;

        //back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 200, 50);
        add(backButton);
        backButton.addActionListener(e -> {
            mainPanel.showScreen(MainPanel.GAME);
        });

        File plateImage = new File("src/Assets/plate.png");
        plateSprite = new SolidSprite(ImageIO.read(plateImage), 40, 30, 550, 550);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw table
        g.setColor(new Color(115, 76, 54));
        g.fillRect(0, 0, 1000, 6000);

        //draw plate
        plateSprite.setPosition(1, 341, 1, 335);
        plateSprite.draw(g);

        //draw all items in the current plate
        ArrayList<SolidSprite> itemList = currentPlate.getItemList();
        int numItems = itemList.size();
        double angle = (2*Math.PI)/(double)numItems;
        double currentAngle = 0;

        for(int i=0; i<numItems; i++) {

            //calculate position of item on plate
            int x1 = 280 + (int)(160*Math.cos(currentAngle));
            int y1 = 275 + (int)(160*Math.sin(currentAngle));

            (itemList.get(i)).changePosition(x1, y1, 100, 100);
            (itemList.get(i)).draw(g);

            currentAngle += angle;
        }

    }
}
