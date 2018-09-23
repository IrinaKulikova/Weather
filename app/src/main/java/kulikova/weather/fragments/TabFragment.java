package kulikova.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kulikova.weather.R;
import kulikova.weather.api.App;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.services.ServiceLoader;
import kulikova.weather.utils.EnumTime;
import kulikova.weather.utils.WeatherAdapter;
import retrofit2.Retrofit;

public class TabFragment extends Fragment {

    private static String ARG_POSITION = "position";
    private int position;

    private TextView timeView;
    private TextView tempView;
    private TextView pressureView;
    private TextView windView;
    private TextView humidityView;
    private TextView cloudsView;
    private TextView iconView;

    Retrofit retrofit;
    WeatherAPI api;
    WeatherAdapter adapter;

    public TabFragment() {}

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
        adapter = new WeatherAdapter();

        retrofit = App.get(getContext()).getRetrofit();
        api = retrofit.create(WeatherAPI.class);

        adapter.setOnLoad(list -> {
            timeView.setText(list.getDtTxt());
            tempView.setText(list.getMain().getTemp().toString());
            cloudsView.setText(list.getClouds().getAll().toString());
            humidityView.setText(list.getMain().getHumidity().toString());
            pressureView.setText(list.getMain().getPressure().toString());
            windView.setText(list.getWind().getSpeed().toString());
            iconView.setText(list.getWeather().get(0).getIcon());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        timeView = view.findViewById(R.id.time);
        tempView = view.findViewById(R.id.temperature);
        cloudsView = view.findViewById(R.id.clouds);
        humidityView = view.findViewById(R.id.humidity);
        pressureView = view.findViewById(R.id.pressure);
        windView = view.findViewById(R.id.wind);
        //iconView = view.findViewById(R.id.icon);
        ServiceLoader.loadTime(api, adapter, EnumTime.getByPosition((getArguments().getInt(ARG_POSITION))));
        return view;
    }
}
