package org.pursuit.quoteme.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MotivationQuoteService {
    String PATH = "wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";
    @GET(PATH)
    Call<List<MotivationalQuote>> getMotivationAPI();
}
