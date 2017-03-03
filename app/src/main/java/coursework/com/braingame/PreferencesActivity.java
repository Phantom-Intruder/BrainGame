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

    private Toolbar mToolbar;
    private static boolean hintsOnOrOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Preferences");
        ToggleButton toggleButton = ((ToggleButton) findViewById(R.id.hint_toggle_button));
        Log.d(TAG, "Data displayed here: " + hintsOnOrOff);
        if (hintsOnOrOff){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }
        Log.d(TAG, "Data displayed here: " + hintsOnOrOff);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }

    public static boolean isHintsOnOrOff() {
        return hintsOnOrOff;
    }

    public static void setHintsOnOrOff(boolean hintsOnOrOffVar){
        hintsOnOrOff = hintsOnOrOffVar;
    }

    public void setHintsOffOrOn(View view) {
        hintsOnOrOff = !hintsOnOrOff;
    }
}
