package kulikova.weather.api;

import android.app.Application;
import android.content.Context;

import kulikova.weather.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    Retrofit retrofit = null;

    public static App get(Context ctx){
        return (App)ctx.getApplicationContext();
    }

    public Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.api))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}