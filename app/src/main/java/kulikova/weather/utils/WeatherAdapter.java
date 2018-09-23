package kulikova.weather.utils;

public class WeatherAdapter {

    ListLoad onLoad = null;
    kulikova.weather.entities.List list = null;

    public void setData(kulikova.weather.entities.List listCur) {
        this.list = listCur;
        if (onLoad != null) {
            onLoad.OnCreateView(list);
        }
    }

    public void setOnLoad(ListLoad onLoad) {
        this.onLoad = onLoad;
    }
}
