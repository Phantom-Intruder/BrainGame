package coursework.com.braingame;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
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
        //TODO: Start saved game
            readFromDatabase();

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
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

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                saveToDatabase();
                finish();
            }
        }).create();

        builder.setPositiveButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create();


        builder.show();
    }

    // Constant with a file name

    // Serializes an object and saves it to a file
    public void saveToDatabase() {
        BraingameDatabaseHelper braingameDatabaseHelper = new BraingameDatabaseHelper(this);
    }


    // Creates an object by reading it from a file
    public  void  readFromDatabase() {
        try{
            SQLiteOpenHelper braingameDatabaseHelper = new BraingameDatabaseHelper(this);
            SQLiteDatabase db = braingameDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("PLAYER",
                    new String[] {"PLAYER_LEVEL", "QUESTION_NUMBER", "SCORE", "HINTS"}, null, null, null, null, null);
            if (cursor.moveToFirst()){
                LevelActivity.player.setPlayerLevel(cursor.getString(0));
                LevelActivity.player.setQuestionNumber(cursor.getInt(1));
                LevelActivity.player.setScore(cursor.getInt(2));
                int value = cursor.getInt(3);
                if (value == 0){
                    LevelActivity.player.setHintsOnOrOff(false);
                }else{
                    LevelActivity.player.setHintsOnOrOff(true);
                }
            }
        }catch (SQLiteException e){

        }

    }






}
