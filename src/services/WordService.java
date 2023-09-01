package services;

import java.io.File;

public interface WordService {
    void load(File file);
    boolean isWord(String word);
    String getRandomWord();
}
