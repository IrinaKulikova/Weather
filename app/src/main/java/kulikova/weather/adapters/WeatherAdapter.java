package kulikova.weather.adapters;

import android.support.annotation.Nullable;

import kulikova.weather.entities.List;
import kulikova.weather.entities.WeatherJSON;
import kulikova.weather.enums.EnumTime;
import kulikova.weather.interfaces.ListLoad;

public class WeatherAdapter {

    ListLoad onLoad = list -> {};
    kulikova.weather.entities.List list = null;

    public void setData(EnumTime time, WeatherJSON response) {
        this.list = getData(time, response);
            onLoad.OnCreateView(list);
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

    public void setOnLoad(ListLoad onLoad) {
        this.onLoad = onLoad;
    }
}
