/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/main.java to edit this template
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.net.URL;
public class Game extends JPanel {

    Man man = new Man(50, 360,100,"Man.png");
    private int timeLeft = 60;
    private Timer gameTimer;
    private boolean isGameOver = false;
    private int score = 0;
    private int collisionCount = 3;
    private List<FallingObject> fallingObjects = new ArrayList<>();
    private Timer objectSpawner;
    private List<FallingObject2> fallingObjects2 = new ArrayList<>();
    private Timer objectSpawner2;
    private List<FallingObject3> fallingObjects3 = new ArrayList<>();
    private Timer objectSpawner3;
    private List<FallingObject4> fallingObjects4 = new ArrayList<>();
    private Timer objectSpawner4;
    private List<FallingObject5> fallingObjects5 = new ArrayList<>();
    private Timer objectSpawner5;
    private List<FallingObject6> fallingObjects6 = new ArrayList<>();
    private Timer objectSpawner6;
    private Timer updateTimer;



    Sound soundeiei = new Sound();
    String soundzaza = "song.wav";


    public Game() {
        soundeiei.playSound(soundzaza,0.5f);
        this.setBounds(0, 0, 1000, 600);
        this.setFocusable(true);
        this.setLayout(null);
            this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed A"), "moveLeft");
            this.getActionMap().put("moveLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    man.walkleft();
                    repaint();
                }
            });

            this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed D"), "moveRight");
            this.getActionMap().put("moveRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    man.walkright();
                    repaint();
                }
            });

