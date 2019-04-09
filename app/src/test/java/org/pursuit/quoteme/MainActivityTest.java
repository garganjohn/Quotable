package org.pursuit.quoteme;

import android.support.v4.app.Fragment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pursuit.quoteme.fragment.ViewPagerFragment;

public class MainActivityTest {
    Fragment frag;

    @Before
    public void setup() {
        frag = ViewPagerFragment.getInstance("");
    }

    @Test
    public void viewPagerFragment_Name_With_Null() {
        Fragment actual = frag;

        Assert.assertNotNull(actual);
    }

    @After
    public void tearDown() {

    }
}
