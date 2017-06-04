/*  Copyright 2017 by AlaskaLinuxUser (https://thealaskalinuxuser.wordpress.com)
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package com.alaskalinuxuser.justnotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    ListView theList;
    ArrayList<String> listNote, titleNote;
    ArrayAdapter<String> addaptedAray;
    Boolean askDelete, newNote;
    int toDelete;
    Set<String> stringSet;
    static SharedPreferences myPrefs;
    String exportNotes, tempNotes, currentDateandTime, alistNote, subString, tempString;
    static int textColorChoice, colorChoice, titleChoice, fabColorChoice;
    FloatingActionButton fab;
    Layout mainLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myPrefs = this.getSharedPreferences("com.alaskalinuxuser.justnotes", Context.MODE_PRIVATE);

        try {


            colorChoice = Integer.parseInt(myPrefs.getString("colorPref", null));
            textColorChoice = Integer.parseInt(myPrefs.getString("textColorPref", null));
            fabColorChoice = Integer.parseInt(myPrefs.getString("fabColorPref", null));
            titleChoice = Integer.parseInt(myPrefs.getString("titlePref", null));

            Log.i("WJH", String.valueOf(colorChoice));
            Log.i("WJH", String.valueOf(textColorChoice));
            Log.i("WJH", String.valueOf(fabColorChoice));
            Log.i("WJH", String.valueOf(titleChoice));

        } catch (Exception a) {


            Log.i("WJH", "No pref." + a);

        }


        theList = (ListView) findViewById(R.id.theList);


        listNote = new ArrayList();
        titleNote = new ArrayList<>();


        addaptedAray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleNote) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                switch (textColorChoice) {

                    case 0:
                       textView.setTextColor(Color.GRAY);
                        break;

                    case 1:
                        textView.setTextColor(Color.RED);
                        break;

                    case 2:
                        textView.setTextColor(Color.GREEN);
                        break;

                    case 3:
                        textView.setTextColor(Color.BLUE);
                        break;

                    case 4:
                        textView.setTextColor(Color.BLACK);
                        break;

                    case 5:
                        textView.setTextColor(Color.YELLOW);
                        break;

                    case 6:
                        textView.setTextColor(Color.MAGENTA);
                        break;

                }

                return view;

            }
        };



        theList.setAdapter(addaptedAray);


        askDelete = false;


        stringSet = new HashSet<String>();
        stringSet.clear();


        try {


            stringSet.addAll(myPrefs.getStringSet("ssnotes", null));


        } catch (Exception e) {


            Log.i("WJH", "Malformed saved notes.");

        }


        if (stringSet.size() > 0) {


            listNote.addAll(stringSet);


            setTitleNote();

        } else {


            Log.i("WJH", "No saved notes.");

        }


        fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                String c = "";

                newNote = true;


                Intent myIntent = new Intent(getApplicationContext(), writenote.class);

                myIntent.putExtra("listNote", c);

                startActivityForResult(myIntent, 1);

            }
        });



        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Overriding the generic code that Android uses for list views to do something specific.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int z, long l) {


                if (askDelete) {


                    Log.i("WJH", "Can't click, because we are deleting something.");


                } else {


                    String c = (String) listNote.get(z);

                    toDelete = z;

                    newNote = false;


                    Intent myIntent = new Intent(getApplicationContext(), writenote.class);

                    myIntent.putExtra("listNote", c);

                    startActivityForResult(myIntent, 1);

                }

            }
        });


        theList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {


                askDelete = true;


                toDelete = arg2;


                dialogBuild();

                return false;

            }
        });


        selectColors();

    }


        public void dialogBuild () {


            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure?")
                    .setMessage("Do you want to delete this message?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            listNote.remove(toDelete);


                            setTitleNote();


                            saveMessages();


                            askDelete = false;

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            askDelete = false;

                        }
                    })
                    .show();

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){


                alistNote = data.getStringExtra("alistNote");

                makeNote();

            }


            if (resultCode == Activity.RESULT_CANCELED) {


                Log.i("WJH", "There was no  one result.");

            }

        }  else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {


                colorChoice = Integer.parseInt(data.getStringExtra("colorChoice"));
                textColorChoice = Integer.parseInt(data.getStringExtra("textColorChoice"));
                fabColorChoice = Integer.parseInt(data.getStringExtra("fabColorChoice"));
                titleChoice = Integer.parseInt(data.getStringExtra("titleChoice"));

                Log.i("WJH", String.valueOf(colorChoice));
                Log.i("WJH", String.valueOf(textColorChoice));
                Log.i("WJH", String.valueOf(fabColorChoice));
                Log.i("WJH", String.valueOf(titleChoice));


                myPrefs.edit().putString("colorPref", String.valueOf(colorChoice)).apply();
                myPrefs.edit().putString("textColorPref", String.valueOf(textColorChoice)).apply();
                myPrefs.edit().putString("fabColorPref", String.valueOf(fabColorChoice)).apply();
                myPrefs.edit().putString("titlePref", String.valueOf(titleChoice)).apply();

                selectColors();

            }


            if (resultCode == Activity.RESULT_CANCELED) {


                Log.i("WJH", "There was no two result.");

            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void makeNote () {


        if (alistNote != null) {


            if (newNote == false) {

                listNote.remove(toDelete);

            }


            listNote.add(alistNote);


            setTitleNote();

            saveMessages();

        } else {


            Log.i("WJH", "Note was null.");

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {


            Intent settingIntent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivityForResult(settingIntent, 2);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public  void writeToFile() {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        currentDateandTime = sdf.format(new Date());


        tempNotes = currentDateandTime + " justnotestxt ";


        exportNotes = tempNotes;


        try {


            for (int k = 0; k <= listNote.size(); k++) {


                exportNotes = tempNotes + listNote.get(k) + " justnotestxt ";


                tempNotes = exportNotes;

            }

        } catch (Exception e) {

            Log.i("WJH", "No notes to export.");

        }


        FileOutputStream fos = null;


        try {

            File appDir = new File(Environment.getExternalStorageDirectory()+File.separator+"justnotes");

            if(!appDir.exists() && !appDir.isDirectory())
            {

                if (appDir.mkdirs())
                {

                    Log.i("WJH","App dir created");

                }
                else
                {

                    Toast.makeText(getApplicationContext(), "You do not have permission to create a directory.",
                            Toast.LENGTH_SHORT).show();

                }
            } else {

                Log.i("WJH","App dir already exists");

            }


            final File myFile = new File(appDir, currentDateandTime + ".txt");


            if (!myFile.exists())
            {

                myFile.createNewFile();
            }


            fos = new FileOutputStream(myFile);


            fos.write(exportNotes.getBytes());


            fos.close();


            Toast.makeText(getApplicationContext(), "Exported file!", Toast.LENGTH_SHORT).show();


        } catch (Exception eX) {

            eX.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: Please check permissions....",
                    Toast.LENGTH_SHORT).show();

        }
    }


    public void saveMessages () {


        stringSet.clear();

        myPrefs.edit ().putStringSet("ssnotes", stringSet).apply();


        stringSet.addAll(listNote);


        myPrefs.edit ().putStringSet("ssnotes", stringSet).apply();


    }

    public void setTitleNote () {


        titleNote.clear();


        for (int k = 0; k <= stringSet.size(); k++) {

            try {

                tempString = listNote.get(k);

                if (titleChoice == 0) {


                    titleChoice = 250;

                }


                if (tempString.length() < titleChoice) {


                    subString = tempString;

                } else {


                    subString = tempString.substring(0, titleChoice);

                }


                titleNote.add(subString);

            } catch (Exception e) {

                Log.i("WJH", "Exception " + e);
            }

        }


        addaptedAray.notifyDataSetChanged();

    }


    public void selectColors () {


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


        setTitleNote();

    }
}
