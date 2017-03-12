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

import java.util.Random;



public class GameActivity extends AppCompatActivity {
    private EditText answerEntryEditText;
    private BaseInputConnection deleteKeyHandler;
    private int correctAnswer;
    private static CountDownTimer countDownTimer;
    private ProgressBar progressBar;
    private int levelOfProgressbarFilled;
    private TextView correctOrWrongAnswerDisplay;
    private int numberOfHashButtonClicks = 0;
    private int timerRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Runs every time a new question is presented to the user in the game
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play game");
        answerEntryEditText = (EditText)findViewById(R.id.text_entry_area);
        answerEntryEditText.setInputType(InputType.TYPE_NULL);
        //Keep track of which question the player is in
        PlayerManagementClass.player.setQuestionNumber(PlayerManagementClass.player.getQuestionNumber()+1);
        deleteKeyHandler = new BaseInputConnection(answerEntryEditText, true);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            answerEntryEditText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            answerEntryEditText.setTextIsSelectable(true);
        }
        String levelOfPlayer = PlayerManagementClass.player.getPlayerLevel();
        switch (levelOfPlayer){
            case "novice":
                novice();
                break;
            case "easy":
                easy();
                break;
            case "medium":
                medium();
                break;
            case "guru":
                guru();
                break;
        }
        startCountdownTimer();
    }

    private void startCountdownTimer() {
        levelOfProgressbarFilled = 0;
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        correctOrWrongAnswerDisplay = ((TextView)findViewById(R.id.result));
        countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                //Fill progress bar with every second
                levelOfProgressbarFilled += 10;
                progressBar.setProgress(levelOfProgressbarFilled);
                timerRemaining = (10 - levelOfProgressbarFilled / 10);
                correctOrWrongAnswerDisplay.setText(String.format("%d", timerRemaining));
            }
            //Once the timer is finished
            public void onFinish() {
                moveToNextQuestion();
            }
        }.start();
    }

    private void moveToNextQuestion() {
        //Move to next question unless question is the 10th question.
        // In that case show the score.
        if (PlayerManagementClass.player.getQuestionNumber() == 10){
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
        int[] dataToBeDisplayed = new int[numberOfLoops];
        for (int i = 0; i < numberOfLoops; i++) {
            dataToBeDisplayed[i] = generateRandomNumbers();
        }
        showDataOnScreen(dataToBeDisplayed);
    }

    private void showDataOnScreen(int[] dataToBeDisplayed) {
        TextView displayPanel = (TextView) findViewById(R.id.text_entry_area);
        String stringToBeShown = "";
        correctAnswer = 0;
        Random ran = new Random();
        int firstCount = 0;
        for (int i : dataToBeDisplayed){
            String currentChar = "" + i;
            if (firstCount == 0){
                correctAnswer = i;
                stringToBeShown = stringToBeShown.concat(currentChar);
                firstCount++;
            }else {
                currentChar = "" + i;
                int symbol = ran.nextInt(4) + 1;
                switch (symbol) {
                    case 1:
                        correctAnswer += i;
                        stringToBeShown = stringToBeShown.concat("+");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                    case 2:
                        correctAnswer -= i;
                        stringToBeShown = stringToBeShown.concat("-");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                    case 3:
                        correctAnswer *= i;
                        stringToBeShown = stringToBeShown.concat(" x ");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                    case 4:
                        correctAnswer /= i;
                        stringToBeShown = stringToBeShown.concat("/");
                        stringToBeShown = stringToBeShown.concat(currentChar);
                        break;
                }
                Log.d(TAG, "TotalFor: " + correctAnswer);
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
                countDownTimer.cancel();
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
        String textOnEditText = String.valueOf(answerEntryEditText.getText());
        if ((textOnEditText.charAt(textOnEditText.length()-1))=='?') {
            answerEntryEditText.setSelection(answerEntryEditText.getText().length());
            textOnEditText = textOnEditText.replace('?', ' ');
            answerEntryEditText.setText(textOnEditText);
        }
            answerEntryEditText = (EditText) findViewById(R.id.text_entry_area);
            answerEntryEditText.append(text);

    }

    public void onButtonDelClicked(View view) {
        answerEntryEditText.setSelection(answerEntryEditText.getText().length());
        String textOnEditText = String.valueOf(answerEntryEditText.getText());
        if ((textOnEditText.charAt(textOnEditText.length()-1))!='=') {
            deleteKeyHandler.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        }
    }

    public void onButtonHashClicked(View view) {
        if ((numberOfHashButtonClicks >= 1)&&(!PreferencesActivity.isHintsOnOrOff()) ){
            moveToNextQuestion();
        }else{
            if (numberOfHashButtonClicks == 4){
                countDownTimer.cancel();
                moveToNextQuestion();
            }else{
                numberOfHashButtonClicks++;
            }
        }
        if (!PreferencesActivity.isHintsOnOrOff()) {
            countDownTimer.cancel();
        }
        validateAnswer();
    }

    private void validateAnswer() {
        String textOnEditText = String.valueOf(answerEntryEditText.getText());
        String[] partsOfSplittedAnswer = textOnEditText.split("=");
        TextView result;
        try{
            String answerGiven = partsOfSplittedAnswer[1];
            answerGiven = answerGiven.trim();
            int answer = Integer.parseInt(answerGiven);
            result = ((TextView) findViewById(R.id.textView6));
            if (answer == correctAnswer) {
                //correct
                result.setTextColor(Color.GREEN);
                result.setText(R.string.correct_answer);
                countDownTimer.cancel();
                numberOfHashButtonClicks = 4;
                calculateScore();
            } else {
                //wrong
                result.setTextColor(Color.RED);
                result.setText(R.string.wrong_answer);
                if (PreferencesActivity.isHintsOnOrOff()) {
                    if (answer < correctAnswer) {
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
            PlayerManagementClass.player.setScore(PlayerManagementClass.player.getScore()+score);
        }else{
            score = (100/(10-timerRemaining));
            PlayerManagementClass.player.setScore(PlayerManagementClass.player.getScore()+score);
    }}


    public void onButtonMinusClicked(View view) {
        placeDigitOnEditText("-");
    }

}
