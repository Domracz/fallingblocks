package net.domraczpvp.games.fallingblocks.levelhandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class LevelCompleteHandler {
    public static final Boolean[] completions = new Boolean[LevelHandler.levels.size()];


    public LevelCompleteHandler() {
        Arrays.fill(completions, false);
    }
    public void setCompleted(int l, boolean value) {
        completions[l] = value;
    }

    public void save() {
        try {
            File file = new File("completions.txt");
            FileWriter wr = new FileWriter(file, false);
            BufferedWriter wr1 = new BufferedWriter(wr);
            for (int i = 0; i < completions.length; i++) {
                if (String.valueOf(completions[i]).equals("null")) {
                    throw new NullPointerException();
                }
                wr1.write(String.valueOf(completions[i]));
                wr1.newLine();
            }
            wr1.close();
        }catch (Exception e) {
            System.out.println("An error occured while saving.");
            e.printStackTrace();
        }

    }

    public void load() {

    }
}
