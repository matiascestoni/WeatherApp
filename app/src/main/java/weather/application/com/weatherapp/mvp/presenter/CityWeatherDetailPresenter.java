package weather.application.com.weatherapp.mvp.presenter;

import android.support.annotation.Nullable;

import weather.application.com.weatherapp.mvp.view.CityWeatherDetailsView;

/**
 * @author Matias Cestoni.
 */
public class CityWeatherDetailPresenter {

    private CityWeatherDetailsView view;

    public void setView(@Nullable CityWeatherDetailsView view) {
        this.view = view;
    }

}
