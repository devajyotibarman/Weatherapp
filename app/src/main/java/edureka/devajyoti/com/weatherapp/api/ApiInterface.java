package edureka.devajyoti.com.weatherapp.api;

import edureka.devajyoti.com.weatherapp.model.ForecastResponse;
import edureka.devajyoti.com.weatherapp.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Devajyoti on 10/1/2017.
 */

public interface ApiInterface {
    @GET("forecast/daily")
    Call<ForecastResponse> getForecast(@Query("lat") Double latitude,
                                       @Query("lon") Double longitude,
                                       @Query("cnt") int count,
                                       @Query("appid") String apiKey,
                                       @Query("units") String units);

    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("lat") Double latitude,
                                     @Query("lon") Double longitude,
                                     @Query("appid") String apiKey,
                                     @Query("units") String units);
}