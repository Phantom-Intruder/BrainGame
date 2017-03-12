package coursework.com.braingame;

//Singleton class that has a player object that can be accessed by all other classes
class Player {
    private String playerLevel;
    private int questionNumber=0;
    private int score =0;
    private boolean hintsOnOrOff;
    private static Player instanceOfObject;

    private Player(){
        this.hintsOnOrOff = false;
    }

    static synchronized Player getInstanceOfObject()
    {
        if (instanceOfObject == null)
            instanceOfObject = new Player();

        return instanceOfObject;
    }

    void setPlayerLevel(String playerLevel){
        this.playerLevel = playerLevel;
    }

    void destroyInstance(){
        instanceOfObject = null;
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
