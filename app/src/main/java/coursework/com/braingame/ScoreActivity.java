package coursework.com.braingame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView textView = ((TextView)findViewById(R.id.textView10));
        textView.setText(String.format("%d", LevelActivity.player.getScore()));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this,MainActivity.class);
        startActivity(setIntent);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        LevelActivity.player = new Player(LevelActivity.player.getPlayerLevel(), PreferencesActivity.isHintsOnOrOff());
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
