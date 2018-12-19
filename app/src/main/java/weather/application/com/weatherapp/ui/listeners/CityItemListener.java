package weather.application.com.weatherapp.ui.listeners;

import weather.application.com.weatherapp.domain.GeoCity;

/**
 * @author Matias Cestoni.
 */
public interface CityItemListener {

    void onCitySelected(GeoCity city);
}
