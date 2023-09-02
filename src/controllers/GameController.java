package controllers;

import models.Letter;
import models.Word;
import services.WordService;
import util.CustomKeyAdapter;
import views.GamePanel;
import views.LetterPanel;
import views.WordPanel;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {

    private GamePanel panel;
    private WordService wordService;
    private int wordIndex = 0;
    private int letterIndex = 0;
    private boolean isRunning = true;
    String goal;
    private ExecutorService executor = Executors.newSingleThreadExecutor();


    public GameController(GamePanel panel, WordService wordService) {
        this.panel = panel;
        this.wordService = wordService;
        this.goal = wordService.getRandomWord();
        setupKeyListener();
    }

    private LetterPanel getCurrentLetterPanel() {
        return panel.rows[wordIndex].getPanelAt(letterIndex);
    }

    private void showResetDialog(boolean isWin) {
        String title = isWin ? "You Win" : "You Lost";
        String description = isWin ? "Congratulations, you win at " + (wordIndex + 1) + " tries." : "The word was " + goal + ".";
        description += " Retry ?";
        int result = JOptionPane.showConfirmDialog(this.panel, description, title, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            reset();
        } else {
            System.exit(0);
        }
    }

    private void reset() {
        goal = wordService.getRandomWord();
        wordIndex = 0;
        letterIndex = 0;
        isRunning = true;
        SwingUtilities.invokeLater(() -> {
            for (WordPanel wordPanel : panel.rows) {
                for (int i = 0; i < wordPanel.getWordLength(); i++) {
                    wordPanel.getPanelAt(i).setLetter(null);
                }
            }
        });
    }

    private void setupKeyListener() {
        panel.addKeyListener(new CustomKeyAdapter() {
            @Override
            public void onEnter() {
                if (!isRunning || wordIndex >= 6 || letterIndex != 4 || getCurrentLetterPanel().getLetter() == null) {
                    return;
                }
                Letter[] letters = new Letter[5];
                WordPanel wordPanel = panel.rows[wordIndex];
                for (int i = 0; i < 5; i++) {
                    letters[i] = wordPanel.getPanelAt(i).getLetter();
                }
                Word word = new Word(letters);
                if (!wordService.isWord(word.toPlainText())) {
                    executor.execute(() -> {
                        isRunning = false;
                        JOptionPane.showMessageDialog(panel, "You must enter a valid word.", "INVALID WORD", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        isRunning = true;
                    });
                    return;
                }
                letterIndex = 0;
                word.compareTo(goal);
                SwingUtilities.invokeLater(() -> wordPanel.updateAll());
                if (word.isCorrect()) {
                    isRunning = false;
                    showResetDialog(true);
                    return;
                }
                wordIndex++;
                if (wordIndex >= 6) {
                    isRunning = false;
                    showResetDialog(false);
                }
            }

            @Override
            public void onBackspace() {
                if (!isRunning) {
                    return;
                }
                if (getCurrentLetterPanel().getLetter() == null) {
                    letterIndex = letterIndex > 0 ? letterIndex - 1 : letterIndex;
                }
                LetterPanel letterPanel = getCurrentLetterPanel();
                SwingUtilities.invokeLater(() -> letterPanel.setLetter(null));
            }

            @Override
            public void onLetter(char c) {
                if (!isRunning) {
                    return;
                }
                LetterPanel letterPanel = getCurrentLetterPanel();
                if (letterPanel.getLetter() != null) {
                    return;
                }
                Letter letter = new Letter(c);
                SwingUtilities.invokeLater(() -> letterPanel.setLetter(letter));
                letterIndex = letterIndex < 4 ? letterIndex + 1 : letterIndex;
            }

            @Override
            public void onInvalid() {
                if (!isRunning) {
                    return;
                }
            }
        });
    }
}
