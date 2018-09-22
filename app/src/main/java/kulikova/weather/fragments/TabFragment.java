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
import retrofit2.Retrofit;

public class TabFragment extends Fragment {

    private static final String ARG_CAPTION = "param1";
    private String caption="def";
    private TextView captionView;

    Retrofit retrofit;
    WeatherAPI api;

    public TabFragment() {}

    public static TabFragment newInstance(String caption) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CAPTION, caption);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = App.get(getContext()).getRetrofit();
        api = retrofit.create(WeatherAPI.class);

        if (getArguments() != null) {
            caption = getArguments().getString(ARG_CAPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        captionView = view.findViewById(R.id.caption);
        captionView.setText(caption);
        return view;
    }
}
