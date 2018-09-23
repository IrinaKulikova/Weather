package kulikova.weather.utils;

import java.util.List;

public class PointsAdapter {

    PointLoad onLoad = null;
    List<Float> pointList = null;

    public void setData(List<Float> points ) {
        this.pointList = points;
        if (onLoad != null) {
            onLoad.OnLoaded(points);
        }
    }

    public void setOnLoad(PointLoad onLoad) {
        this.onLoad = onLoad;
    }
}