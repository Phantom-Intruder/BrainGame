package coursework.com.braingame;


class PlayerManagementClass {
    static Player player;

    void createPlayer(String level){
        player = new Player(level);
    }
}
