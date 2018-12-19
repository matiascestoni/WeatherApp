package weather.application.com.weatherapp.ui.listeners;

import weather.application.com.weatherapp.domain.CityWeather;

/**
 * @author Matias Cestoni.
 */
public interface CityWeatherListener {

    void onCityWeatherSelected(CityWeather cityWeather);

    void onCityWeatherRemoved(CityWeather cityWeather);
}
