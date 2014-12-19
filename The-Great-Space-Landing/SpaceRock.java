

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.Random;

//SpaceRock is the object that the PlayerRocket has to avoid to land safely on the landing pad.
//Written by Chris Cashion with help from GameTutorial.net
public class SpaceRock {
    
    //x-coordinate of the rock. This is the top left corner of the rock.
    public int x;
    
  	//this is the Y coordinate of the rock. This is the top left corner of the rock.
    public int y;
    
    //boolean b tells us which direction the rock is traveling, right or left across the screen.
	private boolean b;

    //stores the image of the landing area.
    private BufferedImage spaceRockImg;
	
    
    //the height and width of the space rock, this should be the height and width of the image we load.
    public int spaceRockImgWidth;
	public int spaceRockImgHeight;
    
    
    public SpaceRock()
    {
        Initialize();
        LoadContent();
    }
    
    
    private void Initialize()
    {   
        //x is at 1/4 from the right edge of the frame.
		x = (int)(Framework.frameWidth * 0.25);
        //Y is at 3/10 of the right edge of the frame.
        y = (int)(Framework.frameHeight * 0.30);
        
        //set the boolean to true
		b = true;
    }
    
    private void LoadContent()
    {
        try
        {
        	//gets the .png from the disk and stores it
            URL spaceRockImgUrl = this.getClass().getResource("space_rock.png");
            spaceRockImg = ImageIO.read(spaceRockImgUrl);
            
            //sets the image height and width to the values of height and width
            spaceRockImgWidth = spaceRockImg.getWidth();
			spaceRockImgHeight = spaceRockImg.getHeight();
        }
        catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //draws the rock to the screen
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(spaceRockImg, x, y, null);
    }
	 
    //updates the location of the rock, and the direction the rock is traveling.
	public void Update()
	 {
	 //if the rock is at less than 400 and b== true add 4 to x and if it's now greater than 400, set b to false
		if(x < 400 && b){
			x += 4;
			if(x>400){
				b=false;
			}
		}
		//else, subtract four from x and if it's now at less than 200, set b back to true.
		else{
			x -=4;
			if(x<200){
				b = true;
			}
			
		}
		
	 }
    
}
