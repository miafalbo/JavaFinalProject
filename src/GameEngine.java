import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements KeyListener, Engine {

    //private variables
    private final DynamicSprite hero;
    private boolean spacePressed = false;

    private RenderEngine renderEngine;
    private PhysicsEngine physicsEngine;

    //constructor
    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    //setter methods
    public void setRenderEngine(RenderEngine renderEngine) {
        this.renderEngine = renderEngine;
    }

    public void setPhysicsEngine(PhysicsEngine physicsEngine) {
        this.physicsEngine = physicsEngine;
    }

    //keyPressed changes the direction of the sprite
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            //left arrow
            case (KeyEvent.VK_LEFT):
                hero.setDirection(Direction.WEST);
                break;
            // up arrow
            case (KeyEvent.VK_UP):
                hero.setDirection(Direction.NORTH);
                break;
            //right arrow
            case (KeyEvent.VK_RIGHT):
                hero.setDirection(Direction.EAST);
                break;
            //down arrow
            case (KeyEvent.VK_DOWN):
                hero.setDirection(Direction.SOUTH);
                break;
            case (KeyEvent.VK_SPACE):
                spacePressed = true;
                break;
            //otherwise, do not change anything
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void update() {


    }


}
