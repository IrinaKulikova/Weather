package kulikova.weather.services;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kulikova.weather.R;
import kulikova.weather.api.App;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.entities.List;
import kulikova.weather.entities.Weather;
import kulikova.weather.entities.WeatherJSON;
import kulikova.weather.utils.EnumTime;
import kulikova.weather.utils.WeatherAdapter;
import kulikova.weather.utils.WeatherAdapterList;

public class ServiceLoader {
    public static void load(WeatherAPI weatherAPI, WeatherAdapterList adapter) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    //TODO: изменить при наличие бд, (загрузка погоды со следующего день)
                    adapter.setWeathers(createList(response));
                }, e -> {
                    e.printStackTrace();
                });
    }

    private static java.util.List<kulikova.weather.entities.List> createList(WeatherJSON weatherJSON) {

        java.util.List<kulikova.weather.entities.List> newList = new ArrayList<>();

        int days = 3;

        for (kulikova.weather.entities.List list : weatherJSON.getList()) {
            if (list.getDtTxt().contains(EnumTime.MORNING.toString())) {
                if (newList.size() < days) {
                    newList.add(list);
                } else {
                    break;
                }
            }
        }
        return newList;
    }

    public static void loadTime(WeatherAPI weatherAPI, WeatherAdapter adapter, EnumTime time) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    //TODO: выбирать из бд
                    adapter.setData(getData(time, response));
                }, e -> {
                    e.printStackTrace();
                });
    }

    @Nullable
    private static List getData(EnumTime time, WeatherJSON response) {
        for (List list : response.getList()) {
            if (list.getDtTxt().contains(time.toString())) {
                return list;
            }
        }
        return null;
    }
}
