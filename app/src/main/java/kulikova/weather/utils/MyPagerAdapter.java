package kulikova.weather.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kulikova.weather.fragments.ListFragment;
import kulikova.weather.fragments.TabFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] captions = new String[]{
            "Morning", "Midday", "Evening", "5 days"
    };

    Fragment morningFragment;
    Fragment middayFragment;
    Fragment eveningFragment;
    Fragment listFragment;
    Context context;


    public MyPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            morningFragment = TabFragment.newInstance(captions[0]);
            return morningFragment;

        } else if (position == 1) {
            middayFragment = TabFragment.newInstance(captions[1]);
            return middayFragment;
        } else if (position == 2) {
            eveningFragment = TabFragment.newInstance(captions[2]);
            return eveningFragment;
        }
        if (position == 3) {
            listFragment = ListFragment.newInstance(captions[3]);
            return listFragment;
        }
        return null;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return captions[position];
    }

    @Override
    public int getCount() {
        return captions.length;
    }
}
