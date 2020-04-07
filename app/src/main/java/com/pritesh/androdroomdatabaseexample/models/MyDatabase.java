package com.pritesh.androdroomdatabaseexample.models;

import com.pritesh.androdroomdatabaseexample.App;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by pritesh.patel on 2017-11-21, 2:43 PM.
 * ADESA, Canada
 */

@Database(entities = {Product.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class MyDatabase extends RoomDatabase
{
    public abstract ProductDao productDao();
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE product "
                    + " ADD COLUMN price INTEGER");

            // enable flag to force update products
            App.get().setForceUpdate(true);
        }
    };

}
