package weather.application.com.weatherapp.ui.adapters;

import java.util.List;

import weather.application.com.weatherapp.domain.CityWeather;

/**
 * @author Matias Cestoni.
 */
public class DayPager {

    private List<CityWeather> nextDays;

    public DayPager() {
    }

    public List<CityWeather> getNextDays() {
        return nextDays;
    }

    public void setNextDays(List<CityWeather> nextDays) {
        this.nextDays = nextDays;
    }
}
