import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {

    //private variable
    private ArrayList<Sprite> environment = new ArrayList<>();

    public Playground(String pathname, RenderEngine game) {
        try{
            final Image imageFloor = ImageIO.read(new File("src/Assets/floorTile.png"));
            final Image imageCounter = ImageIO.read(new File("src/Assets/counter.png"));
            final Image imageMachine = ImageIO.read(new File("src/Assets/machineCounter.png"));
            final Image imageBasket = ImageIO.read(new File("src/Assets/foodCounter.png"));
            final Image imageStudent = ImageIO.read(new File("src/Assets/customerSprite.png"));

            final int imageFloorWidth = imageFloor.getWidth(null);
            final int imageFloorHeight = imageFloor.getHeight(null);

            final int imageCounterWidth = imageCounter.getWidth(null);
            final int imageCounterHeight = imageCounter.getHeight(null);


            //store buffered reader based on the current level
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathname));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;

            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'F' : environment.add(new Sprite(imageFloor, columnNumber*imageFloorWidth,
                                lineNumber*imageFloorHeight, imageFloorWidth, imageFloorHeight));
                            break;
                        case 'C' :
                            environment.add(new CounterSprite(imageCounter, columnNumber*imageCounterWidth,
                                lineNumber*imageCounterHeight, imageCounterWidth, imageCounterHeight, CounterType.COUNTER));
                            break;
                        case 'M' :
                            environment.add(new CounterSprite(imageMachine, columnNumber*imageCounterWidth,
                                    lineNumber*imageCounterHeight, imageCounterWidth, imageCounterHeight, CounterType.MACHINE));
                            break;
                        case 'B' :
                            environment.add(new CounterSprite(imageBasket, columnNumber*imageCounterWidth,
                                    lineNumber*imageCounterHeight, imageCounterWidth, imageCounterHeight, CounterType.FOOD));
                            break;
                        case 'O' :
                            environment.add(new CustomerSprite(imageCounter, columnNumber*imageCounterWidth,
                                    lineNumber*imageCounterHeight, imageCounterWidth, imageCounterHeight, game));
                            break;
                        case 'S' :
                            environment.add(new Sprite(imageStudent, columnNumber*imageCounterWidth,
                                    lineNumber*imageCounterHeight, imageCounterWidth, imageCounterHeight));
                            break;
                    }
                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //getter methods
    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}