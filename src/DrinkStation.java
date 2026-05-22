import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DrinkStation extends JPanel {
    private MachineSprite coffee;
    private MachineSprite tea;
    private MachineSprite chocChaud;

    private JButton coffeeStartButton;
    private JButton teaStartButton;
    private JButton chocChaudStartButton;

    private CurrentPlate plate;

    public DrinkStation(CurrentPlate plate, MainPanel mainPanel) throws IOException {
        this.plate = plate;
        File machineImage = new File("src/Assets/machines.png");

        //initiate machine sprites
        coffee = new MachineSprite(ImageIO.read(machineImage), 0, 150, 250, 250, Drink.COFFEE);
        tea = new MachineSprite(ImageIO.read(machineImage), 195, 195, 250, 210, Drink.TEA);
        chocChaud = new MachineSprite(ImageIO.read(machineImage), 405, 160, 250, 250, Drink.CHOC_CHAUD);

        //back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 200, 50);
        add(backButton);
        backButton.addActionListener(e -> {
            mainPanel.showScreen(MainPanel.GAME);
        });

        //create start buttons
        setLayout(null);
        coffeeStartButton = new JButton("BREW");
        teaStartButton = new JButton("BREW");
        chocChaudStartButton = new JButton("BREW");

        coffeeStartButton.setBounds(50, 420, 100, 40);
        teaStartButton.setBounds(250, 420, 100, 40);
        chocChaudStartButton.setBounds(480, 420, 100, 40);

        add(coffeeStartButton);
        add(teaStartButton);
        add(chocChaudStartButton);

        //add action listeners to buttons
        coffeeStartButton.addActionListener(e -> {
            try {
                coffee.pressButton(plate);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        teaStartButton.addActionListener(e -> {
            try {
                tea.pressButton(plate);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        chocChaudStartButton.addActionListener(e -> {
            try {
                chocChaud.pressButton(plate);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //start timer
        Timer timer = new Timer(50, e -> {
            //update machine states
            coffee.update();
            tea.update();
            chocChaud.update();

            //update button text
            updateButton(coffee, coffeeStartButton);
            updateButton(tea, teaStartButton);
            updateButton(chocChaud, chocChaudStartButton);

            repaint();
        });

        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw counter
        g.setColor(new Color(115, 76, 54));
        g.fillRect(0, 340, 1000, 6000);

        //set machine positions
        int y1 = getY1(coffee);
        int y2 = getY2(coffee);
        coffee.getPic(0, 140, y1, y2);

        y1 = getY1(tea);
        y2 = getY2(tea);
        tea.getPic(149, 346, y1, y2);

        y1 = getY1(chocChaud);
        y2 = getY2(chocChaud);
        chocChaud.getPic(347, 520, y1, y2);

        //draw machines
        coffee.draw(g);
        tea.draw(g);
        chocChaud.draw(g);

        //progress bar
        drawProgressBar(g, coffee);
        drawProgressBar(g, tea);
        drawProgressBar(g, chocChaud);

    }

    private int getY1(MachineSprite machine) {
        if(machine.getState() instanceof MachineRest) {
            return 131;
        }
        return 0;
    }

    private int getY2(MachineSprite machine) {
        if(machine.getState() instanceof MachineRest) {
            return 260;
        }
        return 130;
    }

    //updates button text
    private void updateButton(MachineSprite machine, JButton button) {
        if(machine.getState() instanceof MachineComplete) {
            button.setText("COLLECT");
        } else if(machine.getState() instanceof MachineBrew) {
            button.setText("BREWING");
        } else {
            button.setText("BREW");
        }
    }

    //updates progress bar
    private void drawProgressBar(Graphics g, MachineSprite machine) {
        int barWidth = 100;
        int barHeight = 12;

        int barX = (int)machine.getX() + 50;
        int barY = (int)machine.getY() - 50;

        // background
        g.setColor(Color.GRAY);
        g.fillRect(barX, barY, barWidth, barHeight);

        // progress amount
        double progress = 1.0 - ((double)machine.getRemainingTime() / machine.getBrewTime());
        int filledWidth = (int)(progress * barWidth);
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, filledWidth, barHeight);

        // border
        g.setColor(Color.BLACK);
        g.drawRect(barX, barY, barWidth, barHeight);

    }


}
