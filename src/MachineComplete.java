public class MachineComplete implements MachineState {

    private MachineSprite machine;
    private boolean buttonPressed;

    public MachineComplete(MachineSprite machine){
        this.machine = machine;
        machine.setRemainingTime(0);
        machine.setBrewTime(1);
        buttonPressed = false;
    }

    @Override
    public void update() {
        if(buttonPressed) {
            machine.setState(new MachineRest(machine));
        }

    }

    @Override
    public void pressButton() {
        buttonPressed = true;
    }
}
