package kulikova.weather.api;

import io.reactivex.Observable;
import kulikova.weather.entities.WeatherJSON;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("data/2.5/forecast?appid=5f6b7e1998e37b6b94bdfc5f81916d4c")
    Observable<WeatherJSON> getByCityId(@Query("id") String id);
}
