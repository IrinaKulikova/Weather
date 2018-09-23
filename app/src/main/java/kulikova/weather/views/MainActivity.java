package kulikova.weather.views;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import kulikova.weather.R;
import kulikova.weather.services.ServiceLoader;
import kulikova.weather.utils.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    MyPagerAdapter adapter;

    String[] images = new String[]{
            "morning.jpg", "midday.jpg", "evening.jpg", "night.jpg", "3_days_2.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InitViews();
    }

    private void InitViews() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                setBackground(images[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setBackground(images[0]);
    }

    private void setBackground(String image) {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(inputStream, null);
        viewPager.setBackground(d);
    }
}