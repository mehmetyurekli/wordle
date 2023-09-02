import services.MyWordService;
import services.WordService;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        WordService wordService = new MyWordService();
        wordService.load(Main.class.getResourceAsStream("/turkish.txt"));

        SwingUtilities.invokeLater(() -> {
            MainFrame gameFrame = new MainFrame(wordService);
        });
    }
}