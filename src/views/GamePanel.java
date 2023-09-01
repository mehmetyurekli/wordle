package views;

import models.Letter;
import models.LetterState;
import net.miginfocom.swing.MigLayout;
import util.CustomKeyAdapter;

import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel {

    public WordPanel[] rows = new WordPanel[6];

    public GamePanel() {
        init();
    }

    private void init() {
        this.setLayout(new MigLayout("insets 0, gapy 10, gapx 0", "[]", "10[][][][][]10"));
        for(int i = 0; i<rows.length; i++){
            rows[i] = new WordPanel();
            this.add(rows[i], "cell 0 " + i + " 1 1");
        }
        this.setFocusable(true);
        this.requestFocus();
    }
}
