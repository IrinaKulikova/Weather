package kulikova.weather.services;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.utils.EnumTime;
import kulikova.weather.utils.PointsAdapter;
import kulikova.weather.utils.WeatherAdapter;
import kulikova.weather.utils.WeatherAdapterList;

public class ServiceLoader {
    public static void load(WeatherAPI weatherAPI, WeatherAdapterList adapter) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapter.setWeathers(response);
                }, e -> {
                    e.printStackTrace();
                });
    }

    public static void loadTime(WeatherAPI weatherAPI, WeatherAdapter adapter, EnumTime time) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapter.setData(time, response);
                }, e -> {
                    e.printStackTrace();
                });
    }

    public static void loadPoints(WeatherAPI weatherAPI, PointsAdapter adapter) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapter.setData(response);
                }, e -> {
                    e.printStackTrace();
                });
    }
}
