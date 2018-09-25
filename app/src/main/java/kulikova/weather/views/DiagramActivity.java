package kulikova.weather.views;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kulikova.weather.R;
import kulikova.weather.api.App;
import kulikova.weather.api.WeatherAPI;
import kulikova.weather.services.ServiceLoader;
import kulikova.weather.utils.PointsAdapter;
import retrofit2.Retrofit;

public class DiagramActivity extends AppCompatActivity {

    LineView lineView;
    List<Float> points;
    Retrofit retrofit;

    WeatherAPI api;
    PointsAdapter pointsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);

        retrofit = App.get(this).getRetrofit();
        api = retrofit.create(WeatherAPI.class);

        pointsAdapter = new PointsAdapter();
        points = new ArrayList<Float>();

        pointsAdapter.setOnLoad(pointList -> {
            points = pointList;
            lineView.setPoints(points);
        });
        lineView = findViewById(R.id.line_view);
        ServiceLoader.loadPoints(this.getString(R.string.cityID), api, pointsAdapter);
    }
}
