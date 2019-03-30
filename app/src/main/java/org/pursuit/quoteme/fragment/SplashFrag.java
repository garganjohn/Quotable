package org.pursuit.quoteme.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.quoteme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFrag extends Fragment {
    private FragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentListener) {
            fragmentListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Implement your interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SplashFrag frag = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentListener.closeFragment(frag);
            }
        }, 2500 );
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }
}
