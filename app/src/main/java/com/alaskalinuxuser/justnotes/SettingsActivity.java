package com.alaskalinuxuser.justnotes;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.alaskalinuxuser.justnotes.MainActivity.colorChoice;
import static com.alaskalinuxuser.justnotes.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justnotes.MainActivity.textColorChoice;
import static com.alaskalinuxuser.justnotes.MainActivity.titleChoice;

public class SettingsActivity extends AppCompatActivity {


    RadioGroup radioTextColor, radioTitleColor, radioFabColor, radioTitleLength;
    RadioButton fblue,fred,fgreen,fgray,fblack,fyellow,fmag,fcyan;
    RadioButton tblue,tred,tgreen,tgray,tblack,tyellow,tmag,tcyan;
    RadioButton xblue,xred,xgreen,xgray,xblack,xyellow,xmag,xcyan;
    RadioButton tshort,tmedium,tlong,tall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        radioFabColor = (RadioGroup)findViewById(R.id.radioFabColor);
        radioTextColor = (RadioGroup)findViewById(R.id.radioTextColor);
        radioTitleColor = (RadioGroup)findViewById(R.id.radioTitleColor);
        radioTitleLength = (RadioGroup)findViewById(R.id.radioTitleLength);
        fblue = (RadioButton)findViewById(R.id.fblue);
        fblack = (RadioButton)findViewById(R.id.fblack);
        fred = (RadioButton)findViewById(R.id.fred);
        fgreen = (RadioButton)findViewById(R.id.fgreen);
        fgray = (RadioButton)findViewById(R.id.fgray);
        fyellow = (RadioButton)findViewById(R.id.fyellow);
        fmag = (RadioButton)findViewById(R.id.fmag);
        fcyan = (RadioButton)findViewById(R.id.fcyan);
        xblue = (RadioButton)findViewById(R.id.xblue);
        xblack = (RadioButton)findViewById(R.id.xblack);
        xred = (RadioButton)findViewById(R.id.xred);
        xgreen = (RadioButton)findViewById(R.id.xgreen);
        xgray = (RadioButton)findViewById(R.id.xgray);
        xyellow = (RadioButton)findViewById(R.id.xyellow);
        xmag = (RadioButton)findViewById(R.id.xmag);
        xcyan = (RadioButton)findViewById(R.id.xcyan);
        tblue = (RadioButton)findViewById(R.id.tblue);
        tblack = (RadioButton)findViewById(R.id.tblack);
        tred = (RadioButton)findViewById(R.id.tred);
        tgreen = (RadioButton)findViewById(R.id.tgreen);
        tgray = (RadioButton)findViewById(R.id.tgray);
        tyellow = (RadioButton)findViewById(R.id.tyellow);
        tmag = (RadioButton)findViewById(R.id.tmag);
        tcyan = (RadioButton)findViewById(R.id.tcyan);
        tshort = (RadioButton)findViewById(R.id.tshort);
        tmedium = (RadioButton)findViewById(R.id.tmedium);
        tlong = (RadioButton)findViewById(R.id.tlong);
        tall = (RadioButton)findViewById(R.id.tall);


        switch (colorChoice) {

            case 0:
                toolbar.setBackgroundColor(Color.BLUE);
                break;

            case 1:
                toolbar.setBackgroundColor(Color.RED);
                break;

            case 2:
                toolbar.setBackgroundColor(Color.GREEN);
                break;

            case 3:
                toolbar.setBackgroundColor(Color.GRAY);
                break;

            case 4:
                toolbar.setBackgroundColor(Color.BLACK);
                break;

            case 5:
                toolbar.setBackgroundColor(Color.YELLOW);
                break;

            case 6:
                toolbar.setBackgroundColor(Color.MAGENTA);
                break;

            case 7:
                toolbar.setBackgroundColor(Color.CYAN);
                break;

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        switch (fabColorChoice) {

            case 0:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                break;

            case 1:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                break;

            case 6:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                break;

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent returnIntent = getIntent();
                returnIntent.putExtra("colorChoice", String.valueOf(colorChoice));
                returnIntent.putExtra("textColorChoice", String.valueOf(textColorChoice));
                returnIntent.putExtra("fabColorChoice", String.valueOf(fabColorChoice));
                returnIntent.putExtra("titleChoice", String.valueOf(titleChoice));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        });


        radioTitleLength.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.tall) {

                    titleChoice = 200000;

                } else if(checkedId == R.id.tlong) {

                    titleChoice = 250;

                } else if(checkedId == R.id.tmedium) {

                    titleChoice = 100;

                } else {

                    titleChoice = 25;

                }


                Log.i("WJH", String.valueOf(titleChoice));

            }
        });


        radioTitleColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.tblue) {

                    colorChoice = 0;

                } else if(checkedId == R.id.tred) {

                    colorChoice = 1;

                } else if(checkedId == R.id.tgreen) {

                    colorChoice = 2;

                } else if(checkedId == R.id.tgray) {

                    colorChoice = 3;

                } else if(checkedId == R.id.tblack) {

                    colorChoice = 4;

                } else if(checkedId == R.id.tyellow) {

                    colorChoice = 5;

                } else if(checkedId == R.id.tmag) {

                    colorChoice = 6;

                } else {

                    colorChoice = 7;

                }


                Log.i("WJH", String.valueOf(colorChoice));

            }
        });


        radioFabColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.fblue) {

                    fabColorChoice = 0;

                } else if(checkedId == R.id.fred) {

                    fabColorChoice = 1;

                } else if(checkedId == R.id.fgreen) {

                    fabColorChoice = 2;

                } else if(checkedId == R.id.fgray) {

                    fabColorChoice = 3;

                } else if(checkedId == R.id.fblack) {

                    fabColorChoice = 4;

                } else if(checkedId == R.id.fyellow) {

                    fabColorChoice = 5;

                } else if(checkedId == R.id.fmag) {

                    fabColorChoice = 6;

                } else {

                    fabColorChoice = 7;

                }


                Log.i("WJH", String.valueOf(fabColorChoice));
            }
        });


        radioTextColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.xblue) {

                    textColorChoice = 3;

                } else if(checkedId == R.id.xred) {

                    textColorChoice = 1;

                } else if(checkedId == R.id.xgreen) {

                    textColorChoice = 2;

                } else if(checkedId == R.id.xgray) {

                    textColorChoice = 0;

                } else if(checkedId == R.id.xblack) {

                    textColorChoice = 4;

                } else if(checkedId == R.id.xyellow) {

                    textColorChoice = 5;

                } else if(checkedId == R.id.xmag) {

                    textColorChoice = 6;

                } else {

                    textColorChoice = 7;

                }


                Log.i("WJH", String.valueOf(textColorChoice));

            }
        });


    }

}
