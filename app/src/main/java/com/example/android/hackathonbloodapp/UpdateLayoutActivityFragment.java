package com.example.android.hackathonbloodapp;

import android.support.v4.app.Fragment;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by omar on 15-02-2017.
 */
public class UpdateLayoutActivityFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int URGENT_BLOOD_LOADER = 0;

    BloodCursorAdapter mbloodCursorAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.urgent_blood_listview_layout, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView urgentbloodListview = (ListView) this.getActivity().findViewById(R.id.urgentbloodlist);

        mbloodCursorAdapter = new BloodCursorAdapter(getContext(),null);
        urgentbloodListview.setAdapter(mbloodCursorAdapter);

        urgentbloodListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), UpdateUrgentActivityDetail.class);

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
