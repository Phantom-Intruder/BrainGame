package coursework.com.braingame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import static android.content.ContentValues.TAG;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;



public class GameActivity extends AppCompatActivity {
    private EditText editText;
    private BaseInputConnection textFieldInputConnection;
    private int total;
    private static CountDownTimer timer;
    private ProgressBar progressBar;
    private int levelOfProgressbarFilled;
    private TextView textView;
    private int numberOfhashButtonClicks = 0;
    private int timerRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play game");
        editText = (EditText)findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_NULL);
        LevelActivity.player.setQuestionNumber(LevelActivity.player.getQuestionNumber()+1);
        Log.d(TAG, "TotalFor: player question " + LevelActivity.player.getQuestionNumber());
        textFieldInputConnection = new BaseInputConnection(editText, true);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        }
        String levelOfPlayer = LevelActivity.player.getPlayerLevel();
        switch (levelOfPlayer){
            case "novice":
                //Novice
                novice();
                break;
            case "easy":
                //easy
                easy();
                break;
            case "medium":
                //medium
                medium();
                break;
            case "guru":
                //guru
                guru();
                break;
        }
        timerMethod();
    }

    private void timerMethod() {
        levelOfProgressbarFilled = 0;

        progressBar = (ProgressBar)findViewById(R.id.progressBar5);
        textView = ((TextView)findViewById(R.id.textView9));
        timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                levelOfProgressbarFilled += 10;
                progressBar.setProgress(levelOfProgressbarFilled);
                timerRemaining = (10 - levelOfProgressbarFilled / 10);
                textView.setText(String.format("%d", timerRemaining));
            }
            public void onFinish() {
                moveToNextQuestion();
            }

        }.start();
    }

    private void moveToNextQuestion() {
        if (LevelActivity.player.getQuestionNumber() == 4){
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else{
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private void novice() {
        generateDataToShowOnScreen(2,2);
    }

    private void easy() {
        generateDataToShowOnScreen(3,2);
    }

    private void medium() {
        generateDataToShowOnScreen(4,2);
    }
    
    private void guru() {
        generateDataToShowOnScreen(6,4);
    }

    private void generateDataToShowOnScreen(int upperbound, int lowerbound) {
        Random ran = new Random();
        int numberOfLoops = ran.nextInt(upperbound-lowerbound + 1) + lowerbound;
        Log.d(TAG, "Data displayed here: "+ numberOfLoops+ " "+ upperbound + " " + lowerbound);
        int[] dataToBeDisplayed = new int[numberOfLoops];
        for (int i = 0; i < numberOfLoops; i++) {
            dataToBeDisplayed[i] = generateRandomNumbers();
        }
        showDataOnScreen(dataToBeDisplayed);
    }

    private void showDataOnScreen(int[] dataToBeDisplayed) {
        TextView displayPanel = (TextView) findViewById(R.id.editText);
        String stringToBeShown = "";
        total = 0;
        Random ran = new Random();
        int firstCount = 0;
        for (int i : dataToBeDisplayed){
            String currentChar = "" + i;
            if (firstCount == 0){
                total = i;
                stringToBeShown = stringToBeShown.concat(currentChar);
                firstCount++;
            }else {
                currentChar = "" + i;
                int symbol = ran.nextInt(4) + 1;
                switch (symbol) {
                    case 1:
                        total += i;
                        stringToBeShown = stringToBeShown.concat("+");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                    case 2:
                        total -= i;
                        stringToBeShown = stringToBeShown.concat("-");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                    case 3:
                        total *= i;
                        stringToBeShown = stringToBeShown.concat(" x ");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                    case 4:
                        total /= i;
                        stringToBeShown = stringToBeShown.concat("/");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                }
                Log.d(TAG, "TotalFor: " + total);
            }
        }

        displayPanel.setText(String.format("%s = ?", stringToBeShown));
    }


    private int generateRandomNumbers() {
        Random ran = new Random();
        return ran.nextInt(999)+1;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.stop_game);
        builder.setMessage(R.string.stop_text);
        final Intent intent = new Intent(this, MainActivity.class);

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //saveGameData();
                timer.cancel();
                startActivity(intent);
                finish();
            }
        }).create();

        builder.setPositiveButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();


        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                return true;
        }

    }

    //Number pad buttons

    public void onButtonZeroClicked(View view) {
        placeDigitOnEditText("0");
    }

    public void onButtonOneClicked(View view) {
        placeDigitOnEditText("1");
    }

    public void onButtonTwoClicked(View view) {
        placeDigitOnEditText("2");
    }

    public void onButtonThreeClicked(View view) {
        placeDigitOnEditText("3");
    }

    public void onButtonFourClicked(View view) {
        placeDigitOnEditText("4");
    }

    public void onButtonFiveClicked(View view) {
        placeDigitOnEditText("5");
    }

    public void onButtonSixClicked(View view) {
        placeDigitOnEditText("6");
    }

    public void onButtonSevenClicked(View view) {
        placeDigitOnEditText("7");
    }

    public void onButtonEightClicked(View view) {
        placeDigitOnEditText("8");
    }

    public void onButtonNineClicked(View view) {
        placeDigitOnEditText("9");
    }

    private void placeDigitOnEditText(String text) {
        String textOnEditText = String.valueOf(editText.getText());
        if ((textOnEditText.charAt(textOnEditText.length()-1))=='?') {
            editText.setSelection(editText.getText().length());
            textOnEditText = textOnEditText.replace('?', ' ');
            editText.setText(textOnEditText);
        }
            editText = (EditText) findViewById(R.id.editText);
            editText.append(text);

    }

    public void onButtonDelClicked(View view) {
        editText.setSelection(editText.getText().length());
        String textOnEditText = String.valueOf(editText.getText());
        if ((textOnEditText.charAt(textOnEditText.length()-1))!='=') {
            textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        }
    }

    public void onButtonHashClicked(View view) {
        if ((numberOfhashButtonClicks >= 1)&&(!PreferencesActivity.isHintsOnOrOff()) ){
            moveToNextQuestion();
        }else{
            if (numberOfhashButtonClicks == 4){
                timer.cancel();
                moveToNextQuestion();
            }else{
                numberOfhashButtonClicks++;
            }
        }
        if (!PreferencesActivity.isHintsOnOrOff()) {
            timer.cancel();
        }
        validateAnswer();
    }

    private void validateAnswer() {
        String textOnEditText = String.valueOf(editText.getText());
        String[] partsOfSplittedAnswer = textOnEditText.split("=");
        TextView result = null;
        try{
            String answerGiven = partsOfSplittedAnswer[1];
            answerGiven = answerGiven.trim();
            int answer = Integer.parseInt(answerGiven);
            result = ((TextView) findViewById(R.id.textView6));
            if (answer == total) {
                //correct
                result.setTextColor(Color.GREEN);
                result.setText(R.string.correct_answer);
                timer.cancel();
                numberOfhashButtonClicks = 4;
                calculateScore();
            } else {
                //wrong
                result.setTextColor(Color.RED);
                result.setText(R.string.wrong_answer);
                if (PreferencesActivity.isHintsOnOrOff()) {
                    if (answer < total) {
                        TextView textViewHints = ((TextView) findViewById(R.id.textView3));
                        textViewHints.setText(R.string.greater);
                    } else {
                        TextView textViewHints = ((TextView) findViewById(R.id.textView3));
                        textViewHints.setText(R.string.lesser);
                    }
                }
            }
        }catch (Exception e){
            result = ((TextView) findViewById(R.id.textView6));
            result.setTextColor(Color.BLUE);
            result.setText(R.string.unanswered);
        }
    }

    private void calculateScore() {
        int score;
        if (timerRemaining == 10){
            score = 100;
            LevelActivity.player.setScore(LevelActivity.player.getScore()+score);
        }else{
            score = (100/(10-timerRemaining));
            LevelActivity.player.setScore(LevelActivity.player.getScore()+score);
    }}


    public void onButtonMinusClicked(View view) {
        placeDigitOnEditText("-");
    }

}
