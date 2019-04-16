package org.pursuit.quoteme.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.pursuit.quoteme.database.model.QuoteDBModel;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface QuoteDao {
    //TODO add queries

    @Query("SELECT * FROM QuoteDBModel where id = :id")
    QuoteDBModel loadQuoteById(int id);

    @Insert(onConflict = IGNORE)
    void insertQuotes(QuoteDBModel quoteDBModel);

    @Query("DELETE FROM QuoteDBModel")
    void deleteAll();

}
