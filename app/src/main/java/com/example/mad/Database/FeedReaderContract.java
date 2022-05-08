package com.example.mad.Database;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "OwnerName";
        public static final String COLUMN_2 = "contactNumber";
        public static final String COLUMN_3 = "Address";
        public static final String COLUMN_4 = "vehicleType";
        public static final String COLUMN_5 = "vehicleModel";
        public static final String COLUMN_6 = "passengers";

    }
}