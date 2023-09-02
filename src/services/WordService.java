package services;
import java.io.InputStream;

public interface WordService {
    void load(InputStream stream);
    boolean isWord(String word);
    String getRandomWord();
}
