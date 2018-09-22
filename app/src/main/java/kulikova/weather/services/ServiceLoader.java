package kulikova.weather.services;

import android.util.Log;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kulikova.weather.api.App;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.utils.WeatherAdapterList;

public class ServiceLoader {
    public static void load(WeatherAPI weatherAPI, WeatherAdapterList adapter) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r -> {
                    Log.i("response", r.toString());
                    adapter.setWeathers(r.getList());
                }, e -> {
                    e.printStackTrace();
                });
    }
}
