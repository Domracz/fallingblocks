package net.domraczpvp.games.fallingblocks.levelhandler;

public abstract class Level {
    public abstract int getid();
    public abstract boolean enemys();
    public abstract boolean statusbeam();
    public abstract int lowenemyspeed();

    public abstract int completescore();
    public abstract String[] levelnotes();


}
