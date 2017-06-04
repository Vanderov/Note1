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
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import static com.alaskalinuxuser.justnotes.MainActivity.colorChoice;
import static com.alaskalinuxuser.justnotes.MainActivity.textColorChoice;

public class writenote extends AppCompatActivity {

    String listNote;
    EditText multiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writenote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        multiView = (EditText)findViewById(R.id.multiTextView);

        switch (textColorChoice) {

            case 0:
                multiView.setTextColor(Color.GRAY);
                break;

            case 1:
                multiView.setTextColor(Color.RED);
                break;

            case 2:
                multiView.setTextColor(Color.GREEN);
                break;

            case 3:
                multiView.setTextColor(Color.BLUE);
                break;

            case 4:
                multiView.setTextColor(Color.BLACK);
                break;

            case 5:
                multiView.setTextColor(Color.YELLOW);
                break;

            case 6:
                multiView.setTextColor(Color.MAGENTA);
                break;

            case 7:
                multiView.setTextColor(Color.CYAN);
                break;

        }

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


        Intent i = getIntent();
        listNote = i.getStringExtra("listNote");

        if (listNote == "") {

            Log.i("WJH", "listNote was blank.");

        } else {

            multiView.setText(listNote);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_writenote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_save) {


            listNote = multiView.getText().toString();

            Intent returnIntent = getIntent();
            returnIntent.putExtra("alistNote",listNote);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
