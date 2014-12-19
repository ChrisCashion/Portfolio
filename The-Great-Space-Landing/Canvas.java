

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


//This controls keyboard/mouse commands and draws to the screen if it wasn't overridden in framework.java 
//Written by Chris Cashion with help from GameTutorial.net

public abstract class Canvas extends JPanel implements KeyListener, MouseListener {
    
	//Global status array variables for keyboard and mouse state
    //Array of boolean keyboard states, one for all of the keys.
    private static boolean[] keyboardState = new boolean[525];
    
    //Array of boolean mouse states, one for the three keys on a standard mouse
    private static boolean[] mouseState = new boolean[3];
        
    
    public Canvas()
    {
        //Use the double buffer, and make it the focus, also the background should be drawn black four our case.
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.black);
        
        //remove true and replace with false if you want to be able to see the mouse cursor on the window.
        //this should be false if we implement some of the mouse functionality later.
        if(true)
        {
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }
        
        //adds key listener to this JPanel
        this.addKeyListener(this);
        //adds mouse listener to this JPanel
        this.addMouseListener(this);
    }
    
    
    //Overridden in framework.java shouldn't ever be used.
    public abstract void Draw(Graphics2D g2d);
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;        
        super.paintComponent(g2d);        
        Draw(g2d);
    }
       
    
    
    //Returns true if the key is held down, else returns false.
    public static boolean keyboardKeyState(int key)
    {
        return keyboardState[key];
    }
    
    //Methods of the keyboard listener.
    @Override
    public void keyPressed(KeyEvent e) 
    {
        keyboardState[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    
    public abstract void keyReleasedFramework(KeyEvent e);
    
    
    //Returns boolean if the mouse key is held down
    public static boolean mouseButtonState(int button)
    {
        return mouseState[button - 1];
    }
    
    //Sets the mouse key's status.
    private void mouseKeyStatus(MouseEvent e, boolean status)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
            mouseState[0] = status;
        else if(e.getButton() == MouseEvent.BUTTON2)
            mouseState[1] = status;
        else if(e.getButton() == MouseEvent.BUTTON3)
            mouseState[2] = status;
    }
    
    //Mouse listener methods
    @Override
    public void mousePressed(MouseEvent e)
    {
        mouseKeyStatus(e, true);
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseKeyStatus(e, false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }
    
    @Override
    public void mouseEntered(MouseEvent e) { }
    
    @Override
    public void mouseExited(MouseEvent e) { }
    
}
