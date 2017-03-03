package coursework.com.braingame;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
    //Level screen buttons
    public void noviceButtonClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        PlayerManagementClass playerManagementClass = new PlayerManagementClass();
        playerManagementClass.createPlayer("novice", PreferencesActivity.isHintsOnOrOff());
        startActivity(intent);
    }

    public void easyButtonClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        PlayerManagementClass playerManagementClass = new PlayerManagementClass();
        playerManagementClass.createPlayer("easy", PreferencesActivity.isHintsOnOrOff());
        startActivity(intent);
    }

    public void mediumButtonClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        PlayerManagementClass playerManagementClass = new PlayerManagementClass();
        playerManagementClass.createPlayer("medium", PreferencesActivity.isHintsOnOrOff());
        startActivity(intent);
    }

    public void guruButtonClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        PlayerManagementClass playerManagementClass = new PlayerManagementClass();
        playerManagementClass.createPlayer("guru", PreferencesActivity.isHintsOnOrOff());
        startActivity(intent);
    }
}
