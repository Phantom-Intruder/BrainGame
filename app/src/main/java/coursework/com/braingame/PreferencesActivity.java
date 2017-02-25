package coursework.com.braingame;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

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
        ToggleButton toggleButton = ((ToggleButton) findViewById(R.id.toggleButton2));
        if (hintsOnOrOff){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }
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
