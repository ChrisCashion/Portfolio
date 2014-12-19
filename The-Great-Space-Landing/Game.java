

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

//This is the actual game. This holds all of the objects and updates them.
//Written by Chris Cashion with help from GameTutorial.net

public class Game {

    //The rocket that the player controls. This makes a new PlayerRocket Object.
    private PlayerRocket playerRocket = new PlayerRocket();
    
    //landing area for landing the rocket on. This is the target where the player is trying to land.
    private LandingArea landingArea;
    
    //loads the background image for the game, in our case it's a moon and some stars and things
    private BufferedImage backgroundImg;
    
    //loads the SpaceRock, an obstacle the player has to try to avoid.
    private SpaceRock spaceRock;
    
    //simple red boarder for the frame of the window when the player fails to land the rocket.
    private BufferedImage redBorderImg;
    

    public Game()
    {	
    	//changes the gamestate in Framework.java to 'loading'.
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        //makes a new thread for the game
        Thread threadForInitGame = new Thread() {
        	
        	
            @Override
            public void run(){
                //Initializes the variables of the game, such as the rocket's random position and resets the rock.
                Initialize();
                //Loads the files, such as sounds, pictures, graphics, etc.
                LoadContent();
                
                //gamestate updates to 'playing'
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };//end of that thread
        threadForInitGame.start();
    }
    
    
 //initializes the rocket, landing area and spacerock for game start and game resets.
    private void Initialize()
    {
        playerRocket = new PlayerRocket();
        landingArea  = new LandingArea();
		spaceRock = new SpaceRock();
    }
    
   //loads content for the game, like the background, boarder, any sounds, etc.
    private void LoadContent()
    {
        try
        {
            URL backgroundImgUrl = this.getClass().getResource("background.jpg");
            backgroundImg = ImageIO.read(backgroundImgUrl);
            
            URL redBorderImgUrl = this.getClass().getResource("red_border.png");
            redBorderImg = ImageIO.read(redBorderImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //reset the rocket if gameover happens.
    public void RestartGame()
    {
        playerRocket.ResetPlayer();
        landingArea.Reset();
    }
    
    
    //updates the new values for different parts of the game.
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket and the obstacle
        playerRocket.Update();
		spaceRock.Update();
        
        //Checks to make sure the rocket isn't in a breaking zone or landing zone
        if(playerRocket.y + playerRocket.rocketImgHeight - 10 > landingArea.y || (playerRocket.x + 50 > spaceRock.x && playerRocket.x < spaceRock.x+125 && playerRocket.y+70 > spaceRock.y && playerRocket.y<spaceRock.y+30 /*playerRocket.y > spaceRock.y  && playerRocket.y < spaceRock.y+spaceRock.spaceRockImgHeight- playerRocket.rocketImgHeight*/) )
        {
            //is the rocket close to landing?
            if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth - playerRocket.rocketImgWidth))
            {
                //if it is close to landing, how fast is it going? 
            	//AND makes sure the rocket is low enough to land... darned bugs.
                if(playerRocket.speedY <= playerRocket.TOPLANDINGSPEED && playerRocket.y > 300)
                    playerRocket.landed = true;
                else
                    playerRocket.crashed = true;
            }
            else
                playerRocket.crashed = true;
                
            Framework.gameState = Framework.GameState.GAMEOVER;
        }
    }
    
    //draws the important parts of the game to the screen with the graphics and mouse position.
    public void Draw(Graphics2D g2d, Point mousePosition)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        landingArea.Draw(g2d);
		  
		spaceRock.Draw(g2d);
        
        playerRocket.Draw(g2d);
		  
		  
    }
    
    
    //draws the gameover screen, with text.
    public void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        Draw(g2d, mousePosition);
        //tells you how to restart
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        
        //draws the successful message of landing the rocket
        if(playerRocket.landed)
        {
            g2d.drawString("You have successfully landed!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have landed in " + gameTime / Framework.secInNanosec + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
        }
        
        //draws the red 'youdunbad' message.
        else
        {
            g2d.setColor(Color.red);
            g2d.drawString("You have crashed the rocket!", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
            g2d.drawImage(redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        }
    }
}
