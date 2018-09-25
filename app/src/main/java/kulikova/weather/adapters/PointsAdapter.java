package kulikova.weather.adapters;

import java.util.ArrayList;
import java.util.List;

import kulikova.weather.entities.WeatherJSON;
import kulikova.weather.enums.EnumTime;
import kulikova.weather.interfaces.PointLoad;

public class PointsAdapter {

    PointLoad onLoad = pointList1 -> {
    };

    List<Float> pointList = null;

    public void setData(WeatherJSON response) {
        this.pointList = createListPoints(response);
        onLoad.OnLoaded(pointList);
    }

    private static java.util.List createListPoints(WeatherJSON response) {

        //TODO: выбирать точки с бд
        java.util.List<Float> pointsJSON = new ArrayList<Float>();
        int days = 3;
        int points = 4;
        for (kulikova.weather.entities.List list : response.getList()) {
            if (list.getDtTxt().contains(EnumTime.MORNING.toString()) ||
                    list.getDtTxt().contains(EnumTime.MIDDAY.toString()) ||
                    list.getDtTxt().contains(EnumTime.EVENING.toString()) ||
                    list.getDtTxt().contains(EnumTime.NIGHT.toString())) {
                if (pointsJSON.size() < days * points) {
                    pointsJSON.add(list.getMain().getTemp().floatValue());
                } else {
                    break;
                }
            }
        }
        return pointsJSON;
    }

    public void setOnLoad(PointLoad onLoad) {
        this.onLoad = onLoad;
    }
}