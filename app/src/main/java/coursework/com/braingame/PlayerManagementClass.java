package coursework.com.braingame;


public class PlayerManagementClass {
    public static Player player;

    void createPlayer(String level, boolean hintsStatus){
        player = new Player(level, hintsStatus);
    }
}
