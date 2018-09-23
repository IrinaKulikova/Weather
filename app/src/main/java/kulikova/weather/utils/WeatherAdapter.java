package kulikova.weather.utils;

import java.util.ArrayList;

public class WeatherAdapter {

    OnLoad onLoad = null;
    kulikova.weather.entities.List list = null;

    public void setData(kulikova.weather.entities.List listCur) {
        this.list = listCur;
        if (onLoad != null) {
            onLoad.OnCreateView(list);
        }
    }

    public void setOnLoad(OnLoad onLoad) {
        this.onLoad = onLoad;
    }
}
