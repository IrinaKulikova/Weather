package kulikova.weather.services;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.entities.List;
import kulikova.weather.entities.WeatherJSON;
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

    public static void loadPoints(WeatherAPI weatherAPI, PointsAdapter adapter) {
        weatherAPI.getByCityId("709930")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    //TODO: изменить при наличие бд
                    adapter.setData(createListPoints(response));
                }, e -> {
                    e.printStackTrace();
                });
    }

    private static java.util.List createListPoints(WeatherJSON response) {

        //TODO: выбирать точки с бд
        java.util.List<Float> pointsJSON = new ArrayList<Float>();

        for (kulikova.weather.entities.List list : response.getList()) {
            if(list.getDtTxt().contains(EnumTime.MORNING.toString())||
                    list.getDtTxt().contains(EnumTime.MIDDAY.toString())||
                            list.getDtTxt().contains(EnumTime.EVENING.toString())||
                                            list.getDtTxt().contains(EnumTime.NIGHT.toString())) {
                pointsJSON.add(list.getMain().getTemp().floatValue());
            }
        }
        return pointsJSON;
    }
}
