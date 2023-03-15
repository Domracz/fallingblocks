package net.domraczpvp.games.fallingblocks.level;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(int level) {
        // Set window properties such as size, title, and close operation
        this.add(new GamePanel(level));
        this.setTitle("Falling Blocks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
