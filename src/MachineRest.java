public class MachineRest implements MachineState {

    private MachineSprite machine;
    private boolean buttonPressed;

    public MachineRest(MachineSprite machine) {
        this.machine = machine;
        buttonPressed = false;

        //set brew time and remaining time equal
        machine.setRemainingTime(1);
        machine.setBrewTime(1);
    }

    @Override
    public void update() {
        if(buttonPressed) {
            machine.setState(new MachineBrew(machine));
        }
    }

    @Override
    public void pressButton() {
        buttonPressed = true;
    }

}
