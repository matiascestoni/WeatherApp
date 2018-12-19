package weather.application.com.weatherapp.mvp.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import weather.application.com.weatherapp.BuildConfig;
import weather.application.com.weatherapp.api.CityWeatherApi;
import weather.application.com.weatherapp.api.CityWeatherApiClient;
import weather.application.com.weatherapp.domain.CityWeather;
import weather.application.com.weatherapp.domain.ForecastWeather;
import weather.application.com.weatherapp.domain.GeoCity;

/**
 * @author Matias Cestoni.
 */
public class WeatherModel {

    public static final int MAX_CITIES_LIST_SIZE = 10;
    private static final String FORECAST_COUNT_DAYS = "7";
    public static final String CITIES_STYLE = "cities1000";

    private List<CityWeather> cityWeatherList = new ArrayList<>();
    private GeoCity currentCity;
    private CityWeather currentCityWeather;

    public void addCityToPreferredList(ForecastWeather forecastWeather) {
        if (!isCityAlreadyInList(currentCity)) {
            CityWeather cityWeather = new CityWeather(currentCity, forecastWeather);
            cityWeatherList.add(cityWeather);
        }
    }

    private boolean isCityAlreadyInList(GeoCity city) {
        if (city != null) {
            for (CityWeather cityWeather : cityWeatherList) {
                if (city.equals(cityWeather.getCity())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<CityWeather> getCityWeatherList() {
        return cityWeatherList;
    }

    public void removeCityWeatherFromList(CityWeather cityWeather) {
        cityWeatherList.remove(cityWeather);
    }

    public Observable<ForecastWeather> getObservable(GeoCity city) {
        currentCity = city;
        return CityWeatherApiClient.getRetrofit().create(CityWeatherApi.class)
                .getWeather(BuildConfig.WEATHER_API_KEY, getCoordinates(city), FORECAST_COUNT_DAYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ForecastWeather> getObservable(CityWeather cityWeather) {
        currentCityWeather = cityWeather;
        return CityWeatherApiClient.getRetrofit().create(CityWeatherApi.class)
                .getWeather(BuildConfig.WEATHER_API_KEY, getCoordinates(cityWeather.getCity()), FORECAST_COUNT_DAYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private String getCoordinates(GeoCity city) {
        return String.valueOf(city.getLat()) + " , " + String.valueOf(city.getLng());
    }

    public void updateCurrentWeather(ForecastWeather response) {
        for (CityWeather cityWeather : cityWeatherList) {
            if (cityWeather.getCity().equals(currentCityWeather.getCity())) {
                cityWeather.setForecast(response);
            }
        }
    }
}
