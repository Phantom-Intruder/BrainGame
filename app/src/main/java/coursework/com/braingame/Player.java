package coursework.com.braingame;

import java.io.Serializable;

public class Player implements Serializable {
    private String playerLevel;
    private int questionNumber=0;
    private boolean hintsOnOrOff;
    private int score =0;

    Player(String playerLevel, boolean hintsOnOrOff){
        this.playerLevel = playerLevel;
        this.setHintsOnOrOff(hintsOnOrOff);
    }


    public void setPlayerLevel(String playerLevel){
        this.playerLevel = playerLevel;
    }

    public String getPlayerLevel() {
        return playerLevel;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
