package org.pursuit.quoteme.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.quoteme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    public static final String NAME_KEY = "name";
    private String name;

    private FragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            fragmentListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " implement your listener");
        }
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
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView nameTV = view.findViewById(R.id.fragment_name);
        ConstraintLayout viewPagerContainer = view.findViewById(R.id.viewpager_fragment_container);

        nameTV.setText(name);
        viewPagerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.toDisplayFragment(name);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    public static ViewPagerFragment getInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, name);
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }
}

