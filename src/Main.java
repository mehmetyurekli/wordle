import services.MyWordService;
import services.WordService;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        WordService wordService = new MyWordService();
        File txt = new File(Main.class.getClassLoader().getResource("turkish.txt").getPath());
        wordService.load(txt);

        SwingUtilities.invokeLater(() -> {
            MainFrame gameFrame = new MainFrame(wordService);
        });
    }
}