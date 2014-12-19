

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


//Written by Chris Cashion with help from GameTutorial.net

public class PlayerRocket {
    
    //stores the random number for the rocket's starting coordinate.
    private Random random = new Random();
 	   
    //stores the x coordinate of the rocket.
    public int x;
    
    //Stores the Y coordinate of the rocket.
    public int y;
    
    //boolean variable for whether the rocket has landed or not.
    public boolean landed;
    
    //boolean variable for wether the rocket is crashed or not.
    public boolean crashed;
        
    //acceleration speed of the rocket, this changes on if the key is held down or not.
    private int speedAccelerating;
    
    //slowing speed of the rocket, or 'gravity'.
    private int speedStopping;
    
    //the maximum speed the rocket can have without causing a crash landing. it is a constant.
    public int TOPLANDINGSPEED;
    
    //speed of the rocket in the X direction.
    private int speedX;
   
    //speed of the rocket in the Y direction.
    public int speedY;
            
    //the image of the rocket.
    private BufferedImage rocketImg;
    
    //image of the rocket landed.
    private BufferedImage rocketLandedImg;
    
    //image of the rocket crashed.
    private BufferedImage rocketCrashedImg;
   
    //image of the rocket's burners/fire
    private BufferedImage rocketFireImg;
    
    //width of the rocket img.
    public int rocketImgWidth;
    
    //height of the rocket image.
    public int rocketImgHeight;
    
    
    
    public PlayerRocket()
    {
        Initialize();
        LoadContent();
        
        // Now that we have rocketImgWidth we set starting x coordinate.
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
    }
    
    
    private void Initialize()
    {	
    	//resets the player every time it's initialized
        ResetPlayer();
        
        //sets the acceleration speed and stopping speed.
        speedAccelerating = 2;
        speedStopping = 1;
        
        //sets the top landing speed to 5
        TOPLANDINGSPEED = 5;
    }
    
    private void LoadContent()
    {
    	//load all the images for the rocket's different images for its states.
        try
        {
            URL rocketImgUrl = this.getClass().getResource("rocket.png");
            rocketImg = ImageIO.read(rocketImgUrl);
            
            //get the image width and img height of the standard rocket picture.
            rocketImgWidth = rocketImg.getWidth();
            rocketImgHeight = rocketImg.getHeight();
            
            URL rocketLandedImgUrl = this.getClass().getResource("rocket_landed.png");
            rocketLandedImg = ImageIO.read(rocketLandedImgUrl);
            
            URL rocketCrashedImgUrl = this.getClass().getResource("rocket_crashed.png");
            rocketCrashedImg = ImageIO.read(rocketCrashedImgUrl);
            
            URL rocketFireImgUrl = this.getClass().getResource("rocket_fire.png");
            rocketFireImg = ImageIO.read(rocketFireImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //resets all of the variables and sets the rocket back at the top.
    public void ResetPlayer()
    {
    	//resets our booleans
        landed = false;
        crashed = false;
        
        //a random x gives it some fun, while a constant y keeps it from crashing or landing prematurely.
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
        y = 10;
        
        //sets the speeds back to zero
        speedX = 0;
        speedY = 0;
    }
    
    
    //update is in charge of moving the rocket and updating the speeds that it is traveling at.
    public void Update()
    {
	 	
        //Calculates the speed up and down
        if(Canvas.keyboardKeyState(KeyEvent.VK_W))
            speedY -= speedAccelerating;
        else
            speedY += speedStopping;
        
        //calculates the speed for going left
        if(Canvas.keyboardKeyState(KeyEvent.VK_A))
            speedX -= speedAccelerating;
        else if(speedX < 0)
            speedX += speedStopping;
        
        //Calculates the speed for going right.
        if(Canvas.keyboardKeyState(KeyEvent.VK_D))
            speedX += speedAccelerating;
        else if(speedX > 0)
            speedX -= speedStopping;
        
        //Sets the new x and y of the rocket.
        x += speedX;
        y += speedY;

		 		  
    }
    
    //Draws the rocket to the screen.
    public void Draw(Graphics2D g2d)
    {	
    	//sets the color of the string to white
        g2d.setColor(Color.white);
        
        //draws the rocket coordinates x and y to the top left corner of the screen
        g2d.drawString("Rocket coordinates: " + x + " : " + y, 5, 15);
        
        //If the rocket is landed.
        if(landed)
        {
            g2d.drawImage(rocketLandedImg, x, y, null);
        }
        //If the rocket is crashed.
        else if(crashed)
        {
            g2d.drawImage(rocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
        }
        //If the rocket is still in space.
        else
        {
            //if the W key is pressed, draw rocket fire on the bottom of the rocket.
            if(Canvas.keyboardKeyState(KeyEvent.VK_W))
                g2d.drawImage(rocketFireImg, x + 12, y + 66, null);
            g2d.drawImage(rocketImg, x, y, null);
        }
    }
    
}
