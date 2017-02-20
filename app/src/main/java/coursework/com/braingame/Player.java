package coursework.com.braingame;

import java.io.Serializable;

public class Player implements Serializable {
    private String playerLevel;
    private int questionNumber;
    private boolean hintsOnOrOff;

    Player(String playerLevel, boolean hintsOnOrOff){
        this.playerLevel = playerLevel;
        this.setHintsOnOrOff(hintsOnOrOff);
    }

    public String getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(String playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public boolean isHintsOnOrOff() {
        return hintsOnOrOff;
    }

    public void setHintsOnOrOff(boolean hintsOnOrOff) {
        this.hintsOnOrOff = hintsOnOrOff;
    }
}
