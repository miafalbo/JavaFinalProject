import java.util.ArrayList;

public class PhysicsEngine implements Engine {

    //private variables
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<Sprite> environment;

    //constructor
    public PhysicsEngine() {
        movingSpriteList = new ArrayList<DynamicSprite>();
        environment = new ArrayList<Sprite>();
    }

    public void addToMovingSpriteList(DynamicSprite newSprite) {
        movingSpriteList.add(newSprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    @Override
    public void update() {
        for(int i=0; i<movingSpriteList.size(); i++) {
            (movingSpriteList.get(i)).moveIfPossible(environment);
        }
    }
}
