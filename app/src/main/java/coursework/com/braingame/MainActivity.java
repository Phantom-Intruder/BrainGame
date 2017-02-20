package coursework.com.braingame;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesActivity.setHintsOnOrOff(false);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //TODO: Preferences
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
        return true;
    }

    //Main screen buttons
    public void newGameButtonClicked(View view) {
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }

    public void continueButtonClicked(View view) {
        //TODO: Start saved game
    }

    public void aboutButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instructions");
        builder.setMessage("You shall be presented with an mathematical problem, which will depend on the level of skill you choose.\n\nYou must type in the answer to the questions within the time limit.\n\nYou may use the hints option in the preferences menu to help you with the game.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void exitButtonClicked(View view) {
        Intent intent = new Intent(this, ExitActivity.class);
        startActivity(intent);
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }





}
