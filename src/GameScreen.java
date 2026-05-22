import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameScreen extends JPanel {

    private MainPanel mainPanel;
    CurrentPlate plate;

    public GameScreen(CurrentPlate plate, MainPanel mainPanel) throws IOException {

        this.mainPanel = mainPanel;
        this.plate = plate;
        setLayout(null);

        //load sprite and level
        File sprites = new File("src/Assets/studentSprite.png");
        String pathname = "src/Assets/level/level0.txt";

        //create main character sprite and game engines
        DynamicSprite hero = new DynamicSprite(ImageIO.read(sprites), 200, 200, 64, 64);

        PhysicsEngine physicsEngine = new PhysicsEngine();
        GameEngine gameEngine = new GameEngine(hero);
        RenderEngine renderEngine = new RenderEngine(gameEngine, mainPanel, hero, plate, this);

        gameEngine.setRenderEngine(renderEngine);
        gameEngine.setPhysicsEngine(physicsEngine);

        Playground playground = new Playground(pathname, renderEngine);
        renderEngine.setRenderList(playground.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicsEngine.setEnvironment(playground.getSolidSpriteList());

        physicsEngine.addToMovingSpriteList(hero);

        //initiate and start timers
        Timer renderTimer = new Timer(50, (time)-> renderEngine.update());
        Timer gameTimer = new Timer(50, (time)-> gameEngine.update());
        Timer physicsTimer = new Timer(50, (time) -> physicsEngine.update());
        renderTimer.start();
        gameTimer.start();
        physicsTimer.start();

        //add render engine to frame
        renderEngine.setBounds(0, 0, 1000, 700);
        add(renderEngine);

        // KEYBOARD
        setFocusable(true);
        addKeyListener(gameEngine);

        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow();
        });
    }

    public void regainFocus() {
        requestFocusInWindow();
    }
}
