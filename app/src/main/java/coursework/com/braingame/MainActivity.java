package coursework.com.braingame;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.logging.Level;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    public static final String MyPREFERENCES = "SavedGamePreferences" ;
    public static final String PlayerLevel = "playerLevelKey";
    public static final String QuestionNumber = "questionNumberKey";
    public static final String HintsStatus = "hintsKey";
    public static final String ScoreLevel = "scoreLevelKey";


    SharedPreferences sharedpreferences;

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
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String playerLevel = sharedpreferences.getString(PlayerLevel, "Novice");
        int questionNumber = sharedpreferences.getInt(QuestionNumber, 1);
        boolean hintsOnOrOff = sharedpreferences.getBoolean(HintsStatus, false);
        int score = sharedpreferences.getInt(ScoreLevel, 0);
        Intent intent = new Intent(this, GameActivity.class);
        Player player = new Player("novice", PreferencesActivity.isHintsOnOrOff());
        startActivity(intent);
        Log.d(TAG, "Data displayed here: "+ playerLevel + " === " + questionNumber + " === "+hintsOnOrOff+" === " +score);

    }

    public void aboutButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.instruction_head);
        builder.setMessage(R.string.instruction_body);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void exitButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.save_game_Head);
        builder.setMessage(R.string.save_game_body);
        final Intent intent = new Intent(this, MainActivity.class);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saveGameData();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).create();

        builder.setPositiveButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create();
        builder.show();
    }

    public void saveGameData() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        try {
            String playerLevel = LevelActivity.player.getPlayerLevel();
            int questionNumber = LevelActivity.player.getQuestionNumber();
            boolean isHintsOnOrOff = LevelActivity.player.isHintsOnOrOff();
            int score = LevelActivity.player.getScore();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(PlayerLevel, playerLevel);
            editor.putInt(QuestionNumber, questionNumber);
            editor.putBoolean(HintsStatus, isHintsOnOrOff);
            editor.putInt(ScoreLevel, score);
            editor.commit();
        }catch (NullPointerException e){

        }
    }
}
