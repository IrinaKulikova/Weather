package kulikova.weather.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kulikova.weather.R;
import kulikova.weather.api.App;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.services.ServiceLoader;
import kulikova.weather.utils.WeatherAdapterList;
import retrofit2.Retrofit;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    WeatherAdapterList list;
    Retrofit retrofit;
    WeatherAPI api;

    public ListFragment() {
    }

    public static ListFragment newInstance(String param1) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = App.get(getContext()).getRetrofit();
        api = retrofit.create(WeatherAPI.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new WeatherAdapterList();
        ServiceLoader.load(getContext().getString(R.string.cityID) , api, list);
        recyclerView.setAdapter(list);
        return recyclerView;
    }
}
