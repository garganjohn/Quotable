package org.pursuit.quoteme.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YeSingleton {
    private static Retrofit instance;
    public static final String BASE_URL = "https://api.kanye.rest/";

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

}
