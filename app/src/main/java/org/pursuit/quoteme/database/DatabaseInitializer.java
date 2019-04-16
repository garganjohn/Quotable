package org.pursuit.quoteme.database;

import android.os.AsyncTask;
import android.util.Log;

import org.pursuit.quoteme.database.model.QuoteDBModel;
import org.pursuit.quoteme.network.QuoteRepository;

//TODO initialize db
public class DatabaseInitializer {

    public static void populateASync(final QuoteDatabase db) {
        PopulateDbAsync async = new PopulateDbAsync(db);
        async.execute();
    }

    private static QuoteDBModel addQuote(final QuoteDatabase db, final int id, final String kanyeDBQuote, final String motivationalQuoteDB) {
        QuoteDBModel quoteDBModel = new QuoteDBModel();


        quoteDBModel.kanyeDBQuote = kanyeDBQuote;
        quoteDBModel.motivateDBQuote = motivationalQuoteDB;

        db.quoteDao().insertQuotes(quoteDBModel);

        return quoteDBModel;
    }

    private static void populateQuoteRepo(QuoteDatabase db) {
        QuoteRepository qr = new QuoteRepository();
        addQuote(db, 0, qr.kanyeQuote(), qr.motivateQuote());
        Log.d("DB Init", "populateQuoteRepo: " + db.quoteDao().loadQuoteById(0).motivateDBQuote);

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final QuoteDatabase quoteDB;

        PopulateDbAsync(QuoteDatabase db) {
            quoteDB = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
           populateQuoteRepo(quoteDB);
            return null;
        }

    }
}
