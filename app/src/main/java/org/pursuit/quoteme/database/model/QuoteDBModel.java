package org.pursuit.quoteme.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;


@Entity
public class QuoteDBModel {

    @ColumnInfo(name="kanye")
    public String kanyeDBQuote;

    @ColumnInfo(name="motivational")
    public String motivateDBQuote;
}
