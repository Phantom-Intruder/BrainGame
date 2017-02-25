package coursework.com.braingame;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BraingameDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "braingame";
    private static final int DB_VERSION = 1;


    BraingameDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try{
            sqLiteDatabase.execSQL("DROP TABLE PLAYER");
        }catch (SQLiteException e){

        }
        sqLiteDatabase.execSQL("CREATE TABLE PLAYER ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "PLAYER_LEVEL TEXT, "
                + "QUESTION_NUMBER INTEGER, "
                + "SCORE INTEGER, "
                + "HINTS INTEGER);");
        insert(sqLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PLAYER ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "PLAYER_LEVEL TEXT, "
                + "QUESTION_NUMBER INTEGER, "
                + "SCORE INTEGER, "
                + "HINTS INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    private void insert(SQLiteDatabase sqLiteDatabase){
        int hints=0;
        if (LevelActivity.player.isHintsOnOrOff()){
            hints = 1;
        }
        insertPlayer(sqLiteDatabase, LevelActivity.player.getPlayerLevel(), LevelActivity.player.getQuestionNumber(), LevelActivity.player.getScore(), hints);
    }

    private static void insertPlayer(SQLiteDatabase db, String playerLevel, int questionNumber, int score, int hints){
        ContentValues playerValues = new ContentValues();
        playerValues.put("PLAYER_LEVEL", playerLevel);
        playerValues.put("QUESTION_NUMBER", questionNumber);
        playerValues.put("SCORE", score);
        playerValues.put("HINTS", hints);

    }
}
