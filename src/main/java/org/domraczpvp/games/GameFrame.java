package org.domraczpvp.games;

import javax.swing.*;
import java.awt.Canvas;

public class GameFrame extends JFrame {
    public GameFrame() {
        // Set window properties such as size, title, and close operation
        this.add(new GamePanel());
        this.setTitle("Falling Blocks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
