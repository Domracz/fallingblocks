package net.domraczpvp.games.fallingblocks.levelhandler.levels;

import net.domraczpvp.games.fallingblocks.levelhandler.Level;

public class Level1 extends Level {
    @Override
    public int getid() {
        return 1;
    }

    @Override
    public boolean enemys() {
        return false;
    }

    @Override
    public boolean statusbeam() {
        return false;
    }

    @Override
    public int lowenemyspeed() {
        return 1;
    }

    @Override
    public int completescore() {
        return 10;
    }

    @Override
    public String[] levelnotes() {
        return new String[]{"Score 30 Required to pass."};
    }
}
