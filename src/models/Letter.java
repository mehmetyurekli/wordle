package models;

public class Letter {
    private char ch;
    private LetterState state;
    public Letter(char ch){
        this.ch = ch;
        this.state = LetterState.NOT_CHECKED;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public LetterState getState() {
        return state;
    }

    public void setState(LetterState state) {
        this.state = state;
    }
}
