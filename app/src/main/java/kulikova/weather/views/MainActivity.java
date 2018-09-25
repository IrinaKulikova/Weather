package kulikova.weather.views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kulikova.weather.R;
import kulikova.weather.adapters.MyPagerAdapter;
import kulikova.weather.enums.EnumCoefficient;
import kulikova.weather.enums.EnumFragmentImage;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    MyPagerAdapter adapter;

    @BindView(R.id.diagram)
    Button diagram;

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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setBackground(EnumFragmentImage.getByPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setBackground(EnumFragmentImage.getByPosition(0));
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

    @OnClick(R.id.diagram)
    public void showDiagram() {
        Intent intent = new Intent(this, DiagramActivity.class);
        startActivity(intent);
    }
}