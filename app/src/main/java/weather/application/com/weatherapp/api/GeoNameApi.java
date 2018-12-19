package weather.application.com.weatherapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.application.com.weatherapp.domain.GeoCityResponse;

/**
 * @author Matias Cestoni.
 */
public interface GeoNameApi {

    @GET("searchJSON")
    Call<GeoCityResponse> findCity(
            @Query("name_startsWith") String cityName,
            @Query("maxRows") int maxRows,
            @Query("lang") String lang,
            @Query("cities") String cities,
            @Query("username") String userName);
}
