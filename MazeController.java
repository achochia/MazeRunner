import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazeController implements MouseMotionListener, ActionListener
{
    private JLabel ball;
    private JLabel[] walls;
    private JLabel message;
    private JLabel finishLine;
    private int cursorX, cursorY;
    private JButton reset;

    MazeController(JLabel ball, JLabel[] walls, JLabel message,
            JLabel finishLine, JButton reset)
    /**
     * Pre: N/A
     * Params: Components of the view
     * Purpose: Constructor of the logic mechanism
     * Post: Controller of the view is created
     */
    {
        this.ball = ball;
        this.walls = walls;
        this.cursorX = 10;
        this.cursorY = 10;
        this.message = message;
        this.finishLine = finishLine;
        this.reset = reset;
        generateWalls();
        reset();
    }

    public void actionPerformed (ActionEvent e)
    /**
     * Pre: Mouse click on reset button
     * Purpose: To reset the maze if the user so chooses
     * Post: The maze is generated again
     */
    {
        generateWalls();
    }

    public boolean wallContainsBall ()
    /**
     * Pre: N/A
     * Purpose: To detect if the ball hit the wall or not
     * Post: Returns (boolean) if the ball is inside the wall or not
     */
    {
        for (int i = 0; i < walls.length; i++)
            if (ball.getX() <= walls[i].getX() + walls[i].getWidth()
                    && (ball.getX() + 10) >= walls[i].getX()
                    && ball.getY() <= walls[i].getY() + walls[i].getHeight()
                    && (ball.getY() + 10) >= walls[i].getY())
                return true;
        if (ball.getX() < 0 || ball.getX() + ball.getWidth() > 500
                || ball.getY() < 0 || ball.getY() + ball.getHeight() > 500)
            return true;
        return false;
    }

    public void mouseDragged (MouseEvent e)
    /**
     * Pre: Mouse clicked and dragged across the ball
     * Purpose: To have the ball follow the mouse cursor after beginning of
     * drag and move it to beggining on end of drag
     * Post: N/A
     */
    {
        message.setText("");
        reset.setVisible(false);
        if (e.getX() <= ball.getX() + 15 && e.getX() >= ball.getX() - 5
                && e.getY() <= ball.getY() + 15 && e.getY() >= ball.getY() - 5)
        {
            ball.setLocation(e.getX() - 5, e.getY() - 5);
            for (int i = 0; i < walls.length; i++)
                walls[i].setVisible(true);
            finishLine.setVisible(true);
            if (wallContainsBall())
                reset();
        }
        if (ball.getX() + ball.getWidth() > 470
                && ball.getY() + ball.getHeight() > 470)
        {
            message.setText("YOU WON!");
            message.setVisible(true);
            message.setLocation(200, 200);
            for (int i = 0; i < walls.length; i++)
                walls[i].setVisible(false);
            generateWalls();
            ball.setLocation(cursorX, cursorY);
        }
    }

    public void mouseMoved (MouseEvent e)
    /**
     * Pre: Mouse moved without ball drag
     * Purpose: to move ball to beginning if user moves the mouse without
     * dragging
     * (will not trigger on release for those with touchpads)
     * Post: Ball is returned to original position
     */
    {
        reset();
    }

    void reset ()
    /**
     * Pre: N/A
     * Purpose: to reset the maze, but not restart it
     * Post: Maze reset
     */
    {
        ball.setLocation(cursorX, cursorY);
        for (int i = 0; i < walls.length; i++)
            walls[i].setVisible(false);
        message.setText("Drag the blue ball to begin");
        message.setVisible(true);
        message.setBounds(150, 230, 200, 20);
        finishLine.setVisible(false);
        reset.setVisible(true);
    }

    public void generateWalls ()
    /**
     * Pre: N/A
     * Purpose: to set up the location of the rectangle objects acting as walls
     * to the maze
     * Post: Maze created
     */
    {
        MyRectangle array[] = new MyRectangle[walls.length];
        MyRectangle start = new MyRectangle(0, 0, 50, 50);
        MyRectangle finish = new MyRectangle(450, 450, 50, 50);
        for (int i = 0; i < array.length; i++)
        {
            int tempX, tempY, tempW, tempH;
            tempX = (int) (Math.random() * 500);
            tempY = (int) (Math.random() * 500);
            if (Math.random() * 2 > 1)
            {
                tempW = 100;
                tempH = 10;
            }
            else
            {
                tempW = 10;
                tempH = 100;
            }
            array[i] = new MyRectangle(tempX, tempY, tempW, tempH);
            int count = 0;
            if (start.containsRectangle(array[i])
                    || finish.containsRectangle(array[i]))
                i--;
            else
                for (int q = 0; q < i; q++)
                {
                    if (array[q].containsRectangle(array[i]))
                    {
                        count++;
                        if (count == 3)
                        {
                            count = 0;
                            i--;
                            break;
                        }
                    }
                }
        }
        for (int i = 0; i < array.length; i++)
        {
            walls[i].setBounds(array[i].x, array[i].y, array[i].width,
                    array[i].height);
            walls[i].setBorder(BorderFactory.createLineBorder(Color.red, 5));
        }
    }
}
