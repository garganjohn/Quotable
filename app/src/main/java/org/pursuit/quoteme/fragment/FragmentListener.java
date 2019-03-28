package org.pursuit.quoteme.fragment;

import android.support.v4.app.Fragment;

public interface FragmentListener {
    void toDisplayFragment(String str);

    void closeFragment(Fragment fragment);
}