//        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "moveUp");
//        this.getActionMap().put("moveUp", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                man.walkup();
//                repaint();
//            }
//        });
//
//        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "movedown");
//        this.getActionMap().put("movedown", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                man.walkdown();
//                repaint();
//            }
//        });

        objectSpawner = new Timer(1000, new AbstractAction() {  // Spawns an object every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int startX = rand.nextInt(getWidth() - 30);
                fallingObjects.add(new FallingObject(startX));
                repaint();
            }
        });
        objectSpawner.start();
        
        objectSpawner2 = new Timer(500, new AbstractAction() {  // Spawns an object every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int startX = rand.nextInt(getWidth() - 30);
                fallingObjects2.add(new FallingObject2(startX));
                repaint();
            }
        });
        objectSpawner2.start();

        objectSpawner3 = new Timer(5000, new AbstractAction() {  // Spawns an object every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int startX = rand.nextInt(getWidth() - 30);
                fallingObjects3.add(new FallingObject3(startX));
                repaint();
            }
        });
        objectSpawner3.start();

        objectSpawner4 = new Timer(10000, new AbstractAction() {  // Spawns an object every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int startX = rand.nextInt(getWidth() - 30);
                fallingObjects4.add(new FallingObject4(startX));
                repaint();
            }
        });
        objectSpawner4.start();

        objectSpawner5 = new Timer(15000, new AbstractAction() {  // Spawns an object every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int startX = rand.nextInt(getWidth() - 30);
                fallingObjects5.add(new FallingObject5(startX));
                repaint();
            }
        });
        objectSpawner5.start();

        objectSpawner6 = new Timer(10000, new AbstractAction() {  // Spawns an object every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int startX = rand.nextInt(getWidth() - 30);
                fallingObjects6.add(new FallingObject6(startX));
                repaint();
            }
        });
        objectSpawner6.start();
        
        gameTimer = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                } else {
                    ((Timer)e.getSource()).stop();
                    isGameOver = true;
                    repaint();
                }
            }
        });
        gameTimer.start();
        
        updateTimer = new Timer(16, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        updateTimer.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (restartButtonBounds.contains(e.getPoint()) && (collisionCount<=0||timeLeft<=0)) {
                    restartGame();
                }
            }
        });
    }
    public void restartGame() {
        // Reset game variables
        timeLeft = 60;
        isGameOver = false;
        score = 0;
        collisionCount = 3;
        fallingObjects.clear();
        fallingObjects2.clear();
        fallingObjects3.clear();
        fallingObjects4.clear();
        fallingObjects5.clear();
        fallingObjects6.clear();

        // Restart timers
        gameTimer.start();
        objectSpawner.start();
        objectSpawner2.start();
        objectSpawner3.start();
        objectSpawner4.start();
        objectSpawner5.start();
        objectSpawner6.start();
        updateTimer.start();

        // Resume sound if needed
        soundeiei.playSound(soundzaza, 0.5f);

        // Repaint the game panel to reflect the reset
        repaint();
    }
    private void update() {
        // Man
        for (int i = 0; i < fallingObjects.size(); i++) {
            FallingObject obj = fallingObjects.get(i);
            obj.update();
            if (man.getBounds().intersects(obj.bounds)) {
                score += 100;
                fallingObjects.remove(i);
                i--;
            }
        }
        for (int i = 0; i < fallingObjects2.size(); i++) {
            FallingObject2 obj = fallingObjects2.get(i);
            obj.update();
            if (man.getBounds().intersects(obj.bounds)) {
                collisionCount--;
                if (collisionCount <= 0){
                    isGameOver = true;
                    gameTimer.stop();
                    updateTimer.stop();
                    objectSpawner.stop();
                    objectSpawner2.stop();
                    objectSpawner3.stop();
                    objectSpawner4.stop();
                    objectSpawner5.stop();
                    objectSpawner6.stop();
                    soundeiei.stopSound();
                }
                else {
                    score -= 100;
                    fallingObjects2.remove(i);
                    i--;
                }
            }
        }
        for (int i = 0; i < fallingObjects3.size(); i++) {
            FallingObject3 obj = fallingObjects3.get(i);
            obj.update();
            if (man.getBounds().intersects(obj.bounds)) {
                collisionCount++;
                fallingObjects3.remove(i);
                i--;
            }
        }
        for (int i = 0; i < fallingObjects4.size(); i++) {
            FallingObject4 obj = fallingObjects4.get(i);
            obj.update();
            if (man.getBounds().intersects(obj.bounds)) {
                timeLeft+=10;
                fallingObjects4.remove(i);
                i--;
            }
        }
        if (score >= 1000) {
            for (int i = 0; i < fallingObjects5.size(); i++) {
                FallingObject5 obj = fallingObjects5.get(i);
                obj.update();
                if (man.getBounds().intersects(obj.bounds)) {
                    score += 500;
                    fallingObjects5.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < fallingObjects6.size(); i++) {
                FallingObject6 obj = fallingObjects6.get(i);
                obj.update();
                if (man.getBounds().intersects(obj.bounds)) {
                    collisionCount-=30;
                    if (collisionCount <= 0){
                        isGameOver = true;
                        gameTimer.stop();
                        updateTimer.stop();
                        objectSpawner.stop();
                        objectSpawner2.stop();
                        objectSpawner3.stop();
                        objectSpawner4.stop();
                        objectSpawner5.stop();
                        objectSpawner6.stop();
                        soundeiei.stopSound();
                    }
                    else {
                        score -= 100;
                        fallingObjects6.remove(i);
                        i--;
                    }
                }
            }
        }


        if (timeLeft <= 0 && !isGameOver) {
            isGameOver = true;
            gameTimer.stop();
            updateTimer.stop();
            objectSpawner.stop();
            objectSpawner2.stop();
            objectSpawner3.stop();
            objectSpawner4.stop();
            objectSpawner5.stop();
            objectSpawner6.stop();
            soundeiei.stopSound();

            }
    }
    private Rectangle restartButtonBounds = new Rectangle(400,400 , 200, 50);
    private void gameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", getWidth() / 2 - 150, getHeight() / 2 - 100);
        g.drawString("Score: " + score, getWidth() / 2 - 150, getHeight() / 2);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawRect(restartButtonBounds.x, restartButtonBounds.y, restartButtonBounds.width, restartButtonBounds.height);
        g.drawString("Restart Game", restartButtonBounds.x + 25, restartButtonBounds.y + 35);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(score>=1000){
            URL b = this.getClass().getResource("bg3.png");
            Image imagebg3 = new ImageIcon(b).getImage();
            g.drawImage(imagebg3,0,0,1000,600,this);
            for (FallingObject4 obj : fallingObjects4) {
                obj.paint(g2);
            }
            for (FallingObject5 obj : fallingObjects5) {
                obj.paint(g2);
            }
            for (FallingObject6 obj : fallingObjects6) {
                obj.paint(g2);
            }
        }
        else if(score>=500) {
            URL a = this.getClass().getResource("image2.png");
            Image imagebg2 = new ImageIcon(a).getImage();
            g.drawImage(imagebg2,0,0,1000,600,this);
            for (FallingObject4 obj : fallingObjects4) {
                obj.paint(g2);
            }
        }
        else if(score<500){
            URL imagebg = this.getClass().getResource("image.png");
            Image imagebg1 = new ImageIcon(imagebg).getImage();
            g.drawImage(imagebg1,0,0,1000,600,this);
        }

        g2.setColor(Color.WHITE);
        man.draw(g2);
        g2.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
        g2.drawString("Time left: " + timeLeft + "s", 800, 20);
        g2.drawString("Score: " + score, this.getWidth() / 2 - 50, 20);
        g2.drawString("Heart: " + collisionCount, 20, 20);

        if (isGameOver) {
            g2.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
            g2.setColor(Color.WHITE);
            g2.drawString("Game Over", this.getWidth() / 2 - 100, this.getHeight() / 2);
        }
        
        for (FallingObject obj : fallingObjects) {
            obj.paint(g2);
        }
        
        for (FallingObject2 obj : fallingObjects2) {
            obj.paint(g2);
        }

        for (FallingObject3 obj : fallingObjects3) {
            obj.paint(g2);
        }
        if(collisionCount<=0||timeLeft<=0){
            gameOver(g);
        }
    }
}


