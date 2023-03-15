package net.domraczpvp.games.fallingblocks.renderer;

import net.domraczpvp.games.fallingblocks.levelhandler.LevelCompleteHandler;
import net.domraczpvp.games.fallingblocks.levelhandler.LevelHandler;

import javax.swing.*;
import java.awt.*;

public class JListRender extends DefaultListCellRenderer {
    private JList<JButton> optionList;
    private Boolean[] value;

    public JListRender() {
        value = LevelCompleteHandler.completions;
    }
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JButton button = (JButton) value;
        button.setRolloverEnabled(true);
        LevelCompleteHandler h = new LevelCompleteHandler();
        if (LevelCompleteHandler.completions[index]) {
            button.setBackground(new Color(0,255,0,0));
            System.out.println("ye");
        }else if (index - 1 != -1 && LevelCompleteHandler.completions[index] != null && LevelCompleteHandler.completions[index - 1]) {
            button.setBackground(Color.blue);
            System.out.println("nex");
        }else if (index - 1 == -1){
            button.setBackground(new Color(0, 0, 255, 0));
            System.out.println("nex");
        }else{
            button.setBackground(new Color(255, 0, 0, 0));
            System.out.println("no");
        }
        return button;
    }
}
