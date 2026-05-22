import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main{

    JFrame displayZoneFrame;

    public Main() throws Exception{

        JFrame frame = new JFrame("Cafe Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel(frame);
        frame.add(mainPanel);
        frame.setVisible(true);

//        File Image = new File("src/Assets/foodSprites.png");
//        CustomerSprite customer = new CustomerSprite(ImageIO.read(Image), 25, 270, 130, 130);
//        customer.generateOrder();
        
    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }

}