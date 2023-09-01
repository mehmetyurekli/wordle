import com.formdev.flatlaf.FlatIntelliJLaf;
import controllers.GameController;
import services.WordService;
import views.GamePanel;

import javax.swing.*;

public class MainFrame extends JFrame {

    private GameController gameController;
    private WordService wordService;
    public MainFrame(WordService wordService){
        this.wordService = wordService;
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initWindow();
    }

    private void initWindow(){
        this.setSize(400,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        GamePanel panel = new GamePanel();
        this.getContentPane().add(panel);
        gameController = new GameController(panel, wordService);
        this.setVisible(true);
    }
}
