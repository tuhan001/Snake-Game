import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private class Tile{
        int x;
        int y;

        Tile(int x, int y){
            this.x=x;
            this.y=y;

            Tile SnakeHead;
        }
    }
    int boardWidh;
    int boardHight;
    int tileSize = 25;
    Tile snakeHead;
    ArrayList<Tile>snakeBody;
    Tile food;
    Random random;
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameover = false;

    SnakeGame(int boardWidh,int boardHight){
        this.boardWidh=boardWidh;
        this.boardHight=boardHight;
        setPreferredSize(new Dimension(this.boardWidh,this.boardHight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead= new Tile(5,5);
        snakeBody= new ArrayList<Tile>();
        food = new Tile(10,10);
        random= new Random();
        placeFood();
        gameLoop= new Timer(100, this);
        gameLoop.start();
        velocityX = 0;
        velocityY = 0;


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){
        //Grib
      //  for (int i = 0; i <boardWidh/tileSize; i++){
        //    g.drawLine(i*tileSize,0,i*tileSize,boardHight);
          //  g.drawLine(0,i*tileSize,boardWidh,i*tileSize);
        //}

        //Food
        g.setColor(Color.red);
      //  g.fillRect(food.x*tileSize,food.y*tileSize,tileSize,tileSize);
        g.fill3DRect(food.x*tileSize,food.y*tileSize,tileSize,tileSize,true);

        //Snake
        g.setColor(Color.green);
      //  g.fillRect(snakeHead.x*tileSize,snakeHead.y*tileSize,tileSize,tileSize);
        g.fill3DRect(snakeHead.x*tileSize,snakeHead.y*tileSize,tileSize,tileSize,true);


        for (int i=0; i<snakeBody.size();i++){
            Tile sankePart = snakeBody.get(i);
          //  g.fillRect(sankePart.x*tileSize,sankePart.y*tileSize,tileSize,tileSize);
            g.fill3DRect(sankePart.x*tileSize,sankePart.y*tileSize,tileSize,tileSize,true);
        }
        g.setFont(new Font("Arial", Font.PLAIN,16));
        if (gameover){
            g.setColor(Color.red);
            g.drawString("Total Score: "+String.valueOf(snakeBody.size()),tileSize=20,tileSize);

        }
        else {
            g.drawString("Score: "+String.valueOf(snakeBody.size()),tileSize-20,tileSize);
        }



    }
    public void placeFood(){
        food.x=random.nextInt(boardWidh/tileSize);
        food.y=random.nextInt(boardHight/tileSize);


    }
    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x==tile2.x && tile1.y==tile2.y;
    }
    public void move(){
        if (collision(snakeHead,food)){
            snakeBody.add(new Tile(food.x,food.y));
            placeFood();
        }

        for(int i= snakeBody.size()-1;i>=0;i--){
            Tile snakePart = snakeBody.get(i);
            if (i==0){
                snakePart.x=snakeHead.x;
                snakePart.y=snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x=prevSnakePart.x;
                snakePart.y=prevSnakePart.y;


            }
        }
        snakeHead.x+= velocityX;
        snakeHead.y+= velocityY;

        for (int i=0; i<snakeBody.size(); i++){
    Tile snakePart = snakeBody.get(i);
    if (collision(snakeHead,snakePart)){
        gameover = true;
    }
}
        if (snakeHead.x*tileSize<0|| snakeHead.x*tileSize >boardWidh || snakeHead.y*tileSize<0|| snakeHead.y*tileSize>boardHight){
            gameover= true;
        }
    }
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
        if(gameover){
            gameLoop.stop();
        }
    }
    public void keyPressed(KeyEvent e){
       if(e.getKeyCode()== KeyEvent.VK_UP){
           velocityX =0;
           velocityY=-1;
       }
       else if (e.getKeyCode()==KeyEvent.VK_DOWN){
           velocityX= 0;
           velocityY=1;
       }
       else if (e.getKeyCode()==KeyEvent.VK_LEFT){
           velocityX=-1;
           velocityY=0;
       }
       else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
           velocityX=1;
           velocityY=0;
       }
    }
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
