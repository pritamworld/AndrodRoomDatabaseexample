package com.pritesh.androdroomdatabaseexample.models;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by pritesh.patel on 2017-11-21, 2:47 PM.
 * ADESA, Canada
 */

public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
