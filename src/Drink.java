public enum Drink {

    COFFEE(0), TEA(1), CHOC_CHAUD(2);
    private int currentDrink;

    Drink(int currentDrink) {
        this.currentDrink = currentDrink;
    }

    public int getCurrentDrink() {
        return currentDrink;
    }
}