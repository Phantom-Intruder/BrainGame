package coursework.com.braingame;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;
import static android.content.ContentValues.TAG;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    EditText editText;
    BaseInputConnection textFieldInputConnection;

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
    }


    private void novice() {
        generateDataToShowOnScreen(2,2);
        //validateAnswer(firstNumber, secondNumber);
    }

    private void validateAnswer(int firstNumber, int secondNumber) {
        int symbolIndex = generateSymbol();
        int answer =0;
        switch (symbolIndex){
            case 1:
                answer = firstNumber + secondNumber;
                break;
            case 2:
                answer = firstNumber - secondNumber;
                break;
            case 3:
                answer = firstNumber * secondNumber;
                break;
            case 4:
                answer = firstNumber / secondNumber;
                break;
        }
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
        int total = 0;
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
            }
        }

        displayPanel.setText(stringToBeShown + " = ?");
    }

    private int generateSymbol(){
        Random ran = new Random();
        int symbolIndex  = ran.nextInt();
        return symbolIndex;
    }

    private int generateRandomNumbers() {
        Random ran = new Random();
        int randomNumber = ran.nextInt(999)+1;
        return randomNumber;
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
                this.finish();
                return true;
            default:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                return true;
        }

    }

    public void gameLogic(){

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
        if ((textOnEditText.charAt(textOnEditText.length()-1))=='?'){
            Log.d(TAG, "data Of ting "+ textOnEditText);
        }
        editText = (EditText) findViewById(R.id.editText);
        editText.append(text);
    }

    public void onButtonDelClicked(View view) {
        textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
    }

    public void onButtonHashClicked(View view) {
        //TODO: end of answer
    }

    public void onButtonMinusClicked(View view) {
        //TODO: No idea what to do here
    }

}
