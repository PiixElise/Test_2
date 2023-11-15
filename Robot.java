import java.util.*;
/**
 * The mother class of the specific robots
 *
 * @author Groupe12-COOPOO
 * @version 15/10/23
 */
public class Robot
{
    private int x;
    private int y;
    private int direction;
    private String colorBody;
    private String colorHead;
    private World list;
    private int vitesse;
    private CanvasRobot canvasRobot;
    
    /**
     * Robot's constructor
     * @param x x position
     * @param y y position
     * @param direction the robot's direction
     * @param vitesse the robot's speed
     * @param Color the color of the robot's body
     */
    public Robot(int x,int y,int direction,int vitesse,String Color)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.vitesse = vitesse;
        this.colorBody = Color;
        canvasRobot = new CanvasRobot(Color);
        canvasRobot.drawRobot(x,y);
    }
    
    /**
     * Creates a list of robots
     * @param world the list of the robots
     */
    public void setList(World world){
        list = world;
    }
    
    /**
     * Draws the robot
     * @return the drawing of the robot
     */
    public CanvasRobot getCanvasRobot(){
        return canvasRobot;
    }
   
    /**
     * Define the direction of the robot (top, bottom, left, right)
     */
    public void setDirection(int newDirection){ 
        this.direction = newDirection; 
    }
    
    /**
     * Security for the robot to not get out of the canvas
     */
    public boolean sécurité()
    {
        int ox,oy;
        boolean error;
        error=false;
        ox=this.x;
        oy=this.y;
        switch(direction) {
                case 0:
                    ox++;
                    if (ox>11){
                        error=true;
                    }
                    break;
                case 1:
                    oy++;
                    if (oy>11){
                        error=true;
                    }
                    break;
                case 2:
                    ox--;
                    if (ox<0){
                        error=true;
                    }
                    break;
                case 3:
                    oy--;
                    if (oy<0){
                        error=true;
                    }
                    break;
        }
        return error;
    }
    
    
    /**
     * Calculates and returns the new coordinates (X, Y)
     * of a robot based on its current direction.
     */
    public ArrayList<Integer> PositionGiven(){
        ArrayList<Integer> position = new ArrayList<Integer>();
        int xp = x;
        int yp = y;
        switch(direction) {
            case 0 :
                x++;
                break;             
            case 1 :
                x++;
                y++;
                break;
            case 2 :
                y++;
                break;  
            case 3 :
                x--;
                y++;
                break;
            case 4 :
                x--;
                break;  
            case 5 :
                x--;
                y--;
                break;
            case 6 :
                y--;
                break; 
            case 7 :
                x++;
                y--;
                break;
        }
        position.add(x);
        position.add(y);
        return position; 
    }
    
    /**
    * Define the robot's next position in the canvas
    */
    public void setNextStep() {};
    
    /**
    * Allows the robot to move
    */
    public void avancer()
    {
        for (int v=vitesse; v>0; v--){

            switch(direction) {
                case 0:
                    this.x++;
                    if (sécurité()==true){
                        this.x=11;
                    }                     
                    break;
                case 1:
                    this.y++;
                    if (sécurité()==true){
                        this.y=11;
                    }
                    break;
                case 2:
                    this.x--;
                    if (sécurité()==true){
                        this.x=0;
                    }
                    break;
                case 3:
                    this.y--;
                    if (sécurité()==true){
                        this.y=0;
                    }
                    break;
            }
        }   
    }
    
    
    /**
    * Getter of the X position of the robot
    */
    public int getX()
    {
        return this.x;   
    }
    
    /**
    * Setter of the X position of the robot
    * @param newX the new X position
    */
    public void setX(int newX)
     {
         this.x = newX;   
    }
    
    /**
     * Getter of the Y position of the robot
     * @return the y position
     */
    public int getY()
    {
         return this.y;   
    }
       
    /**
     * Setter of the Y position of the robot
     * @param newY the new Y position
     */
    public void setY(int newY){
        this.y = newY;
    }
    
    /**
     * Getter of the speed of the robot
     * @returns the speed value 
     */
    public int getVitesse()
    {
        return vitesse;   
    }
    
    /**
     * Getter of the direction of the robot
     * @return the direction value 
     */
    public int getDirection()
    {
         return direction;   
    }
    
    /**
     * Getter the robot's head color
     * @return the colorHead value 
     */
    public String getColorHead()
    {
         return colorHead;   
    }
     
    /**
     * Getter the robot's body color
     * @return the colorBody value 
     */
    public String getColorbody()
    {
         return colorBody;   
    }
    
    /**
     * Allows to change the color of the body of the robot
     * @param newColor the new color of the robot
     */
    public void changeColor(String newColor){
        this.colorBody = newColor;
        canvasRobot.setColourBody(this.colorBody);
    }
    
    /**
     * Allows to change the color of the head of the robot
     * @param newColor the new color of the robot
     */
    public void changeHeadColor(String newColor){
        this.colorHead = newColor;
        canvasRobot.setColourHead(this.colorHead);
    }
    
    /**
     * Moves the robot one unit in its current direction
     */
    public boolean move(){
        int x1, y1;
        ArrayList<Integer> position = PositionGiven();
        x1 = position.get(0);
        y1 = position.get(1);
        
        if (World.checkthePosition(x1,y1))
            return true;
        else {
            x = x1;
            y = y1;
            drawRobot(x, y);
            return false;
        }
    }
    
    /**
     * 
     */
    public void drawRobot(int x, int y){
        canvasRobot.drawRobot(x,y);
    }
    
    /**
     * 
     */
    public void drawTobor(){
        canvasRobot.drawRobot(x,y);
    }
}
