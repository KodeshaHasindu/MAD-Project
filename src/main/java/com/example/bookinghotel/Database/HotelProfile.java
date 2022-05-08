package com.example.bookinghotel.Database;

import android.provider.BaseColumns;

public final class HotelProfile {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private HotelProfile() {}

        /* Inner class that defines the table contents */
        public static class Hotels implements BaseColumns {
            public static final String TABLE_NAME = "HotelInfo";
            public static final String COLUMN_1 = "HotelName";
            public static final String COLUMN_2 = "RegistrationNumber";
            public static final String COLUMN_3 = "ContactNUmber";
            public static final String COLUMN_4 = "EmailAddress";

        }
    }

