package weather.application.com.weatherapp.mvp.view;

import java.util.List;

import weather.application.com.weatherapp.domain.CityWeather;

/**
 * @author Matias Cestoni.
 */
public interface MainView {

    void navigateToSearchView();

    void navigateToSettingsView();

    void showCityCancelledMessage();

    void updateCityWeatherListView(List<CityWeather> cityWeatherList);

    void navigateToCityWeatherDetails(CityWeather cityWeather);

    void showLoading();

    void hideLoading();

    void removeCityWeatherFromList(int position);

    void showErrorRetrievingWeather();
}
