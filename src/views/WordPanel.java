package views;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class WordPanel extends JPanel {

    private LetterPanel[] panels = new LetterPanel[5];

    public WordPanel(){
        this.setLayout(new MigLayout("insets 0, gapx 10, gapy 0", "10[][][][][]10", "[]"));
        for(int i = 0; i<panels.length;i++){
            panels[i] = new LetterPanel();
            this.add(panels[i], "cell " + i + " 0 1 1");
        }
    }

    public LetterPanel getPanelAt(int index){
        return panels[index];
    }

    public int getWordLength(){
        return panels.length;
    }

    public void updateAll(){
        for(int i = 0; i<panels.length;i++){
            panels[i].update();
        }
    }
}
