package kulikova.weather.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kulikova.weather.fragments.ListFragment;
import kulikova.weather.fragments.TabFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] captions = new String[]{
            "Morning", "Midday", "Evening", "Night", "3 days"
    };

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position < captions.length - 1) {
            return TabFragment.newInstance(position);
        } else if (position == captions.length - 1) {
            return ListFragment.newInstance(captions[position]);
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
