package com.example.android.hackathonbloodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by omar on 15-02-2017.
 */
public class SpinnerActivity extends Activity {

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_giver_or_receiver);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                radioSexButton = (RadioButton) findViewById(selectedId);

                String whichSelected = radioSexButton.getText().toString();

                Intent intent = new Intent(SpinnerActivity.this,RegisteringWithoutLoginActivity.class);
                intent.putExtra("whichselected",whichSelected);
                startActivity(intent);
            }
        });


    }
}
