package weather.application.com.weatherapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.application.com.weatherapp.domain.ForecastWeather;

/**
 * @author Matias Cestoni.
 */
public interface CityWeatherApi {

    @GET("forecast.json")
    Observable<ForecastWeather> getWeather(@Query("key") String key, @Query("q") String LatLng, @Query("days") String days);
}
