package org.pursuit.quoteme.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.quoteme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {
    public static final String NAME_KEY = "display name";
    private String name;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
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
        nameTV.setText(name);

    }
}
