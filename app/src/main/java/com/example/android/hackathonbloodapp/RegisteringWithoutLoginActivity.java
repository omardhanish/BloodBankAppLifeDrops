package com.example.android.hackathonbloodapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by omar on 14-02-2017.
 */
public class RegisteringWithoutLoginActivity extends Activity {

    TextView yourName2 , yourPlace2 , phoneNumber2 , BloodGroupNeededUrg2, hospitalName2 , typeUrgentOrVeruyUrgent2 ;
    EditText yourName , yourPlace , phoneNumber , BloodGroupNeededUrg , hospitalName , typeUrgentOrVeruyUrgent ;
    Button submit ;

    GetterSetter form = new GetterSetter();
    DatabaseConnectorHelper dbconnector = new DatabaseConnectorHelper(this);
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.registeringlayout);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex1);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        radioSexButton = (RadioButton) findViewById(selectedId);


        yourName2 = (TextView) findViewById(R.id.one);
        yourPlace2 = (TextView) findViewById(R.id.two);
        phoneNumber2 = (TextView) findViewById(R.id.three);
        BloodGroupNeededUrg2 = (TextView) findViewById(R.id.four);
        hospitalName2 = (TextView) findViewById(R.id.five);
        typeUrgentOrVeruyUrgent2 = (TextView) findViewById(R.id.six);


        yourName = (EditText) findViewById(R.id.editTextYourName);
        yourPlace = (EditText) findViewById(R.id.editTextYourPlace);
        phoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        BloodGroupNeededUrg = (EditText) findViewById(R.id.editTextwhichbloodneededurgent);
        hospitalName = (EditText) findViewById(R.id.editTextHospitalName);
        typeUrgentOrVeruyUrgent = (EditText) findViewById(R.id.editTextUrgentOrVeryUrgent);


        Intent intent = getIntent();

        String whichselected1 = intent.getStringExtra("whichselected");

        if(whichselected1.equals("Receiver")){
            getWindow().getDecorView().setBackground(getResources().getDrawable(R.drawable.spinnerbackground));
            yourName2.setTextColor(getResources().getColor(R.color.white));
            yourPlace2.setTextColor(getResources().getColor(R.color.white));
            phoneNumber2.setTextColor(getResources().getColor(R.color.white));
            BloodGroupNeededUrg2.setTextColor(getResources().getColor(R.color.white));
            hospitalName2.setTextColor(getResources().getColor(R.color.white));
            typeUrgentOrVeruyUrgent2.setTextColor(getResources().getColor(R.color.white));

            yourName.setTextColor(getResources().getColor(R.color.white));
            yourPlace.setTextColor(getResources().getColor(R.color.white));
            phoneNumber.setTextColor(getResources().getColor(R.color.white));
            BloodGroupNeededUrg.setTextColor(getResources().getColor(R.color.white));
            hospitalName.setTextColor(getResources().getColor(R.color.white));
            typeUrgentOrVeruyUrgent.setTextColor(getResources().getColor(R.color.white));
            radioSexButton.setText("Receiver");
            radioSexButton.setTextColor(getResources().getColor(R.color.white));

        }else if(whichselected1.equals("Giver")){
            getWindow().getDecorView().setBackground(getResources().getDrawable(R.drawable.greenbackground));
            radioSexButton.setText("Giver");
        }



        submit = (Button) findViewById(R.id.buttonSubmit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                form.setReceiverorgiver(radioSexButton.getText().toString().trim());
                form.setYourName(yourName.getEditableText().toString().trim());
                form.setYourPlace(yourPlace.getEditableText().toString().trim());
                form.setPhoneNumber(phoneNumber.getEditableText().toString().trim());
                form.setBloodGroupNeededUrg(BloodGroupNeededUrg.getEditableText().toString().trim());
                form.setHospitalName(hospitalName.getEditableText().toString().trim());
                form.setTypeUrgentOrVeruyUrgent(typeUrgentOrVeruyUrgent.getEditableText().toString().trim());


                dbconnector.addRecord(form.getYourName(), form.getYourPlace(), form.getPhoneNumber(), form.getBloodGroupNeededUrg(), form.getHospitalName(),form.getReceiverorgiver(), form.getTypeUrgentOrVeruyUrgent());
                Toast.makeText(getApplicationContext(), "Record successfully Added!", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(RegisteringWithoutLoginActivity.this , MainActivity.class);
                finish();
                startActivity(intent);

            }
        });




    }



}
