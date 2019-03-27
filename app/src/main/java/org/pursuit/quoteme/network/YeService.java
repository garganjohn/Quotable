package org.pursuit.quoteme.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YeService {
    String PATH = " ";

    @GET(PATH)
    Call<Ye> getYeAPI();
}
