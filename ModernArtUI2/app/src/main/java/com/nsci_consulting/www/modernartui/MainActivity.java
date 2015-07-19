package com.nsci_consulting.www.modernartui;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.util.Log;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import org.w3c.dom.Text;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class MainActivity extends ActionBarActivity {

    // Identifier for each type of Dialog
    private static final int ALERTTAG = 0;
    private static final String TAG = "AlertDialogActivity";
    private DialogFragment mDialog;

    private SeekBar seekBar = null;
    private ImageView views[];
    float[] hsv = new float[3];
/*
    // I prefer somehow using the resources that I set for colors, but
    // am unable to use this string array to set color for now!
    private String[] colorArray = {"R.color.indigo_primary", "R.color.indigo_light",
    "R.color.indigo_dark", "R.color.blue_grey_light",
            "R.color.color5_lavendar", "R.color.color5_yellow", "R.color.color5_cyan",
             "R.color.color5_salmon", "R.color.color5_teal"};
*/
    private String[] colorArray = {"#B7B1D1", "#EB8D78", "#90C9AB",
            "#F6E870", "#276F97", "#303F9F", "#C5CAE9", "#3F51B5", "#FF7043"};

/*
    // These two variables are futures for using animation for color transitions
    private String newColor1 = "";
    private String newColor2 = "";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        views = new ImageView[5];
        views[0] = (ImageView) findViewById(R.id.rect01);
        views[1] = (ImageView) findViewById(R.id.rect02);
        views[2] = (ImageView) findViewById(R.id.rect03);
        views[3] = (ImageView) findViewById(R.id.rect04);
        views[4] = (ImageView) findViewById(R.id.rect05);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(1000); // implelmented as a 20% slider for hue
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Goal:  change background colors of non white ImageViews with progress of seekbar
                //        default range is between 0 & 100
                for (int i = 0; i<5 ;i++){

                    if(i != 3){
/*
                        // this color transition is unilateral & irreversible
                        ((GradientDrawable) views[i].getBackground()).setColor(
                                getResources().getColor(R.color.indigo_light));
*/
/*
                        // this color transition is strobo-like & irreversible
                        newColor1 = colorArray[(new Random().nextInt(colorArray.length))];
                        newColor2 = colorArray[(new Random().nextInt(colorArray.length))];
                        ((GradientDrawable) views[i].getBackground()).setColor(
                                Color.parseColor(newColor1));
*/
                        // this color transition is not that smooth, but is reversible
                        // based on setMax, hue varies by 2%
                        Color.colorToHSV(Color.parseColor(colorArray[i]), hsv);
                        hsv[0] = hsv[0] * (1 + progress*20/1000) % 360;
                        ((GradientDrawable) views[i].getBackground()).setColor(
                                Color.HSVToColor( hsv )
                        );
/*
                        // I would prefer smoothing color transition with animation, but
                        // the following code is not working--colors remain static
                        ObjectAnimator colorFade = ObjectAnimator.ofObject(views[i],
                                "((GradientDrawable)views[i].getBackground()).setColor()", new ArgbEvaluator(),
                                Color.parseColor(newColor1), Color.parseColor(newColor2));
                        colorFade.setDuration(1000);
                        colorFade.start();
*/
                    }

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not used for this project
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not used for this project
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Activate Dialog that either takes user to MOMA or back to origination
            showDialogFragment(ALERTTAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Show desired Dialog
    void showDialogFragment(int dialogID) {

        switch (dialogID) {

            // Show AlertDialog
            case ALERTTAG:

                // Create a new AlertDialogFragment
                mDialog = AlertDialogFragment.newInstance();

                // Show AlertDialogFragment
                mDialog.show(getFragmentManager(), "Alert");

                break;

            // Show Another Dialog if necessary

        }
    }

    // Class that creates the AlertDialog
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Inspired by the works of artists such as Piet Mondrian " +
                            "and Ben Nicholson \n \n Click below to learn more!")

                            // User cannot dismiss dialog by hitting back button
            .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    ((MainActivity) getActivity())
                                            .mDialog.dismiss();
                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    ((MainActivity) getActivity())
                                            .startBrowser();
                                }
                            }).create();
        }
    }

    // Start the ExplicitlyLoadedActivity

    private void startBrowser() {

        Log.i(TAG,"Entered startBrowser()");

        Intent browserIntent = new Intent(MainActivity.this, BrowserActivity.class);
        startActivity(browserIntent);
    }

}