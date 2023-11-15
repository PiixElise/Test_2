import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * Draws robots on a canvas.
 * 
 * @author Patrick Girard 
 * @version 2021.08
 */
public class CanvasRobot
{
    private static Canvas canvas = Canvas.getCanvas(); 
    private static Random randomGenerator; 
    
    private static ArrayList<CanvasRobot> canvasRobotList = new ArrayList<CanvasRobot>();
    private static boolean gameOver = false;
    
    // Coordinates for redraw
    private boolean redrawable;
    private int x;
    private int y;
    
    private int lo = 30;
    private int la = 31;
    private int plo = 8;
    private int pla = 10;
    private int tlo = 26;
    private int tla = 13;
    private int qla = 4;
    private static Colour colourHead = Colour.RED;
    private Colour colourBody = Colour.BLUE;
    private static Colour colourLeg = Colour.BLACK;
    private static Colour colourEye = Colour.GREEN;
    private Integer corps;
    private Integer brasG;
    private Integer brasD;
    private Integer jambeG;
    private Integer jambeD;
    private Integer tete;
    private Integer oeilD;
    private Integer oeilG;

    /**
     * CanvasRobot Constructor - Creates a new graphical robot, which can be drawn. 
     * if the colour is not correct, the colour body is set to BLUE
     * The robot is not drawn at this time (no coordinates)
     *
     * @param colourBody body colour
     */
    public CanvasRobot(String colourBody)
    {
        // The random generator is instantiated if necessary
        if (randomGenerator == null) randomGenerator = new Random();
        // The different objects of the robot are instantiated, to allow the process of identification 
        // of graphical objects in the canvas. 
        corps = new Integer(randomGenerator.nextInt());
        brasG = new Integer(randomGenerator.nextInt());
        brasD = new Integer(randomGenerator.nextInt());
        jambeG = new Integer(randomGenerator.nextInt());
        jambeD = new Integer(randomGenerator.nextInt());
        tete = new Integer(randomGenerator.nextInt());
        oeilD = new Integer(randomGenerator.nextInt());
        oeilG = new Integer(randomGenerator.nextInt());

        this.colourBody = valueOf(colourBody);
        redrawable = false;
        
        canvasRobotList.add(this);
    }
    
    /**
     * Draws a robot onto the canvas, only if not game over
     * If try to draw out of canvas or on an existing CanvasRobot => Game Over!
     * If the robot was already drawn, it is erased (thanks to Canvas)
     * stores the coordinates to allow redraw if the color changes
     *
     * @param x,y robot position
     */
    public void drawRobot(int x, int y)
    {
        if (!gameOver) {
            int xp = x * 50;
            int yp = y * 50;
            canvas.draw(brasG, colourLeg, new Rectangle(xp, yp + 15, plo, pla));
            canvas.draw(brasD, colourLeg, new Rectangle(xp + la + 2, yp + 15, plo, pla));
            canvas.draw(jambeG, colourLeg, new Rectangle(xp + 10, yp + 40, plo, pla));
            canvas.draw(jambeD, colourLeg, new Rectangle(xp + la - 8, yp + 40, plo, pla));
            canvas.draw(tete, colourHead, new Rectangle(xp + 14, yp, tla, tlo));
            canvas.draw(oeilG, colourEye, new Ellipse2D.Double(xp + 16, yp + 2, qla, qla));
            canvas.draw(oeilD, colourEye, new Ellipse2D.Double(xp + 21, yp + 2, qla, qla));
            canvas.draw(corps, colourBody, new Rectangle(xp + 5, yp + 10, la, lo));
            this.x = x;
            this.y = y;
            redrawable = true;
            
            if (outOfCanvas()) gameOver();                
            if (collision()) gameOver();
        }
    }
    
    /**
     * Method setColourBody
     * Sets the colour of the body and draw it again
     * 
     * @param colourBody the new colour
     */
    public void setColourBody(String colourBody) {
        this.colourBody = valueOf(colourBody);
        if (redrawable) drawRobot(this.x, this.y);
    }
    
    /**
     * Method setColourHead
     * Sets the colour of the head and draw it again
     * 
     * @param colourHead the new colour
     */
    public void setColourHead(String colourHead) {
        this.colourHead = valueOf(colourHead);
        if (redrawable) drawRobot(this.x, this.y);
    }
    
    /**
     * Method getColourBody
     *
     * @return The actual color of the body
     */
    public String getColourBody() {
        return colourBody.toString();
    }
    
    /**
     * Draws a robot on the canvas at the specified position.
     * 
     * @param x The x coordinate where the robot should be drawn.
     * @param y The y coordinate where the robot should be drawn.
     */
    public void drawTobor(int x, int y)
    {
        if (!gameOver) {
            int xp = x*50 ;
            int yp = y*50 ;
            canvas.draw(brasG, colourLeg, new Rectangle(xp, yp+20,
                                         plo, pla));
            canvas.draw(brasD, colourLeg, new Rectangle(xp+la+2, yp+20,
                                         plo, pla));
            canvas.draw(jambeG, colourLeg, new Rectangle(xp+10, yp,
                                         plo, pla));
            canvas.draw(jambeD, colourLeg, new Rectangle(xp+la-8, yp,
                                         plo, pla));
            canvas.draw(tete, colourHead, new Rectangle(xp+14, yp+40,
                                         tla, tlo));
            canvas.draw(oeilG, colourEye, new Ellipse2D.Double(xp+16, yp+42,
                                         qla, qla));
            canvas.draw(oeilD, colourEye, new Ellipse2D.Double(xp+21, yp+42,
                                         qla, qla));
            canvas.draw(corps, colourBody, new Rectangle(xp+5, yp+10,
                                         la, lo));
                                        
            this.x = x;
            this.y = y;
            
        }
    }
    
    /**
     * Converts a string representation of a color to the corresponding Color enum value.
     *
     * @param colour A string representing the color.
     * @return The Color enum value associated with the input string (ignoring case). If the string is not a valid color, it returns Color.BLUE as the default color.
     */
    private static Colour valueOf(String colour) {
        try {
            return Colour.valueOf(colour.toUpperCase());
        } catch (Exception e) {
        }
        return Colour.BLUE;
    }
    
    /**
     * Checks if there is a collision with another robot.
     *
     * @return true if a collision occurs, false otherwise
     */
    private boolean collision() {
        for (CanvasRobot c : canvasRobotList) 
            if ((c != this) && (x == c.x) && (y == c.y) && (c.redrawable))  
                return true;
        return false;
    }
    
    /**
     * Checks if the robot is out of the canvas.
     *
     * @return true if the robot is out of the canvas, false otherwise
     */
    private boolean outOfCanvas() {
        if (x < 0) return true;
        if (y < 0) return true;
        if (x > 11) return true;
        if (y > 11) return true;
        return false;
    }
    
    /**
     * Ends the game and displays the game over message on the canvas.
     */
    private static void gameOver() {
        gameOver = true;
        canvas.gameOver();
    }
}
