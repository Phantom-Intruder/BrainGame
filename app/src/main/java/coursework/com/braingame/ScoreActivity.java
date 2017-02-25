package coursework.com.braingame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
