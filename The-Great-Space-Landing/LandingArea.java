

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class LandingArea {
    //random number for moving the landing area around.
    private Random random = new Random();
    
    //
    public int x;
    /**
     * Y coordinate of the landing area.
     */
    public int y;
    
    /**
     * Image of landing area.
     */
    private BufferedImage landingAreaImg;
    
    /**
     * Width of landing area.
     */
    public int landingAreaImgWidth;
    
    
    public LandingArea()
    {
        Initialize();
        LoadContent();
        
        x = random.nextInt(Framework.frameWidth - landingAreaImgWidth);
    }
    
    
    private void Initialize()
    {   
    	//generate a new random number
    	float number = random.nextFloat(); 
    	
    	if(number > .15 || number < .85){
    		// X coordinate of the landing area is a random near the middle of the frame.
        	x = (int)(Framework.frameWidth * number);
    	}
    	
    	else{
    		//our random number wasn't close enough to the center, so we set x to the center.        	
            x = (int)(Framework.frameWidth * .5);
    	}
        
        //Y coordinate is at .88 of the frame height (13% from the bottom of the frame)
        y = (int)(Framework.frameHeight * 0.88);
    }
    
    public void Reset(){
    	float number = random.nextFloat();
    	if(number > .15 || number < .85){
    		// X coordinate of the landing area is a random near the middle of the frame.
        	x = (int)(Framework.frameWidth * number);
    	}
    	
    	else{
    		//our random number wasn't close enough to the center, so we set x to the center.        	
            x = (int)(Framework.frameWidth * .5);
    	}
        
    }
    
    //loads the images to memory for drawing later.
    private void LoadContent()
    {
    	
        try
        {
            URL landingAreaImgUrl = this.getClass().getResource("landing_area.png");
            landingAreaImg = ImageIO.read(landingAreaImgUrl);
            landingAreaImgWidth = landingAreaImg.getWidth();
        }
        catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //draws the image at the current locations
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(landingAreaImg, x, y, null);
    }
    
}
