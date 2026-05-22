import java.util.ArrayList;

public class CurrentPlate {

    private ArrayList<SolidSprite> itemList;

    public CurrentPlate() {
        itemList = new ArrayList<>();
    }

    public void addItem(SolidSprite item) {
        itemList.add(item);
    }

    public ArrayList<SolidSprite> getItemList() {
        return itemList;
    }

    public void clearPlate() {
        itemList.clear();
    }
}
