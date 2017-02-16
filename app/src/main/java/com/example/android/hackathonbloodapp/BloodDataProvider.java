package com.example.android.hackathonbloodapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Switch;

/**
 * Created by omar on 14-02-2017.
 */

public class BloodDataProvider extends ContentProvider {

    private static final int BlOODS = 100;


    private static final int BLOODS_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {

        sUriMatcher.addURI(DatabaseConnectorHelper.CONTENT_AUTHORITY, DatabaseConnectorHelper.PATH_BLOODS, BlOODS);

        sUriMatcher.addURI(DatabaseConnectorHelper.CONTENT_AUTHORITY, DatabaseConnectorHelper.PATH_BLOODS + "/#", BLOODS_ID);

    }

    private DatabaseConnectorHelper dbhelper;



    @Override
    public boolean onCreate() {
        dbhelper = new DatabaseConnectorHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database =  dbhelper.getReadableDatabase();

        Cursor cursor ;
        int match = sUriMatcher.match(uri);

        switch(match){

            case BlOODS:
                cursor = database.query(DatabaseConnectorHelper.tableName, projection , selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case BLOODS_ID:
                selection = DatabaseConnectorHelper.id + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };



                cursor = database.query(DatabaseConnectorHelper.tableName, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = dbhelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BlOODS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(DatabaseConnectorHelper.tableName, selection, selectionArgs);
                break;
            case BLOODS_ID:
                // Delete a single row given by the ID in the URI
                selection = DatabaseConnectorHelper.id + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(DatabaseConnectorHelper.tableName, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }



    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BlOODS:
                return updatePet(uri, contentValues, selection, selectionArgs);
            case BLOODS_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = DatabaseConnectorHelper.id + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }


    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {


        SQLiteDatabase database = dbhelper.getWritableDatabase();


        int rowsUpdated = database.update(DatabaseConnectorHelper.tableName, values, selection, selectionArgs);


        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }


        return rowsUpdated;
    }




}
