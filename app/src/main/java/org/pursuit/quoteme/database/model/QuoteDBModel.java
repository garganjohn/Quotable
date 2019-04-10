package org.pursuit.quoteme.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class QuoteDBModel {
    @PrimaryKey
    @NonNull
    public int id;

    @ColumnInfo(name="kanye")
    public String kanyeDBQuote;

    @ColumnInfo(name="motivational")
    public String motivateDBQuote;
}
