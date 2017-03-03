package coursework.com.braingame;


class PlayerManagementClass {
    static Player player;

    void createPlayer(String level, boolean hintsStatus){
        player = new Player(level, hintsStatus);
    }
}
