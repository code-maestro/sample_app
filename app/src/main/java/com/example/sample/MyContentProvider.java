package com.example.sample;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.example.sample.ContentProviderContract.CONTENT_AUTHORITY;

public class MyContentProvider extends ContentProvider {

    public DatabaseHelper dbHelper;
    public static final UriMatcher matcher = buildUriMatcher();
    public static final int DATA_TABLE = 100;
    public static final int DATA_TABLE_DATE = 101;

    private static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_AUTHORITY, ContentProviderContract.PATH_DATATABLE, DATA_TABLE);
        uriMatcher.addURI(CONTENT_AUTHORITY,
                ContentProviderContract.PATH_DATATABLE + "/#", DATA_TABLE_DATE);
        return uriMatcher;

    }

    @Override
    public boolean onCreate() {

        dbHelper = new DatabaseHelper(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        Cursor retCursor = dbHelper.getReadableDatabase().query(
                ContentProviderContract.TABLE_NAME,
                                        projection,
                                        selection,
                                        selectionArgs, null, null, sortOrder);
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        final int match = matcher.match(uri);

        switch (match) {
            case DATA_TABLE:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/"
                        + ContentProviderContract.CONTENT_AUTHORITY + "/"
                        + ContentProviderContract.PATH_DATATABLE;
            case DATA_TABLE_DATE:
                return ContentResolver.ANY_CURSOR_ITEM_TYPE + "/"
                        + ContentProviderContract.CONTENT_AUTHORITY + "/"
                        + ContentProviderContract.PATH_DATATABLE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(ContentProviderContract.TABLE_NAME, null, values);

        Log.d("Content provider", "insert: Inserting tf");
        
        return ContentUris.withAppendedId(ContentProviderContract.CONTENT_URI, id);

    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete(ContentProviderContract.TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsUpdated = db.update(ContentProviderContract.TABLE_NAME,
                                                            values,
                                                            selection,
                                                            selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
