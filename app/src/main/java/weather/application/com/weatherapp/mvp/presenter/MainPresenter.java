package weather.application.com.weatherapp.mvp.presenter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import weather.application.com.weatherapp.domain.CityWeather;
import weather.application.com.weatherapp.domain.ForecastWeather;
import weather.application.com.weatherapp.domain.GeoCity;
import weather.application.com.weatherapp.mvp.model.WeatherModel;
import weather.application.com.weatherapp.mvp.view.MainView;

/**
 * @author Matias Cestoni.
 */
public class MainPresenter {

    private MainView view;
    private WeatherModel model;

    public MainPresenter(WeatherModel model) {
        this.model = model;
    }

    public void setView(@Nullable MainView view) {
        this.view = view;
    }

    public void onAddCityButtonClicked() {
        if (view != null) {
            view.navigateToSearchView();
        }
    }

    public void onSettingsButtonClicked() {
        if (view != null) {
            view.navigateToSettingsView();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void onCitySelected(GeoCity city) {
        if (view != null) {
            view.showLoading();
            model.getObservable(city).subscribeWith(getObserver());
        }
    }

    private DisposableObserver<ForecastWeather> getObserver() {
        return new DisposableObserver<ForecastWeather>() {

            @Override
            public void onNext(@NonNull ForecastWeather response) {
                if (view != null) {
                    model.addCityToPreferredList(response);
                    view.updateCityWeatherListView(model.getCityWeatherList());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (view != null) {
                    view.hideLoading();
                    view.showErrorRetrievingWeather();
                }
            }

            @Override
            public void onComplete() {
                if (view != null) {
                    view.hideLoading();
                }
            }
        };
    }

    public void onCityCancelled() {
        if (view != null) {
            view.showCityCancelledMessage();
        }
    }

    public void onCityWeatherSelected(CityWeather cityWeather) {
        if (view != null) {
            view.navigateToCityWeatherDetails(cityWeather);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void onRefreshLayoutSwiped() {
        if (view != null && !model.getCityWeatherList().isEmpty()) {
            view.showLoading();
            for(CityWeather cityWeather : model.getCityWeatherList()) {
                model.getObservable(cityWeather).subscribeWith(getUpdateWeatherObserver());
            }
        }
    }

    private DisposableObserver<ForecastWeather> getUpdateWeatherObserver() {
        return new DisposableObserver<ForecastWeather>() {

            @Override
            public void onNext(@NonNull ForecastWeather response) {
                if (view != null) {
                    model.updateCurrentWeather(response);
                    view.updateCityWeatherListView(model.getCityWeatherList());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (view != null) {
                    view.hideLoading();
                    view.showErrorRetrievingWeather();
                }
            }

            @Override
            public void onComplete() {
                if (view != null) {
                    view.hideLoading();
                }
            }
        };
    }

    public void onCityWeatherSwiped(int position) {
        if (view != null) {
            view.removeCityWeatherFromList(position);
        }
    }

    public void onCityWeatherRemoved(CityWeather cityWeather) {
        model.removeCityWeatherFromList(cityWeather);
    }
}
