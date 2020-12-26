package com.example.sample;

import android.net.Uri;

public class ContentProviderContract {

    public static final String CONTENT_AUTHORITY = "com.example.sample";
    public static final String PATH_DATATABLE = "Products";
    public static final String TABLE_NAME = "Products";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(PATH_DATATABLE)
            .build();

}
