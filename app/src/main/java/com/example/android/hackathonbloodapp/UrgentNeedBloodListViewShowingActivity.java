package com.example.android.hackathonbloodapp;

//import android.support.v4.app.LoaderManager;


import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.content.ContentUris;
import android.support.v4.content.CursorLoader;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by omar on 14-02-2017.
 */
public class UrgentNeedBloodListViewShowingActivity extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {


    Button button;

    ListView urgentbloodListview;

    RadioButton whatselected;

    public UrgentNeedBloodListViewShowingActivity() {

    }

    private LoaderManager.LoaderCallbacks<Cursor> callBacks;

    private static final int URGENT_BLOOD_LOADER = 0;

    BloodCursorAdapter mbloodCursorAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.urgent_blood_listview_layout, container, false);


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SpinnerActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RadioGroup radiogroup = (RadioGroup) this.getActivity().findViewById(R.id.radiogroup);

        button = (Button) this.getActivity().findViewById(R.id.buttonfilter);

        urgentbloodListview = (ListView) this.getActivity().findViewById(R.id.urgentbloodlist);

        int selectedId = radiogroup.getCheckedRadioButtonId();
        whatselected  = (RadioButton) this.getActivity().findViewById(selectedId);

//        GetterSetterfilter gettersetter = new GetterSetterfilter();
//        gettersetter.setWhatis("hi");
        mbloodCursorAdapter = new BloodCursorAdapter(getContext(), null);
        urgentbloodListview.setAdapter(mbloodCursorAdapter);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String whatselect = whatselected.getText().toString().trim();
//
//                if (whatselect.equals("all")) {
//                    mbloodCursorAdapter = new BloodCursorAdapter(getContext(), null);
//                    urgentbloodListview.setAdapter(mbloodCursorAdapter);
//                } else if (whatselect.equals("giversshow")) {
//                    GetterSetterfilter gettersetter = new GetterSetterfilter();
//                    gettersetter.setWhatis(whatselect);
//                    mbloodCursorAdapter = new BloodCursorAdapter(getContext(), null);
//                    urgentbloodListview.setAdapter(mbloodCursorAdapter);
//                } else if (whatselect.equals("receiversshow")) {
//                    mbloodCursorAdapter = new BloodCursorAdapter(getContext(), null);
//                    urgentbloodListview.setAdapter(mbloodCursorAdapter);
//                }
//
//            }
//        });






        urgentbloodListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), UrgentBloodDetailActivity.class);

                Uri currentPetUri = ContentUris.withAppendedId(DatabaseConnectorHelper.CONTENT_URI, id);

                intent.setData(currentPetUri);

                startActivity(intent);

            }
        });

        //getActivity().getSupportLoaderManager().initLoader(URGENT_BLOOD_LOADER, null, (LoaderManager.LoaderCallbacks<Cursor>)this);

        getLoaderManager().initLoader(URGENT_BLOOD_LOADER, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                DatabaseConnectorHelper.id,
                DatabaseConnectorHelper.firstName,
                DatabaseConnectorHelper.whichBloodNeeded,
                DatabaseConnectorHelper.phone,
                DatabaseConnectorHelper.receiverorgiver};


        return new CursorLoader(getActivity(),
                DatabaseConnectorHelper.CONTENT_URI,
                projection,
                null,
                null,
                null);



    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        mbloodCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        mbloodCursorAdapter.swapCursor(null);


    }

}
