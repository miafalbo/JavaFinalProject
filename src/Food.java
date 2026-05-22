public enum Food {

    CROISSANT(0), PAIN_CHOC(1), PAIN_SUISSE(2);
    private int currentFood;

    Food(int currentFood) {
        this.currentFood = currentFood;
    }

    public int getCurrentFood() {
        return currentFood;
    }
}