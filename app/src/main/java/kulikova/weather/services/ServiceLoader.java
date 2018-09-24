package kulikova.weather.services;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.utils.EnumTime;
import kulikova.weather.utils.PointsAdapter;
import kulikova.weather.utils.WeatherAdapter;
import kulikova.weather.utils.WeatherAdapterList;

public class ServiceLoader {
    public static void load(String id, WeatherAPI weatherAPI, WeatherAdapterList adapter) {
        weatherAPI.getByCityId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapter.setWeathers(response);
                }, e -> {
                    e.printStackTrace();
                });
    }

    public static void loadTime(String id,WeatherAPI weatherAPI, WeatherAdapter adapter, EnumTime time) {
        weatherAPI.getByCityId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapter.setData(time, response);
                }, e -> {
                    e.printStackTrace();
                });
    }

    public static void loadPoints(String id, WeatherAPI weatherAPI, PointsAdapter adapter) {
        weatherAPI.getByCityId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    adapter.setData(response);
                }, e -> {
                    e.printStackTrace();
                });
    }
}
