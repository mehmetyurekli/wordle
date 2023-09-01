package models;

public enum LetterState {
    INVALID(0), VALID(1), POSITION_INVALID(2), NOT_CHECKED(3);

    private final int value;
    LetterState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LetterState valueOf(int value){
        for(LetterState state : LetterState.values()){
            if(state.getValue() == value){
                return state;
            }
        }
        return NOT_CHECKED;
    }
}
