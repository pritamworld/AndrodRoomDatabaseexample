package com.pritesh.androdroomdatabaseexample;

/**
 * Created by pritesh.patel on 2017-11-21, 2:48 PM.
 * ADESA, Canada
 */

import android.app.Application;
import android.content.SharedPreferences;

import com.pritesh.androdroomdatabaseexample.models.MyDatabase;

import androidx.room.Room;


public class App extends Application
{
    public static App INSTANCE;
    private static final String DATABASE_NAME = "MyDatabase";
    private static final String PREFERENCES = "RoomDemo.preferences";
    private static final String KEY_FORCE_UPDATE = "force_update";

    private MyDatabase database;

    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // create database
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                .addMigrations(MyDatabase.MIGRATION_1_2)
                .build();

        INSTANCE = this;
    }

    public MyDatabase getDB() {
        return database;
    }

    public boolean isForceUpdate() {
        return getSP().getBoolean(KEY_FORCE_UPDATE, true);
    }

    public void setForceUpdate(boolean force) {
        SharedPreferences.Editor edit = getSP().edit();
        edit.putBoolean(KEY_FORCE_UPDATE, force);
        edit.apply();
    }

    private SharedPreferences getSP() {
        return getSharedPreferences(PREFERENCES, MODE_PRIVATE);
    }
}

