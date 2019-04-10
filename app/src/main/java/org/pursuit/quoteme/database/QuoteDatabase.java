package org.pursuit.quoteme.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import org.pursuit.quoteme.database.model.QuoteDBModel;

@Database(entities = {QuoteDBModel.class}, version = 1, exportSchema = false)
public abstract class QuoteDatabase extends RoomDatabase {
    private static QuoteDatabase instance;

    //TODO get dao up running
    public abstract QuoteDao quoteDao;
}
