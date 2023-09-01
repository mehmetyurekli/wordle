package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public abstract class CustomKeyAdapter extends KeyAdapter implements KeyListener {

    private static final Set<Character> alphabet;
    private static final Locale locale;

    static {
        locale = Locale.forLanguageTag("tr-TR");
        alphabet = new HashSet<>();
        char[] arr = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ".toCharArray();
        for(char c : arr){
            alphabet.add(c);
        }
    }

    public abstract void onEnter();

    public abstract void onBackspace();

    public abstract void onLetter(char c);

    public abstract void onInvalid();

    @Override
    public final void keyTyped(KeyEvent e) {

    }

    @Override
    public final void keyPressed(KeyEvent e) {

    }

    @Override
    public final void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            onEnter();
        }
        else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            onBackspace();
        }
        else if(alphabet.contains(Character.toUpperCase(e.getKeyChar()))){
            char c = (e.getKeyChar()+"").toUpperCase(locale).charAt(0);
            onLetter(c);
        }
        else {
            onInvalid();
        }
    }
}
