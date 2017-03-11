package coursework.com.braingame;

import java.io.Serializable;

class Player implements Serializable {
    private String playerLevel;
    private int questionNumber=0;
    private int score =0;
    private boolean hintsOnOrOff;

    Player(String playerLevel){
        this.playerLevel = playerLevel;
        this.hintsOnOrOff = false;
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

    void setHintsOnOrOff(boolean hintsOnOrOff) {
        this.hintsOnOrOff = hintsOnOrOff;
    }

     boolean getHintsOnOrOff(){
        return hintsOnOrOff;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}
