import java.util.Random;
import java.util.ArrayList;

/**
 * The robots in this class inherit the attributes of the Robot
 * class (parent class), so they have a name and coordinates
 * (X and Y position). But these robots also change colour
 * randomly with each movement.
 *
 * @author Elise Martin
 * @version 26/10/2023
 */
public class RobotDISCO extends Robot {
    private int x = 10;  // Initial x position
    private int y = 1; // Initial y position
    
    /**
     * RobotDISCO class object constructor
     * position x souhaitée = 10
     * position y souhaitée = 1
     * direction = 1
     * speed = 0
     * body colour = magenta
     */
    public RobotDISCO(int x, int y, int direction)
    {
        super(x, y, direction, 0, "MAGENTA"); // Calling the constructor of the parent class
        this.x = x;
        this.y = y;
    }
    
    /**
     * Define the robot's next position in the canvas
     */
    public void setNextStep() {
        // Update the robot's direction so that it follows a rectangular trajectory
        switch (super.getDirection()) {
            case 0: // Direction : right
                this.x++;
                if (x == 10){
                    super.setDirection(1); // Go to bottom
                }
                break;
            case 1: // Direction : bottom
                this.y++;
                if (y == 10){
                    super.setDirection(2); // Go to left
                }
                break;
            case 2: // Direction : left
                this.x--;
                if (x == 1){
                    super.setDirection(3); // Go to top
                }
                break;
            case 3: // Direction : top
                this.y--;
                if (y == 1){
                    super.setDirection(0); // Go to right
                }
                break;
        }
        
        // Define a random colour for the body
        String newBodyColor;
        do {
            newBodyColor = getRandomColor();
        }
        // To generate a new colour if it's already the current one
        while (newBodyColor.equals(super.getColorbody())); 
        
        // Define a random colour for the head
        String newHeadColor;
        do {
            newHeadColor = getRandomColor();
        }
        // To generate a new colour if it is already the current colour or the body colour
        while (newHeadColor.equals(super.getColorHead()) || newHeadColor.equals(newBodyColor));
        
        // Update x and y coordinates
        super.setX(this.x);
        super.setY(this.y);
        
        // Apply this change of colour to the robot's body and head
        super.changeColor(newBodyColor);
        super.changeHeadColor(newHeadColor);
    }

    /**
     * Draw the robot with its new x and y coordinates
     */    
    public void avancer() {
        super.drawRobot(this.x, this.y);
    }
    
    /**
     * Generates a random colour for the robot's body and head
     */
    public String getRandomColor() {
        // Create a list and add the different colours
        ArrayList<String> colors = new ArrayList<>();
        colors.add("RED");
        colors.add("BLACK");
        colors.add("BLUE");
        colors.add("YELLOW");
        colors.add("GREEN");
        colors.add("MAGENTA");
        colors.add("WHITE");
        colors.add("PURPLE");
        
        // Generates a random number
        Random random = new Random();
        
        // Generates a random index (with the size of the list as the maximum)
        int randomIndex = random.nextInt(colors.size());
        
        // Returns the colour corresponding to the random index
        return colors.get(randomIndex);
    }
}
