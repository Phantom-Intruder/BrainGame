package coursework.com.braingame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class GameActivity extends AppCompatActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play game");
        String levelOfPlayer = LevelActivity.player.getPlayerLevel();
        switch (levelOfPlayer){
            case "novice":
                //Novice
                novice();
                break;
            case "easy":
                //easy
                easy();
                break;
            case "medium":
                //medium
                medium();
                break;
            case "guru":
                //guru
                guru();
                break;
        }
    }

    private void guru() {

    }

    private void medium() {

    }

    private void easy() {

    }

    private void novice() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                return true;
        }

    }

    public void gameLogic(){

    }

    //Number pad buttons
    public void onButtonZeroClicked(View view) {

    }

    public void onButtonOneClicked(View view) {
    }

    public void onButtonTwoClicked(View view) {
    }

    public void onButtonThreeClicked(View view) {
    }

    public void onButtonFourClicked(View view) {
    }

    public void onButtonFiveClicked(View view) {
    }

    public void onButtonSixClicked(View view) {
    }

    public void onButtonSevenClicked(View view) {
    }

    public void onButtonEightClicked(View view) {
    }

    public void onButtonNineClicked(View view) {
    }

    public void onButtonDelClicked(View view) {
    }

    public void onButtonHashClicked(View view) {
    }

    public void onButtonMinusClicked(View view) {
    }

}
