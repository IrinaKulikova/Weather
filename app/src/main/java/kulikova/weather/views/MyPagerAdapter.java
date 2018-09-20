package kulikova.weather.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] captions = new String[]{
            "Утро", "День", "Вечер", "5 дней"
    };

    Fragment morningFragment;
    Fragment middayFragment;
    Fragment eveningFragment;
    Fragment listFragment;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            morningFragment = TabFragment.newInstance("Утро");
            return morningFragment;
        } else if (position == 1) {
            middayFragment = TabFragment.newInstance("День");
            return middayFragment;
        } else if (position == 2) {
            eveningFragment = TabFragment.newInstance("Вечер");
            return eveningFragment;
        }
        if (position == 3) {
            listFragment = ListFragment.newInstance("5 дней");
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
