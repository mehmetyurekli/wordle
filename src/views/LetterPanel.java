package views;

import models.Letter;
import models.LetterState;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LetterPanel extends JPanel {
    private Letter letter;
    private Color background;
    private Color foreground;
    private JLabel label;

    public LetterPanel(){
        this.setLayout(new MigLayout("", "[68]", "[68]"));
        background = Color.WHITE;
        foreground = Color.BLACK;
        label = new JLabel("");
        Font font = label.getFont();
        label.setFont(new Font(font.getFontName(), Font.BOLD, 20));
        this.setBackground(background);
        label.setForeground(foreground);
        this.add(label, "cell 0 0 1 1, align center");
    }

    public void update(){
        String text = "";
        if(letter == null){
            background = Color.WHITE;
            foreground = Color.BLACK;
        }
        else{
            text = letter.getCh() + "";
            foreground = Color.WHITE;
            background = switch (letter.getState()) {
                case INVALID -> Color.DARK_GRAY;
                case VALID -> Color.GREEN;
                case POSITION_INVALID -> Color.ORANGE;
                default -> Color.WHITE;
            };
            if(letter.getState() == LetterState.NOT_CHECKED){
                foreground = Color.BLACK;
            }
        }
        this.setBackground(background);
        label.setForeground(foreground);
        label.setText(text);
        this.revalidate();
        this.repaint();
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
        this.update();
    }
}
