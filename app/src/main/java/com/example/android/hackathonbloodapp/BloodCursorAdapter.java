package com.example.android.hackathonbloodapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by omar on 14-02-2017.
 */
public class BloodCursorAdapter extends CursorAdapter {




    public BloodCursorAdapter(Context context, Cursor c) {
        super(context, c , 0/* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View rootViewwhat = LayoutInflater.from(context).inflate(R.layout.urgent_blood_itemview_layout, parent, false);

        return  rootViewwhat;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        CardView cardView = (CardView) view.findViewById(R.id.cardview);

//        TextView hidetextview = (TextView) view.findViewById(R.id.hidetextview);
        TextView nameTextView = (TextView) view.findViewById(R.id.bloodgroup_person_name);
        TextView summaryTextView = (TextView) view.findViewById(R.id.bloodgroup_name);
        TextView summaryTextView2 = (TextView) view.findViewById(R.id.phonenumber_for_ungent_need);
        TextView getorreceiver = (TextView) view.findViewById(R.id.getterorreciever56);


        int bloodgrouppersonnameColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.firstName);
        int bloodgroupneededColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.whichBloodNeeded);
        int phonenoColumnIndex = cursor.getColumnIndex(DatabaseConnectorHelper.phone);
        int getorreceiverindex = cursor.getColumnIndex(DatabaseConnectorHelper.receiverorgiver);


        String bloodpersonname1 = cursor.getString(bloodgrouppersonnameColumnIndex);
        String whichBloodneeded1 = cursor.getString(bloodgroupneededColumnIndex);
        String phonenumber1 = cursor.getString(phonenoColumnIndex);
        String gettertext = cursor.getString(getorreceiverindex);

//        GetterSetterfilter setter = new GetterSetterfilter();
//
//        String hidetext = setter.getWhatis();
//
//        String subject = "dope";

//        if(hidetext.equals("giversshow")){
//            cardView.setVisibility(view.GONE);
//        }
         if(gettertext.equals("Receiver")){
            cardView.setBackgroundColor(context.getResources().getColor(R.color.red100));
            nameTextView.setText(bloodpersonname1);
            summaryTextView.setText(whichBloodneeded1);
            summaryTextView.setBackgroundColor(context.getResources().getColor(R.color.darkred));
            summaryTextView.setTextColor(context.getResources().getColor(R.color.white));
            summaryTextView2.setText(phonenumber1);
            getorreceiver.setText(gettertext);
             getorreceiver.setBackgroundColor(context.getResources().getColor(R.color.darkred));
            getorreceiver.setTextColor(context.getResources().getColor(R.color.white));
        }

        else if(gettertext.equals("Giver")){
            cardView.setBackgroundColor(context.getResources().getColor(R.color.green100));
            nameTextView.setText(bloodpersonname1);
            summaryTextView.setText(whichBloodneeded1);
            summaryTextView.setBackgroundColor(context.getResources().getColor(R.color.darkgreen));
            summaryTextView.setTextColor(context.getResources().getColor(R.color.white));
            summaryTextView2.setText(phonenumber1);
            getorreceiver.setText(gettertext);
            getorreceiver.setBackgroundColor(context.getResources().getColor(R.color.darkgreen));
            getorreceiver.setTextColor(context.getResources().getColor(R.color.white));
        }


    }
}
