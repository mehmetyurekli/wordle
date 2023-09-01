package models;

import util.ConsoleColors;

import java.util.HashMap;
import java.util.Map;

public class Word {
    private Letter[] word;
    public Word(String input){
        word = new Letter[input.length()];
        char[] charArray = input.toCharArray();
        for(int i=0; i<charArray.length; i++){
            word[i] = new Letter(charArray[i]);
        }
    }

    public Word(Letter[] letters){
        this.word = letters;
    }

    public void compareTo(String goal){
        Map<Character, Integer> charMap = new HashMap<>();
        char[] goalArray = goal.toCharArray();
        for(int i = 0; i<goal.length(); i++){
            Integer count = charMap.getOrDefault(goalArray[i], 0);
            charMap.put(goalArray[i], count+1);
        }

        for(int i= 0; i<goal.length(); i++) {
            if (word[i].getCh() == goalArray[i]) {
                word[i].setState(LetterState.VALID);
                charMap.put(goalArray[i], charMap.get(goalArray[i]) - 1);
            }
        }

        for(int i= 0; i<goal.length(); i++){
            if(word[i].getCh() == goalArray[i]){
                word[i].setState(LetterState.VALID);
            }
            else if(charMap.getOrDefault(word[i].getCh(), 0) != 0){
                word[i].setState(LetterState.POSITION_INVALID);
                charMap.put(word[i].getCh(), charMap.get(word[i].getCh())-1);
            }
            else{
                word[i].setState(LetterState.INVALID);
            }
        }
    }

    public boolean isCorrect(){
        for(int i = 0; i<word.length; i++){
            if(word[i].getState() != LetterState.VALID){
                return false;
            }
        }
        return true;
    }

    public String toPlainText(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<word.length; i++){
            sb.append(word[i].getCh());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< word.length;i++){
            if(word[i].getState() == LetterState.VALID){
                sb.append(ConsoleColors.GREEN);
                sb.append(word[i].getCh());
                sb.append(ConsoleColors.RESET);
            }
            else if(word[i].getState() == LetterState.POSITION_INVALID){
                sb.append(ConsoleColors.YELLOW);
                sb.append(word[i].getCh());
                sb.append(ConsoleColors.RESET);
            }
            else{
                sb.append(ConsoleColors.RESET);
                sb.append(word[i].getCh());
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}
