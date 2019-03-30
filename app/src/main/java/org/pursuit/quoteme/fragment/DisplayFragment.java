package org.pursuit.quoteme.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.quoteme.R;
import org.pursuit.quoteme.network.MotivationQuoteService;
import org.pursuit.quoteme.network.MotivationalQuote;
import org.pursuit.quoteme.network.MotivationalQuoteSingleton;
import org.pursuit.quoteme.network.Ye;
import org.pursuit.quoteme.network.YeService;
import org.pursuit.quoteme.network.YeSingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {
    public static final String TAG = "Display Fragment";
    public static final String NAME_KEY = "display name";
    private String name;

    private TextView author;
    private TextView quote;
    private TextView title;

    public static DisplayFragment getInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, name);
        DisplayFragment displayFragment = new DisplayFragment();
        displayFragment.setArguments(bundle);
        return displayFragment;
    }

    public DisplayFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            switch (name) {
                case "Motivational Quotes":
                    getMotivationalQuote();
                    break;
//                case "Demotivational Quotes":
//                    getDemotivationalQuote();
//                    break;
                case "Kanye Quotes":
                    getKanyeQuote();
                    break;
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().getString(NAME_KEY).equals("Demotivational Quotes")) {
            quote = view.findViewById(R.id.display_frag_quote);
            title = view.findViewById(R.id.display_frag_title);

            title.setText("Demotivational");
            quote.setText("ur bad");

        }
        quote = view.findViewById(R.id.display_frag_quote);
        title = view.findViewById(R.id.display_frag_title);
        author = view.findViewById(R.id.display_frag_author);
    }

    private void getMotivationalQuote() {
        Retrofit retrofit = MotivationalQuoteSingleton.getInstance();
        final String[] quoteAPI = {""};
        Call<List<MotivationalQuote>> call = retrofit.create(MotivationQuoteService.class).getMotivationAPI();
        call.enqueue(new Callback<List<MotivationalQuote>>() {
            @Override
            public void onResponse(Call<List<MotivationalQuote>> call, Response<List<MotivationalQuote>> response) {
                quoteAPI[0] = response.body().get(0).getContent();
                title.setText("Motivational");
                author.setText(response.body().get(0).getTitle());
                quote.setText(quoteAPI[0]);
            }

            @Override
            public void onFailure(Call<List<MotivationalQuote>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void getKanyeQuote() {
        Retrofit retrofit = YeSingleton.getInstance();
        final String[] quoteAPI = {""};
        Call<Ye> call = retrofit.create(YeService.class).getYeAPI();
        Log.d(TAG, "getKanyeQuote: ;" + call.request());
        call.enqueue(new Callback<Ye>() {
            @Override
            public void onResponse(Call<Ye> call, Response<Ye> response) {
                quoteAPI[0] = response.body().getQuote();
                title.setText("What would Kanye say:");
                quote.setText(quoteAPI[0]);
            }

            @Override
            public void onFailure(Call<Ye> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getDemotivationalQuote() {
        String quote = "ur bad";
        title.setText(quote);

    }

    private String fixStringResponse(String str){
        String holder = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<' && str.charAt(i+1) == 'p' && str.charAt(i+2) == '>'){
                holder = holder + str.substring(i+2);
                i = i+3;
                continue;
            }else if(str.charAt(i) == '<' && str.charAt(i+1) == '/' && str.charAt(i+2) == 'p' && str.charAt(i + 3) == '>'){
                holder = holder + "";
                break;
            }

        }
        return holder;
    }
}
