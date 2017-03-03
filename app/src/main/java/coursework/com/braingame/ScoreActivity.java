package coursework.com.braingame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView textView = ((TextView)findViewById(R.id.score));
        textView.setText(String.format("%d", PlayerManagementClass.player.getScore()));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this,MainActivity.class);
        startActivity(setIntent);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        PlayerManagementClass.player = new Player(PlayerManagementClass.player.getPlayerLevel(), PreferencesActivity.isHintsOnOrOff());
        startActivity(intent);

    }

    public void goToMainMenue(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void changeLevel(View view) {
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
}
