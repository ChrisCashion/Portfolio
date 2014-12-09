

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
//Framework for running the game Game.java, updating it and drawing it to the screen.
//Written by Chris Cashion with help and code from GameTutorial.net


public class Framework extends Canvas {
    
    
    public static int frameWidth;//width of the frame
   
    public static int frameHeight;//height of the frame

   
    public static final long secInNanosec = 1000000000L;//how many nano seconds are in a second
    
    
    public static final long milisecInNanosec = 1000000L;//defines how many nanoseconds are in a milisecond
    
   
    private final int GAME_FPS = 17;//FPS, should be a changing number based on hardware limitations with a cap.
    //however, the game is simple and shouldn't be taxing on anything that has a 16bit operating system or better...
    
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;//figures out the game update period by the nanoseconds in a second divided by game FPS
    
    
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, DESTROYED}
    //various states of the game are defined here
    public static GameState gameState;//holds the value of the current game state
    
  
    private long gameTime;
    // It is used for calculating elapsed time.
    private long lastTime;
    
    // The actual game
    private Game game;
    
    
    
    private BufferedImage moonLanderMenuImg;//holds the image of the moon lander menu splash
    
    
    public Framework ()//actual framework method that controls Game.java
    {
        super();//calls to super
        
        gameState = GameState.VISUALIZING;//updates the game state to visualizing
        
        //We start game in new thread.
        Thread gameThread = new Thread() {//starts a new thread
            @Override
            public void run(){//run method of the new thread
                GameLoop();//loops the game
            }
        };
        gameThread.start();//starts gameThread, that controls the thread the game is on
    }
    
    

    private void Initialize()//this method is for Game.java to initialize all of its values and get ready to play
    {
        
    }
    
   
    private void LoadContent()//loads all of the fancy graphical things for the game. Such as images, sounds, movies, etc.
    {
        try
        {
            URL moonLanderMenuImgUrl = this.getClass().getResource("menu.jpg");//pull the menu from disk
            moonLanderMenuImg = ImageIO.read(moonLanderMenuImgUrl);//set the menu to the value we defined earlier
        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);//catchin' all dem exceptions. Aww YUUUS.
        }
    }
    
   
    private void GameLoop()
    {
        // These two variables are used in VISUALIZING state of the game. We used them to wait some time so that we get correct frame/window resolution.
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        
        // This variables are used for calculating the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;
        
        while(true)//always do this, until we break out of it.
        {
            beginTime = System.nanoTime();//sets system time to begin time for updating things in intervals.
            
            switch (gameState)//switch case for gameState. Much better than an If/Else block!
            {
                case PLAYING:
                    gameTime += System.nanoTime() - lastTime;//adds the new system time minus the last time as long as it's bigger than the original
                    
                    game.UpdateGame(gameTime, mousePosition());//passes game time and mousePosition to the Game.java.
                    //mouse position is stubbed out for future updates and isn't currently used.
                    
                    lastTime = System.nanoTime();//updates the system time
                break;//breaks the loop
                case GAMEOVER:
                    //...
                break;
                case MAIN_MENU:
                    //...
                break;
                case OPTIONS:
                    //...
                break;
                case GAME_CONTENT_LOADING:
                    //...
                break;
                case STARTING:
                    // Sets variables and objects.
                    Initialize();
                    // Load files - images, sounds, ...
                    LoadContent();

                    // When all things that are called above finished, we change game status to main menu.
                    gameState = GameState.MAIN_MENU;
                break;
                case VISUALIZING:
                    // On Ubuntu OS (when I tested on my old computer) this.getWidth() method doesn't return the correct value immediately (eg. for frame that should be 800px width, returns 0 than 790 and at last 798px). 
                    // So we wait one second for the window/frame to be set to its correct size. Just in case we
                    // also insert 'this.getWidth() > 1' condition in case when the window/frame size wasn't set in time,
                    // so that we although get approximately size.
                    if(this.getWidth() > 1 && visualizingTime > secInNanosec)//if the time has been long enough...
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();//sets the height and width to the variables of the frameheight and frame width

                        // When we get size of frame we change status.
                        gameState = GameState.STARTING;
                    }
                    else//else we wait...
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;//sets the current to the last
                        lastVisualizingTime = System.nanoTime();//and the current gets a fresh one
                    }
                break;
            }
            
            // Repaint the screen.
            repaint();
            
            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10) 
                timeLeft = 10; //set a minimum
            try {
                 //Provides the necessary delay and also yields control so that other thread can do work.
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
    
   
    @Override
    public void Draw(Graphics2D g2d)//draws the game to the screen with the 2d graphics
    {
        switch (gameState)//gamestate switch
        {
            case PLAYING:
            	
            	//calls .Draw in game to draw everything to the screen with the current locations of all the images and pointers.
                game.Draw(g2d, mousePosition());//again, stubbed for mouse position
                
            break;
            case GAMEOVER:
                game.DrawGameOver(g2d, mousePosition(), gameTime);//draws all of the things for GameOver screen
            break;
            case MAIN_MENU:
                g2d.drawImage(moonLanderMenuImg, 0, 0, frameWidth, frameHeight, null);//draws the menu background
                g2d.setColor(Color.white);//sets the text color to white to display on the dark background
                //the next two lines contain the strings that are written to the screen, and their locations they need to be written to.
                g2d.drawString("Use w a d keys to control the rocket.", frameWidth / 2 - 117, frameHeight / 2);
                g2d.drawString("Press any key to start the game.", frameWidth / 2 - 100, frameHeight / 2 + 30);
               
            break;
            case OPTIONS:
                //No options menu currently. It is here because it is stubbed out for improvements 
            	//I would like to see an options menu here shortly, this would get that ugly if(true)/else block for setting the window to full-screen or windowed mode...
            break;
            case GAME_CONTENT_LOADING:
                g2d.setColor(Color.white);//text color white if it isn't already
                g2d.drawString("GAME is LOADING", frameWidth / 2 - 50, frameHeight / 2);//displays this screen while things get initialized and loaded into memory.
            break;
        }
    }
    
    
    private void newGame()//begins a new game 
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game();//starts the game
    }
    
   
    private void restartGame()//restarts game for that inevitable problem where the game is won or lost.
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game.RestartGame();
        
        // We change game status so that the game can start.
        gameState = GameState.PLAYING;
    }
    
   
    private Point mousePosition()//this entire method is a stub for mouse cursor positions later.
    {
        try
        {
            Point mp = this.getMousePosition();//gets the current mouse position. 
            
            if(mp != null)
                return this.getMousePosition();//returns mouse position if !null
            else
                return new Point(0, 0);//if it's null, then we return 0,0 to keep everything from exploding.
        }
        catch (Exception e)
        {
            return new Point(0, 0);//if some exception happens at all, we pop out of the try block and simply return a point of 0,0.
        }
    }
    
    
    @Override
    public void keyReleasedFramework(KeyEvent e)//This method changes the game state if a key is released.
    {
        switch (gameState)
        {
            case MAIN_MENU:
                newGame();
            break;
            case GAMEOVER:
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)//if any key is released in gameover then the game resets.
                    restartGame();
            break;
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e)//stubbed for mouse control later
    {
        //we would put a similar code to the one above, but with mouse events instead of key events.
    }
}
