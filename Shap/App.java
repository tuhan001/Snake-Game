import javax.swing.*;
import java.awt.event.KeyEvent;

public class App {
    public static void main(String[] args) throws Exception{
        int boardWidh = 600;
        int boardHeight = boardWidh;


        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardHeight,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(boardWidh, boardHeight);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();







    }
}
