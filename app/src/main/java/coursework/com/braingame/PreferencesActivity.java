package coursework.com.braingame;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

import static android.content.ContentValues.TAG;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Preferences");
        ToggleButton toggleButton = ((ToggleButton) findViewById(R.id.hint_toggle_button));
        Log.d(TAG, "Data displayed here: " + PlayerManagementClass.player.getHintsOnOrOff());
        if (PlayerManagementClass.player.getHintsOnOrOff()){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }
        Log.d(TAG, "Data displayed here: " + PlayerManagementClass.player.getHintsOnOrOff());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }

    public static boolean isHintsOnOrOff() {
        Log.d(TAG, "Hints are on: "+PlayerManagementClass.player.getHintsOnOrOff());
        return PlayerManagementClass.player.getHintsOnOrOff();
    }

    public void setHintsOffOrOn(View view) {
        PlayerManagementClass.player.setHintsOnOrOff(!PlayerManagementClass.player.getHintsOnOrOff());
    }
}
