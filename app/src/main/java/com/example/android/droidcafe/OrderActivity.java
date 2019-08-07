/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity shows the order chosen.  The order is sent as data
 * with the intent to launch this activity.
 */
public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Get the intent and its data.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);
        RadioButton sameDay = (RadioButton)findViewById(R.id.sameDay);
        sameDay.setChecked(true);
        Spinner spinner = (Spinner)findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.label_array,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null){
            spinner.setAdapter(arrayAdapter);
        }
    }
    public void displayToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    public void deliveryOptionClicked(View view){
        boolean isChecked = ((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.sameDay:
                if(isChecked){
                    displayToast(getString(R.string.display_sameDay_message));
                }
                break;
            case R.id.nextDay:
                if(isChecked){
                    displayToast(getString(R.string.display_nextDay_message));
                }
                break;
            case R.id.pickUp:
                if(isChecked){
                    displayToast(getString(R.string.display_pickup_message));
                }
                break;
            default:
                break;
        }

    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       String spinnerLabel = adapterView.getItemAtPosition(i).toString();
       displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        
    }
}
