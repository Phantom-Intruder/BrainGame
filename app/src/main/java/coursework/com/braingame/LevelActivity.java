package coursework.com.braingame;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select level");
    }

    //Handle title bar actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            default:
                this.finish();
                return true;
        }
    }

    //Level screen buttons
    //Any current instance of player object must be destroyed before starting new game
    public void noviceButtonClicked(View view) {
        //Create player object with novice level
        Intent intent = new Intent(this, GameActivity.class);
        Player.getInstanceOfObject().destroyInstance();
        Player.getInstanceOfObject().setPlayerLevel("novice");
        startActivity(intent);
    }

    public void easyButtonClicked(View view) {
        //Create player object with easy level
        Intent intent = new Intent(this, GameActivity.class);
        Player.getInstanceOfObject().destroyInstance();
        Player.getInstanceOfObject().setPlayerLevel("easy");
        startActivity(intent);
    }

    public void mediumButtonClicked(View view) {
        //Create player object with medium  level
        Intent intent = new Intent(this, GameActivity.class);
        Player.getInstanceOfObject().destroyInstance();
        Player.getInstanceOfObject().setPlayerLevel("medium");
        startActivity(intent);
    }

    public void guruButtonClicked(View view) {
        //Create player object with guru level
        Intent intent = new Intent(this, GameActivity.class);
        Player.getInstanceOfObject().destroyInstance();
        Player.getInstanceOfObject().setPlayerLevel("guru");
        startActivity(intent);
    }
}
