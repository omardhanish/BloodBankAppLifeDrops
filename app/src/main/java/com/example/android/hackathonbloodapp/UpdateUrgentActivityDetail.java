package com.example.android.hackathonbloodapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by omar on 15-02-2017.
 */
public class UpdateUrgentActivityDetail extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_PET_LOADER = 0;//???

    private Uri mCurrentPetUri;

    private EditText bloodgroup , personname , urgentorveryurgent , place , phonenumber , hospitalname;

    Button savesubmit , deletesubmit;


    private boolean mBloodHasChanged = false;



    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mBloodHasChanged = true;
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_urgent_activity_detail_view);


        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();


         if(mCurrentPetUri!=null) {

            getLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
        }


        savesubmit = (Button) findViewById(R.id.updatebuttonSubmit);
        deletesubmit =(Button) findViewById(R.id.updatedeletebuttonSubmit);


        bloodgroup = (EditText) findViewById(R.id.updatewhichbloodneededurgent);
        personname = (EditText) findViewById(R.id.updateYourName);
        urgentorveryurgent = (EditText) findViewById(R.id.updateUrgentOrVeryUrgent);
        place = (EditText) findViewById(R.id.updateYourPlace);
        phonenumber = (EditText) findViewById(R.id.updatePhoneNumber);
        hospitalname = (EditText) findViewById(R.id.updateHospitalName);


        savesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String bloodgroup1 = bloodgroup.getText().toString().trim();
                String personname1 = personname.getText().toString().trim();
                String urgentorveryurgent1 = urgentorveryurgent.getText().toString().trim();
                String place1 = place.getText().toString().trim();
                String phonenumber1 = phonenumber.getText().toString().trim();
                String hospitalname1 = hospitalname.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DatabaseConnectorHelper.whichBloodNeeded, bloodgroup1);
                values.put(DatabaseConnectorHelper.firstName, personname1);
                values.put(DatabaseConnectorHelper.urgentOrVeryUrgent, urgentorveryurgent1);
                values.put(DatabaseConnectorHelper.YourPlace, place1);
                values.put(DatabaseConnectorHelper.phone, phonenumber1);
                values.put(DatabaseConnectorHelper.hospitalName, hospitalname1);

                int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);

                if (rowsAffected == 0) {
                    // If no rows were affected, then there was an error with the update.
                    Toast.makeText(getApplicationContext(), "Error with updation!!! sorry", Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(getApplicationContext(), "Succesfully Updated !!!!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        deletesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });


        bloodgroup.setOnTouchListener(mTouchListener);
        personname.setOnTouchListener(mTouchListener);
        urgentorveryurgent.setOnTouchListener(mTouchListener);
        place.setOnTouchListener(mTouchListener);
        phonenumber.setOnTouchListener(mTouchListener);
        hospitalname.setOnTouchListener(mTouchListener);



    }



    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                DatabaseConnectorHelper.id,
                DatabaseConnectorHelper.firstName,
                DatabaseConnectorHelper.YourPlace,
                DatabaseConnectorHelper.phone,
                DatabaseConnectorHelper.whichBloodNeeded,
                DatabaseConnectorHelper.hospitalName,
                DatabaseConnectorHelper.urgentOrVeryUrgent};


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


            String bloodgroup1 = cursor.getString(bloodgroupColumnIndex);
            String personname1 = cursor.getString(personnameColumnIndex);
            String urgentorveryurgent1 = cursor.getString(urgentorveryurgentColumnIndex);
            String place1 = cursor.getString(placeColumnIndex);
            String phonenumber1 = cursor.getString(phonenumberColumnIndex);
            String hospitalname1 = cursor.getString(hospitalnameColumnIndex);

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

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure You want to delete ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deletePet();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void deletePet() {

        if (mCurrentPetUri != null) {

            int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);


            if (rowsDeleted == 0) {

                Toast.makeText(getApplicationContext(), "Delete unsuccesfull",
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getApplicationContext(), "delete process successfull",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}
