package coursework.com.braingame;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public static final String SAVED_GAME_PREFERENCES = "SavedGamePreferences" ;
    public static final String PlayerLevel = "playerLevelKey";
    public static final String QuestionNumber = "questionNumberKey";
    public static final String HintsStatus = "hintsKey";
    public static final String ScoreLevel = "scoreLevelKey";


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appToolbar);
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



    //Main screen button actions


    public void newGameButtonClicked(View view) {
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }

    public void continueButtonClicked(View view) {
        //Get the data from shared preferences
        sharedpreferences = getSharedPreferences(SAVED_GAME_PREFERENCES, Context.MODE_PRIVATE);
        String playerLevel = sharedpreferences.getString(PlayerLevel, "Novice");
        int questionNumber = sharedpreferences.getInt(QuestionNumber, 1);
        boolean hintsOnOrOff = sharedpreferences.getBoolean(HintsStatus, false);
        int score = sharedpreferences.getInt(ScoreLevel, 0);
        Intent intent = new Intent(this, GameActivity.class);
        //Create a new player from the playerManagementClass, set the data and start the game
        PlayerManagementClass playerManagementClass = new PlayerManagementClass();
        playerManagementClass.createPlayer(playerLevel);
        PlayerManagementClass.player.setHintsOnOrOff(hintsOnOrOff);
        PlayerManagementClass.player.setQuestionNumber(questionNumber);
        PlayerManagementClass.player.setScore(score);
        startActivity(intent);
    }

    public void aboutButtonClicked(View view) {
        //Show an alert dialog when the about button is clicked
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.instruction_head);
        builder.setMessage(R.string.instruction_body);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void exitButtonClicked(View view) {
        //Show an alert dialog when the exit button is clicked to ask the user to save the game
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.save_game_Head);
        builder.setMessage(R.string.save_game_body);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //If user responds yes then save the game data and exit
                saveGameData();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).create();

        builder.setPositiveButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Else just exit
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).create();
        builder.show();
    }

    public void saveGameData() {
        sharedpreferences = getSharedPreferences(SAVED_GAME_PREFERENCES, Context.MODE_PRIVATE);
        try {
            //Use shared preferences to save the game data
            String playerLevel = PlayerManagementClass.player.getPlayerLevel();
            int questionNumber = PlayerManagementClass.player.getQuestionNumber();
            int score = PlayerManagementClass.player.getScore();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(PlayerLevel, playerLevel);
            editor.putInt(QuestionNumber, questionNumber);
            //Log.d(TAG, "Hints are on: "+PreferencesActivity.isHintsOnOrOff());
            editor.putBoolean(HintsStatus, PreferencesActivity.isHintsOnOrOff());
            editor.putInt(ScoreLevel, score);
            editor.apply();
        }catch (NullPointerException ignored){

        }
    }
}
