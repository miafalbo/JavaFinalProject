public class MachineBrew implements MachineState{

    private MachineSprite machine;
    long startTime;

    public MachineBrew(MachineSprite machine){
        this.machine = machine;
        startTime = System.currentTimeMillis();

        //sets correct brew time
        switch(machine.getDrink().getCurrentDrink()) {
            case(0):
                machine.setBrewTime(15000);
            case(1):
                machine.setBrewTime(25000);
            case(2):
                machine.setBrewTime(20000);
        }
    }

    @Override
    public void update() {
        //changes the machine to complete when the timer runs out
        machine.setRemainingTime(machine.getBrewTime()  - (System.currentTimeMillis() - startTime));
        if(System.currentTimeMillis() > startTime + machine.getBrewTime()) {
            machine.setState(new MachineComplete(machine));
        }
    }

    @Override
    public void pressButton() {

    }
}
