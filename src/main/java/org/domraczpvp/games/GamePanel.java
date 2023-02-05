package org.domraczpvp.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    static final int DELAY = 175;
    final int[] blockx = new int[GAME_UNITS];
    final int[] blocky = new int[GAME_UNITS];
    final Boolean[] blocka = new Boolean[GAME_UNITS];
    int score;
    int padX;
    int blocksAutoPlaced = 0;
    int padY;
    int blocks = 0;
    boolean running = false;
    Timer StartBlockGen;
    int startblocks = 1;
    Timer timer;
    Timer quittimer;
    Random random;
    String[] LoseSplashes = {"Rest In Peace.", "The nuke blew up!", "BOOM", "Block #69420 has escaped your collector!", "OOF", "You. Died.", "Game Over.", "Never gonna give you up", "L+Bozo", "\"ThE gAmE iS hAcKiNg\""};

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame() {
        Arrays.fill(blocka, false);
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
        padX = SCREEN_WIDTH/2;
        padY = getGridPos(5, 13)[1];
        newBlock();
        blocksAutoPlaced++;
        StartBlockGen = new Timer(1000, e -> {
            if (blocksAutoPlaced != startblocks) {
                newBlock();
                blocksAutoPlaced++;
            }else{
                StartBlockGen.stop();
            }
        });
        StartBlockGen.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public int[] getGridPos(int x, int y) {
        return new int[]{x*50, y*50};
    }
    public void draw(Graphics g) {

        if(running) {
            for(int i = 0; i < blocks;i++) {
                if (blocka[i]) {

                    if (i == 0) {
                        g.setColor(Color.red);
                        g.fillRect(blockx[i], blocky[i], UNIT_SIZE, UNIT_SIZE);
                    } else {
                        g.setColor(new Color(51, 255, 0));
                        //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                        g.fillRect(blockx[i], blocky[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }
            g.setColor(Color.red);
            g.fillOval(padX, padY, UNIT_SIZE, UNIT_SIZE);
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+score, (SCREEN_WIDTH - metrics.stringWidth("Score: "+score))/2, g.getFont().getSize());
        }
        else {
            if (score != 30) {
                gameOver(g);
            }else{
                gameWon(g);
            }
        }

    }
    public void newBlock(){
        blockx[blocks] = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        blocky[blocks] = getGridPos(0, 0)[1];
        blocka[blocks] = true;
        while (blockx[blocks] == padX && blocky[blocks] == padY || blocky[blocks] > padY) {
            blockx[blocks] = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            blocky[blocks] = getGridPos(0, 0)[1];
            blocka[blocks] = true;
        }
        blocks++;
    }
    public void move(char direction){
        switch (direction){
            case 'L':
                if(padX - UNIT_SIZE < 0) return;
                padX = padX - UNIT_SIZE;
                break;
            case 'R':
                if(padX + UNIT_SIZE >= SCREEN_WIDTH) return;
                padX = padX + UNIT_SIZE;
                break;
        }

    }
    public void move(int xsquare){
        if (UNIT_SIZE*xsquare > SCREEN_WIDTH) return;
        if (UNIT_SIZE*xsquare < 0) return;
        padX = UNIT_SIZE*xsquare;

    }

    public void checkHit() {
        for (int i = 0; i<blocks; i++) {
            if((blockx[i] == padX) && (blocky[i] == padY) && blocka[i]) {
                score++;
                blocka[i] = false;
                blockx[i] = 0;
                blocky[i] = 0;
                blocks--;
                newBlock();
                System.out.println("HIT");
            }
        }
    }
    public void checkFall() {
        for (int i = 0; i<blocks; i++) {
            if(blocky[i] > padY && blocka[i]) {
                running = false;
            }
        }

        if(!running) {
            timer.stop();
        }
    }
    public void makefall() {
        for (int i = 0; i<blocks; i++) {
            if (blocka[i]) {
                blocky[i] = blocky[i] + UNIT_SIZE;
            }
        }
    }
    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+score, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+score))/2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        int splash = random.nextInt(LoseSplashes.length);
        System.out.println(splash);
        g.drawString(LoseSplashes[splash], (SCREEN_WIDTH - metrics2.stringWidth(LoseSplashes[splash]))/2, SCREEN_HEIGHT/2);

        quittimer = new Timer(6000, e -> System.exit(0));
        quittimer.start();
    }

    public void checkWon() {

    }
    public void gameWon(Graphics g) {
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+score, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+score))/2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        int splash = random.nextInt(LoseSplashes.length);
        System.out.println(splash);
        g.drawString("You Win!", (SCREEN_WIDTH - metrics2.stringWidth("You Win!"))/2, SCREEN_HEIGHT/2);

        quittimer = new Timer(6000, e -> System.exit(0));
        quittimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            checkHit();
            checkFall();
            makefall();
            checkWon();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    move('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    move('R');
                    break;
                case KeyEvent.VK_Q:
                    move(0);
                    break;
                case KeyEvent.VK_W:
                    move(1);
                    break;
                case KeyEvent.VK_E:
                    move(2);
                    break;
                case KeyEvent.VK_R:
                    move(3);
                    break;
                case KeyEvent.VK_T:
                    move(4);
                    break;
                case KeyEvent.VK_Y:
                    move(5);
                    break;
                case KeyEvent.VK_U:
                    move(6);
                    break;
                case KeyEvent.VK_I:
                    move(7);
                    break;
                case KeyEvent.VK_O:
                    move(8);
                    break;
                case KeyEvent.VK_P:
                    move(9);
                    break;
                case KeyEvent.VK_OPEN_BRACKET:
                    move(10);
                    break;
                case KeyEvent.VK_CLOSE_BRACKET:
                    move(11);
                    break;
                case KeyEvent.VK_BACK_SLASH:
                    move(12);
                    break;
                case KeyEvent.VK_PAGE_UP:
                    move(13);
                    break;
            }
        }
    }
}
