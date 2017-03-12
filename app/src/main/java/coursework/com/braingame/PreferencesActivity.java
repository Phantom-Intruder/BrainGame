package coursework.com.braingame;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

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
        if (Player.getInstanceOfObject().getHintsOnOrOff()){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }
    }

    //Handle back button clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }

    //Turn hints on or off
    public static boolean isHintsOnOrOff() {
        return Player.getInstanceOfObject().getHintsOnOrOff();
    }

    public void setHintsOffOrOn(View view) {
        Player.getInstanceOfObject().setHintsOnOrOff(!Player.getInstanceOfObject().getHintsOnOrOff());
    }
}
