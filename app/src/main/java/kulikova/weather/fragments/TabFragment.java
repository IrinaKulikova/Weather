package kulikova.weather.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kulikova.weather.R;
import kulikova.weather.api.App;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.enums.EnumCoefficient;
import kulikova.weather.services.ServiceLoader;
import kulikova.weather.enums.EnumTime;
import kulikova.weather.adapters.WeatherAdapter;
import retrofit2.Retrofit;

public class TabFragment extends Fragment {

    private static String ARG_POSITION = "position";

    private TextView timeView;
    private TextView tempView;
    private TextView pressureView;
    private TextView windView;
    private TextView humidityView;
    private TextView cloudsView;
    private ImageView iconView;

    Retrofit retrofit;
    WeatherAPI api;
    WeatherAdapter weatherAdapter;

    public TabFragment() {
    }

    public static TabFragment newInstance(int position) {

        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherAdapter = new WeatherAdapter();

        retrofit = App.get(getContext()).getRetrofit();
        api = retrofit.create(WeatherAPI.class);

        weatherAdapter.setOnLoad(list -> {
            timeView.setText(list.getDtTxt());
            tempView.setText(String.valueOf((int)(list.getMain().getTemp()-EnumCoefficient.TEMPERATURE.getValue())));
            cloudsView.setText(list.getClouds().getAll().toString());
            humidityView.setText(list.getMain().getHumidity().toString());
            pressureView.setText(list.getMain().getPressure().toString());
            windView.setText(list.getWind().getSpeed().toString());
            Picasso.with(getContext()).load(getString(R.string.URLicons) + list.getWeather().get(0).getIcon() + getString(R.string.extension)).into(iconView);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"Vodafone.ttf");
        timeView = view.findViewById(R.id.time);
        tempView = view.findViewById(R.id.temperature);
        tempView.setTypeface(typeface);
        cloudsView = view.findViewById(R.id.clouds);
        humidityView = view.findViewById(R.id.humidity);
        pressureView = view.findViewById(R.id.pressure);
        windView = view.findViewById(R.id.wind);
        iconView = view.findViewById(R.id.icon);
        ServiceLoader.loadTime(getContext().getString(R.string.cityID),api, weatherAdapter, EnumTime.getByPosition((getArguments().getInt(ARG_POSITION))));
        return view;
    }
}
