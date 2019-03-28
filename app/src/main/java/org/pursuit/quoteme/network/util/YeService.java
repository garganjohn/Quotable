package org.pursuit.quoteme.network.util;

import org.pursuit.quoteme.network.model.Ye;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YeService {
    String PATH = " ";

    @GET(PATH)
    Call<Ye> getYeAPI();
}
