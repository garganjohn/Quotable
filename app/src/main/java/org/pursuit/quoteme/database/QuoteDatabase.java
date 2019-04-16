package org.pursuit.quoteme.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import org.pursuit.quoteme.database.model.QuoteDBModel;

@Database(entities = {QuoteDBModel.class}, version = 1, exportSchema = false)
public abstract class QuoteDatabase extends RoomDatabase {
    private static QuoteDatabase instance;

    public abstract QuoteDao quoteDao();

    public static QuoteDatabase getInstance(Context c) {
        if (instance == null) {
            instance = Room.inMemoryDatabaseBuilder(c.getApplicationContext(), QuoteDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
