package org.pursuit.quoteme.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.badtranslator.BadTranslator;
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

    private ConstraintLayout display_frag_container;
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

            getDemotivationalQuote();

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
                String titleResponse = response.body().get(0).getTitle();
                String fixedStringResponse = fixStringResponse(quoteAPI[0]);
                author.setText(titleResponse);
                quote.setText(fixedStringResponse);
            }

            @Override
            public void onFailure(Call<List<MotivationalQuote>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void getKanyeQuote() {
        final String quotation = "\"";
        Retrofit retrofit = YeSingleton.getInstance();
        final String[] quoteAPI = {""};
        Call<Ye> call = retrofit.create(YeService.class).getYeAPI();
        Log.d(TAG, "getKanyeQuote: ;" + call.request());
        call.enqueue(new Callback<Ye>() {
            @Override
            public void onResponse(Call<Ye> call, Response<Ye> response) {
                quoteAPI[0] = response.body().getQuote();
                title.setText("What would Kanye say:");
                author.setText("Kanye");
                quote.setText(quotation + quoteAPI[0] + quotation);
            }

            @Override
            public void onFailure(Call<Ye> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getDemotivationalQuote() {
        BadTranslator badTranslator = new BadTranslator();
        String demotivate = badTranslator.demotivateMe();
        title.setText("Demotivational");
        quote.setText(demotivate);

    }

    private String fixStringResponse(String str) {
        return str.replaceAll("<p>","")
                .replaceAll("</p>","")
                .replaceAll("&#8217;","'")
                .replaceAll("<br />", "")
                .replaceAll("<strong>","")
                .replaceAll("<strong>","");
    }

}
