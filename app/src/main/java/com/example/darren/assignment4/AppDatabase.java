package com.example.darren.assignment4;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Darren on 3/18/2018.
 */

@Database(entities = {SynonymPair.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase db = null;

    public abstract SynonymPairDao synonymPairDao();

    public AppDatabase() {
        db = this;
    }

    public static AppDatabase getAppDatabase(Context context) {
        return db == null ? Room.databaseBuilder(context, AppDatabase.class, "synonym-pairs").build() : db;
    }
}
