package coursework.com.braingame;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Main screen buttons
    public void newGameButtonClicked(View view) {
        Fragment newGameFragment = new LevelFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, newGameFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void continueButtonClicked(View view) {
    }

    public void aboutButtonClicked(View view) {
    }

    public void exitButtonClicked(View view) {
    }


    //Level screen buttons
    public void noviceButtonClicked(View view) {
    }

    public void easyButtonClicked(View view) {
    }

    public void mediumButtonClicked(View view) {
    }

    public void guruButtonClicked(View view) {
    }

    //Number pad buttons
    public void onButtonZeroClicked(View view) {
    }

    public void onButtonOneClicked(View view) {
    }

    public void onButtonTwoClicked(View view) {
    }

    public void onButtonThreeClicked(View view) {
    }

    public void onButtonFourClicked(View view) {
    }

    public void onButtonFiveClicked(View view) {
    }

    public void onButtonSixClicked(View view) {
    }

    public void onButtonSevenClicked(View view) {
    }

    public void onButtonEightClicked(View view) {
    }

    public void onButtonNineClicked(View view) {
    }

    public void onButtonDelClicked(View view) {
    }

    public void onButtonHashClicked(View view) {
    }





    public void onButtonMinusClicked(View view) {
    }


}
