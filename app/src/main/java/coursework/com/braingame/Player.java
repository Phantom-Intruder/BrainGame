package coursework.com.braingame;

import java.io.Serializable;

class Player implements Serializable {
    private String playerLevel;
    private int questionNumber=0;
    private int score =0;

    Player(String playerLevel, boolean hintsOnOrOff){
        this.playerLevel = playerLevel;
        this.setHintsOnOrOff(hintsOnOrOff);
    }

    String getPlayerLevel() {
        return playerLevel;
    }

    int getQuestionNumber() {
        return questionNumber;
    }

    void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    private void setHintsOnOrOff(boolean hintsOnOrOff) {
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}
