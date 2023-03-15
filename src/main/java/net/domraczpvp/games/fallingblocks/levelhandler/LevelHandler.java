package net.domraczpvp.games.fallingblocks.levelhandler;

import net.domraczpvp.games.fallingblocks.levelhandler.levels.Level1;
import net.domraczpvp.games.fallingblocks.levelhandler.levels.Level2;

import java.util.ArrayList;
import java.util.List;

public class LevelHandler {
    public static final List<Level> levels = new ArrayList<>();
    static Level1 level1;
    static Level2 level2;

    static {
        levels.add(level1 = new Level1());
        levels.add(level2 = new Level2());
    }
}
