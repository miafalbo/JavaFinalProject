public enum CounterType {

    COUNTER(0), MACHINE(1), FOOD(2);
    private int currentType;

    CounterType(int currentType) {
        this.currentType = currentType;
    }

    public int getCurrentType() {
        return currentType;
    }
}