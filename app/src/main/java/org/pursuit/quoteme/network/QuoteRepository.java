package org.pursuit.quoteme.network;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import org.pursuit.quoteme.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteRepository {
    public static void populateASync() {
        GetQuotesAsync async = new GetQuotesAsync();
        async.execute();
    }

    private static String motivateQuote() {
        Retrofit retrofit = MotivationalQuoteSingleton.getInstance();
        final String[] quoteAPI = {""};
        Call<List<MotivationalQuote>> call = retrofit.create(MotivationQuoteService.class).getMotivationAPI();
        call.enqueue(new Callback<List<MotivationalQuote>>() {
            @Override
            public void onResponse(Call<List<MotivationalQuote>> call, Response<List<MotivationalQuote>> response) {
                quoteAPI[0] = response.body().get(0).getContent();
            }

            @Override
            public void onFailure(Call<List<MotivationalQuote>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return quoteAPI[0];
    }

    private static String kanyeQuote() {
        final String quotation = "\"";

        Retrofit retrofit = YeSingleton.getInstance();
        final String[] quoteAPI = {""};
        Call<Ye> call = retrofit.create(YeService.class).getYeAPI();

        call.enqueue(new Callback<Ye>() {
            @Override
            public void onResponse(Call<Ye> call, Response<Ye> response) {
                quoteAPI[0] = response.body().getQuote();
            }

            @Override
            public void onFailure(Call<Ye> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return quotation + quoteAPI[0] + quotation;
    }

    private static class GetQuotesAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(final Void... voids) {
            kanyeQuote();
            motivateQuote();
            return null;
        }
    }
}
