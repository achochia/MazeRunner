import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MazeFrame extends JFrame
{
    // TODO: Auto-Generated method stub
    private static final long serialVersionUID = 1L;
    private MazeController controller;

    public MazeFrame()
    {
        super("Maze Runner");
        JLabel ball = new JLabel();
        JLabel message = new JLabel();
        JLabel finishLine = new JLabel();
        JButton reset = new JButton();
        finishLine.setBounds(470, 470, 30, 30);
        finishLine.setBorder(BorderFactory.createLineBorder(Color.green, 15));
        JLabel walls[] = new JLabel[90];
        for (int i = 0; i < walls.length; i++)
            walls[i] = new JLabel();
        reset.setBounds(420, 10, 78, 30);
        reset.setText("RESET");
        message.setText("Drag the blue ball to begin");
        message.setBounds(150, 230, 200, 20);
        getContentPane().setPreferredSize(new Dimension(500, 500));
        setLocation(10, 30);
        ball.setSize(10, 10);
        ball.setLocation(10, 10);
        ball.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        controller = new MazeController(ball, walls, message, finishLine, reset);
        getContentPane().setLayout(null);
        getContentPane().add(ball);
        getContentPane().add(message);
        getContentPane().add(finishLine);
        getContentPane().add(reset);
        for (int i = 0; i < walls.length; i++)
            getContentPane().add(walls[i]);
        getContentPane().addMouseMotionListener(controller);
        reset.addActionListener(controller);
        pack();
    }
}
