package net.domraczpvp.games.fallingblocks.levelhandler.levels;

import net.domraczpvp.games.fallingblocks.levelhandler.Level;

public class Level2 extends Level {
    @Override
    public int getid() {
        return 2;
    }

    @Override
    public boolean enemys() {
        return true;
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
        return 15;
    }

    @Override
    public String[] levelnotes() {
        return new String[0];
    }
}
