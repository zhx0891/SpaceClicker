package lesson8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

class GameWindow extends JFrame {
    private static GameWindow gameWindow = new GameWindow();
    private static Image background;
    private static Image target;
    private static float targetLeft = 100;  //координата х верхнего левого угла цели
    private static float targetTop = 200;  //координата y верхнего левого угла цели
    private static int score = 0;

    public static void main(String[] args) throws IOException {

        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.jpg"));
        target= ImageIO.read(GameWindow.class.getResourceAsStream("target.png"));
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(852, 480);

        GameField gameField = new GameField();
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX(); // координаты курсора
                int y = e.getY();
                float targRight = targetLeft + target.getWidth(null); //ширина объекта
                float targBottom = targetTop + target.getHeight(null); //высота объекта !!!

                boolean coinx = x >= targetLeft && x <= targRight && y >= targetTop && y <= targBottom;

                if (coinx) {
                    targetTop = (int) (Math.random() * (gameField.getHeight() - target.getHeight(null)));
                    targetLeft =(int) (Math.random() * (gameField.getWidth() - target.getWidth(null)));
                    score++;
                    gameWindow.setTitle("Score: " + score);
                }


            }
        });
        gameWindow.add(gameField);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);

    }
    private static  void onRepaint (Graphics g) {

        g.drawImage(background, 0,0, null);
        g.drawImage(target, (int) targetLeft, (int) targetTop, null);




    }
    private static class  GameField extends JPanel {
        @Override
        protected  void  paintComponent (Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
