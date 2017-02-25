package com.example.android.hackathonbloodapp;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by omar on 14-02-2017.
 */

public class UrgentBloodDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Cursor cursor;

    private static final int EXISTING_BLOOD_DATA_LOADER = 0;
    RelativeLayout layoutback;

    private Uri mCurrentPetUri;

    private TextView bloodgroup , personname , urgentorveryurgent , place , phonenumber , hospitalname;


    Button call,chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urgentblood_list_view_detail_activity);

        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();

        if(mCurrentPetUri != null){

            getLoaderManager().initLoader(EXISTING_BLOOD_DATA_LOADER, null, this);

        }



        call = (Button)findViewById(R.id.buttoncall);
        chat = (Button)findViewById(R.id.buttonchat);

        bloodgroup = (TextView)findViewById(R.id.bloodgroup1);
        personname = (TextView)findViewById(R.id.personname1);
        urgentorveryurgent = (TextView)findViewById(R.id.urgentorveryurgent1);
        place = (TextView)findViewById(R.id.place1);
        phonenumber = (TextView)findViewById(R.id.phonenumber1);
        hospitalname = (TextView)findViewById(R.id.hospitalname1);






    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                DatabaseConnectorHelper.id,
                DatabaseConnectorHelper.whichBloodNeeded,
                DatabaseConnectorHelper.firstName,
                DatabaseConnectorHelper.urgentOrVeryUrgent,
                DatabaseConnectorHelper.YourPlace,
                DatabaseConnectorHelper.phone,
                DatabaseConnectorHelper.hospitalName,
                DatabaseConnectorHelper.receiverorgiver};


        return new CursorLoader(this,
                mCurrentPetUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if(cursor.moveToFirst()){

            int bloodgroupColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.whichBloodNeeded);
            int personnameColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.firstName);
            int urgentorveryurgentColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.urgentOrVeryUrgent);
            int placeColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.YourPlace);
            int phonenumberColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.phone);
            int hospitalnameColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.hospitalName);
            int gettersetterindex = cursor.getColumnIndex(DatabaseConnectorHelper.receiverorgiver);


            String bloodgroup1 = cursor.getString(bloodgroupColumnIndex);
            String personname1 = cursor.getString(personnameColumnIndex);
            String urgentorveryurgent1 = cursor.getString(urgentorveryurgentColumnIndex);
            String place1 = cursor.getString(placeColumnIndex);
            final String phonenumber1 = cursor.getString(phonenumberColumnIndex);
            String hospitalname1 = cursor.getString(hospitalnameColumnIndex);
            String getter1111 = cursor.getString(gettersetterindex);

            layoutback = (RelativeLayout) findViewById(R.id.relativelayoutback);


            if(getter1111.equals("Receiver")){
                layoutback.setBackgroundColor(getResources().getColor(R.color.darkred));
                call.setBackgroundColor(getResources().getColor(R.color.darkred));
                call.setTextColor(getResources().getColor(R.color.white));
                chat.setBackgroundColor(getResources().getColor(R.color.darkred));
                chat.setTextColor(getResources().getColor(R.color.white));
            }else if(getter1111.equals("Giver")){
                layoutback.setBackgroundColor(getResources().getColor(R.color.darkgreen));
                call.setBackgroundColor(getResources().getColor(R.color.green500));
                call.setTextColor(getResources().getColor(R.color.black));
            }


            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.fromParts("tel", phonenumber1, null));

                    if (ActivityCompat.checkSelfPermission(UrgentBloodDetailActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);
                }

            });

            chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);

                    smsIntent.setType("vnd.android-dir/mms-sms");

                    smsIntent.putExtra("address", phonenumber1);

                    smsIntent.putExtra("sms_body", "I am in need of your blood");


                    startActivity(smsIntent);
                }

            });




            bloodgroup.setText(bloodgroup1);
            personname.setText(personname1);
            urgentorveryurgent.setText(urgentorveryurgent1);
            place.setText(place1);
            phonenumber.setText(phonenumber1);
            hospitalname.setText(hospitalname1);

        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        bloodgroup.setText("");
        personname.setText("");
        urgentorveryurgent.setText("");
        place.setText("");
        phonenumber.setText("");
        hospitalname.setText("");

    }
}
