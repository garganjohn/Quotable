package org.pursuit.quoteme.fragment;


import android.content.Context;
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
import org.pursuit.quoteme.network.QuotesRepo;
import org.pursuit.quoteme.network.util.MotivationQuoteService;
import org.pursuit.quoteme.network.model.MotivationalQuote;
import org.pursuit.quoteme.network.MotivationalQuoteSingleton;
import org.pursuit.quoteme.network.model.Ye;
import org.pursuit.quoteme.network.util.YeService;
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
    private String quote;
    private TextView nameTV;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        QuotesRepo repo = new QuotesRepo();
        quote = repo.getMotivationalQuote();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            QuotesRepo repo = new QuotesRepo();
            name = getArguments().getString(NAME_KEY);
            switch (name) {
                case "Motivational Quotes":
                    //quote = repo.getMotivationalQuote();
                    break;
                case "Kanye Quotes":
                    //quote = repo.getKanyeQuote();
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
        nameTV = view.findViewById(R.id.display_frag_name);
        nameTV.setText(quote);

    }


}
